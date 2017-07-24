/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.garfish;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author ijeongsu
 */
public class GarfishMqttClient {

	private MqttClient mqttClient;

	private MqttCallback mqttCallback = new MqttCallback() {
		@Override
		public void connectionLost(Throwable thrwbl) {
		}

		@Override
		public void messageArrived(String string, MqttMessage mm) throws Exception {
//			String text = new String(mm.getPayload());
//			System.out.println(text);

			
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken imdt) {
		}
	};

	public GarfishMqttClient() {
		try {
			mqttClient = new MqttClient("tcp://192.168.3.129:1883", MqttClient.generateClientId());
			mqttClient.setCallback(mqttCallback);
			mqttClient.connect();
		} catch (MqttException ex) {
			System.out.println("MqttClient 생성 및 연결 실패");
		}
	}

	public void subscribe() {
		try {
			mqttClient.subscribe("/devices/device1/temperature");
		} catch (MqttException ex) {
			System.out.println("구독 실패");
		}
	}

	public void shutdown() {
		try {
			mqttClient.disconnect();
			mqttClient.close();

		} catch (MqttException ex) {
			System.out.println("닫기 실패");
		}
	}
}
