package com.micro.api.abo.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.api.MessageConfig;
import com.micro.api.abo.Abo;
import com.micro.api.abo.IAboService;
import com.micro.api.external.IProductService;
import com.micro.api.external.PaymentOrder;
import com.micro.api.external.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class AboManagement implements IAboService, MessageListener {

    private final Logger LOGGER = Logger.getLogger(AboManagement.class.getName());
    private final ObjectMapper MAPPER = new ObjectMapper();
    private final ScheduledExecutorService MONTH_SCHEDULER = Executors.newSingleThreadScheduledExecutor();

    private final AboRepo aboRepo;
    private final IProductService productService;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public AboManagement(AboRepo aboRepo, IProductService productService, JmsTemplate jmsTemplate) {
        this.aboRepo = aboRepo;
        this.productService = productService;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void addAbo(Integer productNr) {
        Product input = productService.getProduct(productNr);
        AboEntity newEntity = aboRepo.save(
                new AboEntity()
                        .setProductName(input.getName())
                        .setProductNr(input.getNr())
                        .setPrice(input.getPrice())
                        .setPayed(false)
        );
        this.orderPayment(newEntity);
    }

    public void deleteAbo(Integer aboId) {
        aboRepo.deleteById(aboId);
    }

    public List<Abo> getAllAbos() {
        List<AboEntity> table = aboRepo.findAll();
        return table.stream().map(AboMapper::model).collect(Collectors.toList());
    }

    public Abo getAbo(Integer aboId) {
        AboEntity entity = aboRepo.findById(aboId).orElseThrow();
        return AboMapper.model(entity);
    }

    @Override
    @JmsListener(destination = MessageConfig.PAYMENT_ACK_QUEUE)
    public void onMessage(Message message) {
        try {
            PaymentOrder payment = MAPPER.readValue(((TextMessage) message).getText(), PaymentOrder.class);
            LOGGER.info("Payment was confirmed: " + payment);
            aboRepo.save(aboRepo.findById(payment.getOrderId()).orElseThrow()
                    .setPayed(true));
        } catch (JMSException | JsonProcessingException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    private void orderPayment(AboEntity abo) {
        LOGGER.info("Ordering payment for " + abo);
        try {
            jmsTemplate.convertAndSend(MessageConfig.PAYMENT_QUEUE, new PaymentOrder(abo.getId(), abo.getPrice()));
        } catch (Exception e) {
            LOGGER.warning("Failed to order payment: " + e.getMessage());
        }
    }

    @PostConstruct
    private void onStart() {
        MONTH_SCHEDULER.scheduleAtFixedRate(() -> {
            LOGGER.info("Monthly abo reset!");
            aboRepo.findAll().forEach(abo -> {
                aboRepo.save(abo.setPayed(false));
                this.orderPayment(abo);
            });
        }, 10, 30, TimeUnit.SECONDS);
    }

    @PreDestroy
    private void onStop() {
        MONTH_SCHEDULER.shutdown();
    }
}
