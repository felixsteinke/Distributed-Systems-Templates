package com.mono.api.payment.internal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mono.api.MessageConfig;
import com.mono.api.payment.PaymentOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

@Component
public class PaymentExecutor implements MessageListener {

    private final Logger LOGGER = Logger.getLogger(PaymentExecutor.class.getName());
    private final ObjectMapper MAPPER = new ObjectMapper();

    private final JmsTemplate jmsTemplate;
    private double bankAccount;

    @Autowired
    public PaymentExecutor(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.bankAccount = 0f;
    }

    @Override
    @JmsListener(destination = MessageConfig.PAYMENT_QUEUE)
    public void onMessage(Message message) {
        try {
            PaymentOrder payment = MAPPER.readValue(((TextMessage) message).getText(), PaymentOrder.class);
            bankAccount += payment.getPrice();
            LOGGER.info("Received payment: " + payment + " (Bank: " + bankAccount + ")");
            this.confirmPayment(payment);
        } catch (JMSException | JsonProcessingException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    private void confirmPayment(PaymentOrder payment) {
        jmsTemplate.convertAndSend(MessageConfig.PAYMENT_ACK_QUEUE, payment);
    }

    public Double getBankAccount() {
        return this.bankAccount;
    }
}
