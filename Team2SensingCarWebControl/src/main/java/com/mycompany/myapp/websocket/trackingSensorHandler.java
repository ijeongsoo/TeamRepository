package com.mycompany.myapp.websocket;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.Vector;

import javax.annotation.PostConstruct;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.json.JSONObject;
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
public class trackingSensorHandler extends TextWebSocketHandler implements ApplicationListener{
	private static final Logger logger = LoggerFactory.getLogger(trackingSensorHandler.class);

	private List<WebSocketSession> list = new Vector<>();
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelateion;

	@PostConstruct
	public void init() {
		coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.36/tracking");
		coapObserveRelateion=coapClient.observe(new CoapHandler() {
			
			@Override
			public void onLoad(CoapResponse response) {
				String json=response.getResponseText();
				JSONObject jsonObject = new JSONObject(json);
				String trackingS = jsonObject.getString("tracking");
				int color=0;
				if(trackingS.equals("black")){
					color=1;
				}
				
				jsonObject= new JSONObject();
				jsonObject.put("time", getUTCTime(new Date().getTime()));
				jsonObject.put("color", color);
				json = jsonObject.toString();
				try{
					for(WebSocketSession session : list){
						session.sendMessage(new TextMessage(json));
					}
				}catch(Exception e){}
			}
			
			@Override
			public void onError() {
				
			}
		});
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("");
		list.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("");
		list.remove(session);
	}

	public long getUTCTime(long localTime) {
		long utcTime = 0;
		TimeZone tz = TimeZone.getDefault();
		try {
			int offset = tz.getOffset(localTime);
			utcTime = localTime + offset;
		} catch (Exception e) {
		}
		return utcTime;
	}
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ContextClosedEvent){
			coapObserveRelateion.proactiveCancel();
			coapClient.shutdown();
		}
	}
	

}
