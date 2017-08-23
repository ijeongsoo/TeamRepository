package com.mycompany.myapp.websocket;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Vector;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class communicationCheckHandler extends TextWebSocketHandler implements ApplicationListener{
	private static final Logger LOGGER = LoggerFactory.getLogger(communicationCheckHandler.class);
	// 멀티스레드 환경에서 사용하기 위하여 vector 활용!
	private List<WebSocketSession> list = new Vector<>();
	private MqttClient mqttClient;
	private MqttCallback mqttCallback;

	@PostConstruct
	public void init() throws MqttSecurityException, MqttException {
		
		LOGGER.info("init실행");
		mqttClient = new MqttClient("tcp://106.253.56.122:1883", MqttClient.generateClientId());
		
		mqttCallback = new MqttCallback() {
			@Override
			public void connectionLost(Throwable thrwbl) {
				thrwbl.printStackTrace();
			}

			@Override
			public void messageArrived(String string, MqttMessage mm) throws Exception {
				
				try {
					for (WebSocketSession session : list) {
						session.sendMessage(new TextMessage(mm.toString()));
					}
				} catch (Exception e) {
				}
			}

			@Override
			public void deliveryComplete(IMqttDeliveryToken imdt) {
				System.out.println("deliver"+ "yComplete:" + new Date());
			}
		};
		
		mqttClient.setCallback(mqttCallback);
		
		mqttClient.connect();
		
		LOGGER.info("init실행");	
		
	}
	
	@PreDestroy
	public void destroy() throws MqttException{
		mqttClient.disconnect();
		mqttClient.close();
	}

	
	

	public void shudDown() throws MqttException {
		// MQTT 브로커와 연결 해제
		mqttClient.disconnect();
		// MQTT 클라이언트가 사용한 리소스(메모리 등) 해제        
		mqttClient.close();
	}
	
		

	// 메소드 이름 = 이 메소드가 언제 시행할지
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		LOGGER.info("");
		list.add(session);
		Map<String, Object> map = session.getAttributes();
		String dmacaddr = (String)map.get("dmacaddr");
		mqttClient.subscribe("/"+dmacaddr+"/communicationCheck");
		System.out.println(dmacaddr);
	}

	// 연결이 끊어진 후 시행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		LOGGER.info("");
		list.remove(session);
	}

	// 메시지를 받았을 때 시행
	// 여기서 사용하는 session은 HTTPSession이 아님!명심하자!
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		LOGGER.info("");
		String strMessage = message.getPayload();
		TextMessage txtMessage = new TextMessage(strMessage);
		session.sendMessage(txtMessage);
	}
	
	

	public long getTimeUTC(long localTime) {
		long utcTime = 0;
		TimeZone tz = TimeZone.getDefault();
		try {
			int offset = tz.getOffset(localTime);
			utcTime = localTime + offset;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return utcTime;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ContextClosedEvent) {
			LOGGER.info("Webapplication stop");
		
		}
	}
}