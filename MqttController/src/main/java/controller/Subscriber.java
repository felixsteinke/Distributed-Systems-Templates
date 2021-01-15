package controller;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber {
    public static void main(String[] args) {
        String broker = "tcp://mqtt.eclipse.org:1883";
        String topicName = "test/topic";
        try {
            MqttClient mqttClient = new MqttClient(broker, String.valueOf(System.nanoTime()));
            mqttClient.setCallback(new SimpleCallback());
            mqttClient.connect();
            mqttClient.subscribe(topicName);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
