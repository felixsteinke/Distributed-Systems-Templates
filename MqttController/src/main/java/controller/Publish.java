package controller;

import org.eclipse.paho.client.mqttv3.*;

public class Publish {

    public static void main(String[] args) {

        String broker = "tcp://mqtt.eclipse.org:1883";
        String topicName = "test/topic";
        int qos = 1;
        try {
            MqttClient mqttClient = new MqttClient(broker, String.valueOf(System.nanoTime()));
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true); //no persistent session
            connOpts.setKeepAliveInterval(1000);
            MqttMessage message = new MqttMessage("Ed Sheeran".getBytes());
            message.setQos(qos);     //sets qos level 1
            message.setRetained(true); //sets retained message
            MqttTopic topic2 = mqttClient.getTopic(topicName);
            mqttClient.connect(connOpts); //connects the broker with connect options
            topic2.publish(message);    // publishes the message to the topic(test/topic)
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
