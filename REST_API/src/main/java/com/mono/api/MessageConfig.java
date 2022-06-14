package com.mono.api;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

/**
 * <h2>Spring Application Config</h2>
 * Spring JMS can send/receive messages both synchronously and asynchronously.
 * The connector to ActiveMQ needs to be configured in the beginning.
 * <p>Tutorial: https://www.baeldung.com/spring-bean-vs-ejb</p>
 * <p>Important: Start the broker (cmd: activemq start)</p>
 * <p>https://memorynotfound.com/spring-boot-embedded-activemq-configuration-example/</p>
 */
@Configuration
@EnableJms
public class MessageConfig {
    public static final String PAYMENT_QUEUE = "springMonoPaymentQueue";
    public static final String PAYMENT_ACK_QUEUE = "springMonoPaymentAckQueue";

    @Value("${spring.activemq.broker-url}")
    private String activeMqBrokerUrl;

    @Value("${spring.activemq.user}")
    private String activeMqUser;

    @Value("${spring.activemq.password}")
    private String activeMqPassword;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(activeMqUser, activeMqPassword, activeMqBrokerUrl);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate template = new JmsTemplate(connectionFactory);
        template.setConnectionFactory(connectionFactory);
        template.setMessageConverter(jacksonJmsMessageConverter());
        return template;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
