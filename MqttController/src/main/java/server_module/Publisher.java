package server_module;

import org.eclipse.paho.client.mqttv3.*;
import shared_module.ConnectionInfo;

public class Publisher {

    public static void main(String[] args) {
        int qos = 1;
        try {
            MqttClient mqttClient = new MqttClient(ConnectionInfo.BROKER_URL, String.valueOf(System.nanoTime()));
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true); //no persistent session
            connOpts.setKeepAliveInterval(1000);

            MqttMessage message = new MqttMessage("Ed Sheeran".getBytes());
            message.setQos(qos);     //sets qos level 1
            message.setRetained(true); //sets retained message

            MqttTopic topic = mqttClient.getTopic(ConnectionInfo.TOPIC_NAME);
            mqttClient.connect(connOpts); //connects the broker with connect options
            topic.publish(message);    // publishes the message to the topic(test/topic)
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
