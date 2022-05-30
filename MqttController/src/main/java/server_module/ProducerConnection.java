package server_module;

import org.apache.activemq.ActiveMQConnectionFactory;
import shared_module.ConnectionInfo;

import javax.jms.*;
import java.util.logging.Logger;

public class ProducerConnection {
    private static final Logger logger = Logger.getLogger(ProducerConnection.class.getName());
    private Connection connection;
    private Session session;
    private MessageProducer producer;

    public MessageProducer start() throws JMSException {
        connection = createActiveMqConnection();
        session = createAutoSession(connection);
        producer = createTopicProducer(session);
        logger.info("Started Connection.");
        return this.producer;
    }

    public TextMessage produceMessage(String text) throws JMSException {
        return session != null ? session.createTextMessage(text) : null;
    }

    public void stop() throws JMSException {
        if (producer != null) producer.close();
        if (session != null) session.close();
        if (connection != null) connection.close();
        logger.info("Stopped Connection.");
    }

    /**
     * Parameters:
     * <ul>
     *     <li>destination type (topic or queue)</li>
     *     <li>destination name</li>
     *     <li>delivery mode</li>
     * </ul>
     *
     * @param session with activemq
     * @return producer with defined parameters
     * @throws JMSException if connection fails
     */
    private MessageProducer createTopicProducer(Session session) throws JMSException {
        Destination destination = session.createTopic(ConnectionInfo.TOPIC_NAME);
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        return producer;
    }

    /**
     * Parameters:
     * <ul>
     *     <li>transacted (true or false)</li>
     *     <li>session mode</li>
     * </ul>
     *
     * @param connection to activemq
     * @return session with defined parameters
     * @throws JMSException if connection fails
     */
    private Session createAutoSession(Connection connection) throws JMSException {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    /**
     * Parameters:
     * <ul>
     *     <li>connection url</li>
     * </ul>
     *
     * @return activemq connection with defined parameters
     * @throws JMSException if connection fails
     */
    private Connection createActiveMqConnection() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ConnectionInfo.BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        return connection;
    }
}
