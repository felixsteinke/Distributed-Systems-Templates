package com.micro.shop.abo.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.shop.MessageConfig;
import com.micro.shop.abo.access.Abo;
import com.micro.shop.abo.access.AboCreator;
import com.micro.shop.consumer.imported.PaymentOrder;
import com.micro.shop.consumer.imported.ProductSelector;
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
public class AboManagement implements AboCreator, MessageListener {

    private final Logger LOGGER = Logger.getLogger(AboManagement.class.getName());
    private final ObjectMapper MAPPER = new ObjectMapper();
    private final ScheduledExecutorService MONTH_SCHEDULER = Executors.newSingleThreadScheduledExecutor();

    private final AboRepo aboRepo;
    private final ProductSelector productSelector;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public AboManagement(AboRepo aboRepo, ProductSelector productSelector, JmsTemplate jmsTemplate) {
        this.aboRepo = aboRepo;
        this.productSelector = productSelector;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void addAbo(Integer productNr) {
        AboEntity abo = aboRepo.save(new AboEntity(productSelector.getProduct(productNr)));
        this.orderPayment(abo);
    }

    public void deleteAbo(Integer aboId) {
        aboRepo.deleteById(aboId);
    }

    public List<Abo> getAllAbos() {
        return aboRepo.findAll().stream().map(Abo::new).collect(Collectors.toList());
    }

    public Abo getAbo(Integer aboId) {
        return new Abo(aboRepo.findById(aboId).orElseThrow());
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
