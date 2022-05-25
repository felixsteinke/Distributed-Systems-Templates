package client_module;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import shared_module.ConnectionInfo;
import shared_module.SimpleCallback;

public class Subscriber {
    public static void main(String[] args) {
        try {
            MqttClient mqttClient = new MqttClient(ConnectionInfo.BROKER_URL, String.valueOf(System.nanoTime()));
            mqttClient.setCallback(new SimpleCallback());
            mqttClient.connect();

            mqttClient.subscribe(ConnectionInfo.TOPIC_NAME);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
