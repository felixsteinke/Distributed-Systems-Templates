package server_module;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

public class Publisher {
    public static void main(String[] args) throws JMSException, InterruptedException {
        ProducerConnection connection = new ProducerConnection();
        MessageProducer producer = connection.start();
        // Create a messages
        for (int i = 1; i <= 10; i++) {
            String text = "Hello! at: " + System.currentTimeMillis();
            TextMessage message = connection.produceMessage(text);
            System.out.println("Sent message: " + text);
            producer.send(message);
            Thread.sleep(1000);
        }
        connection.stop();
    }
}
