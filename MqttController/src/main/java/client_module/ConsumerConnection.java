package client_module;

import org.apache.activemq.ActiveMQConnectionFactory;
import shared_module.ConnectionInfo;

import javax.jms.*;
import java.util.logging.Logger;

public class ConsumerConnection implements ExceptionListener {
    private static final Logger logger = Logger.getLogger(ConsumerConnection.class.getName());
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;

    public MessageConsumer start() throws JMSException {
        connection = createActiveMqConnection();
        session = createAutoSession(connection);
        consumer = createTopicConsumer(session);
        logger.info("Started Connection.");
        return this.consumer;
    }

    public void stop() throws JMSException {
        if (consumer != null) consumer.close();
        if (session != null) session.close();
        if (connection != null) connection.close();
        logger.info("Stopped Connection.");
    }

    /**
     * Parameters:
     * <ul>
     *     <li>destination type (topic or queue)</li>
     *     <li>destination name</li>
     * </ul>
     *
     * @param session with activemq
     * @return consumer with defined parameters
     * @throws JMSException if connection fails
     */
    private MessageConsumer createTopicConsumer(Session session) throws JMSException {
        Destination destination = session.createTopic(ConnectionInfo.TOPIC_NAME);
        return session.createConsumer(destination);
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
     *     <li>exception listener</li>
     * </ul>
     *
     * @return activemq connection with defined parameters
     * @throws JMSException if connection fails
     */
    private Connection createActiveMqConnection() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ConnectionInfo.BROKER_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        connection.setExceptionListener(this);
        return connection;
    }

    @Override
    public void onException(JMSException e) {
        logger.warning("Consumer exception occurred (" + e.getClass().getSimpleName() + "):" + e.getMessage());
    }
}
