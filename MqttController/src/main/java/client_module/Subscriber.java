package client_module;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;

public class Subscriber {
    public static void main(String[] args) throws JMSException {
        ConsumerConnection connection = new ConsumerConnection();
        MessageConsumer consumer = connection.start();
        Message message;
        do {
            message = consumer.receive(10_000); // null after timeout
            if (message instanceof TextMessage) {
                System.out.println("Received: " + ((TextMessage) message).getText());
            } else {
                System.out.println("Received: " + message);
            }
        } while (message != null);
        connection.stop();
    }
}
