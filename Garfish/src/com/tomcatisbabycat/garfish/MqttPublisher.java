/**
 *
 * @author 2Team(Lee, Kang, Cho)
 */
package com.tomcatisbabycat.garfish;

import java.util.Date;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqttPublisher {
	// LOGGER
	private static final Logger LOG = LoggerFactory.getLogger(MqttPublisher.class);
	
	// FIELD
	private MqttClient mqttClient;
	
	private MqttCallback mqttCallback = new MqttCallback() {
		@Override
		public void connectionLost(Throwable thrwbl) {
			thrwbl.printStackTrace();
		}

		@Override
		public void messageArrived(String string, MqttMessage mm) throws Exception {
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken imdt) {
			LOG.info("deliveryComplete:"+ new Date());
		}
	};
	
	// CONSTRUCTOR
	public MqttPublisher() throws MqttException {
		//MQTT 클라이언트 생성
		mqttClient = new MqttClient("tcp://106.253.56.122:1883", MqttClient.generateClientId());

		//통신 결과에 따라 실행할 콜백 객체 생성 
		mqttClient.setCallback(mqttCallback);
		//MQTT 브로커와 연결하기 
		mqttClient.connect();
	}
	
	// METHOD
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
}
