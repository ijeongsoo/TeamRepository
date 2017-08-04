package com.tomcatisbabycat.garfish;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;


/**
 *
 * @author ijeongsu
 */
public class MqttPublisher {
	//field
	private MqttClient mqttClient;
	
	private MqttCallback mqttCallback = new MqttCallback() {
		@Override
		public void connectionLost(Throwable thrwbl) {
		}

		@Override
		public void messageArrived(String string, MqttMessage mm) throws Exception {
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken imdt) {
			System.out.println("deliveryComplete:"+ new Date());
		}
	};
	
	
	//Constructor

	public MqttPublisher() throws MqttException {
		//MQTT 클라이언트 생성
		mqttClient = new MqttClient("tcp://192.168.3.127:1883", MqttClient.generateClientId());
		//통신 결과에 따라 실행할 콜백 객체 생성 
		mqttClient.setCallback(mqttCallback);
		//MQTT 브로커와 연결하기 
		mqttClient.connect();
	}
	
	
	//Method
	public void publish(String text) throws MqttException{
		//MQTT 브로커로 보내는 메시지 생성
		MqttMessage message = new MqttMessage(text.getBytes());
		//MQTT 브로커로 메시지 보냄 
		mqttClient.publish("/devices/drone/throttle", message);
	}
	
	public void shutdown() throws MqttException{
		//연결 끊기
		mqttClient.disconnect();
		//클라이언트 사용한 리소스를 해제 
		mqttClient.close();
	}
	
	public static void main(String[] args) throws Exception {
		MqttPublisher publisher = new MqttPublisher();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("throttle", 1300);
		String text = jsonObject.toString();
		
		
		publisher.publish(text);
		Thread.sleep(1000);
		
		publisher.shutdown();
		
	}
}
