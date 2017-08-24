/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.garfish.device;

import com.tomcatisbabycat.garfish.hardware.PCA9685;
import com.tomcatisbabycat.garfish.hardware.PwmFowader;
import com.tomcatisbabycat.garfish.hardware.SG90ServoPCA9685Duration;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kang
 */
public class DeviceMqttClient {
	private static final Logger LOG = LoggerFactory.getLogger(DeviceMqttClient.class);
	
	private DeviceInfo dInfo = DeviceInfo.getInstance();
	
	private MqttClient mqttClient;
	private String message;
	
	ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(2);
	
	private MqttCallback mqttCallback = new MqttCallback() {
		@Override
		public void connectionLost(Throwable thrwbl) {
			thrwbl.printStackTrace();
		}

		@Override
		public void messageArrived(String string, MqttMessage mm) throws Exception {
			message = new String(mm.getPayload());
			JSONObject jsonObject = new JSONObject(message);
			
			// NullPointException prevent
			if(jsonObject.has("throttle")){dInfo.setPwmThrottle(jsonObject.getInt("throttle"));}
			if(jsonObject.has("pitch")){dInfo.setPwmPitch(jsonObject.getInt("pitch"));}
			if(jsonObject.has("roll")){dInfo.setPwmRoll(jsonObject.getInt("roll"));}
			if(jsonObject.has("yaw")){dInfo.setPwmYaw(jsonObject.getInt("yaw"));}
			if(jsonObject.has("mode")){dInfo.setPwmMode(jsonObject.getInt("mode"));}
			if(jsonObject.has("leftRight")){dInfo.setAngleLeftRight(jsonObject.getInt("leftRight"));}
			if(jsonObject.has("upDown")){dInfo.setAngleUpDown(jsonObject.getInt("upDown"));}
			
			if(jsonObject.has("pwmCheck")){
				pwmCheck();
			}
			
			dInfo.getThrottle().setPWM(dInfo.getPwmThrottle());
			dInfo.getRoll().setPWM(dInfo.getPwmRoll());
			dInfo.getYaw().setPWM(dInfo.getPwmYaw());
			dInfo.getPitch().setPWM(dInfo.getPwmPitch());
			dInfo.getMode().setPWM(dInfo.getPwmMode());
			
			dInfo.getServoCamLeftRight().setAngle(dInfo.getAngleLeftRight());
			dInfo.getServoCamUpDown().setAngle(dInfo.getAngleUpDown());
			
			LOG.info("Throttle - " + dInfo.getPwmThrottle());
			LOG.info("Yaw - " + dInfo.getPwmYaw());
			LOG.info("Pitch - " + dInfo.getPwmPitch());
			LOG.info("Roll - " + dInfo.getPwmRoll());
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken imdt) {
//			LOG.info("DeliveryComplete");
		}
	};
	
	// CONSTRUCTOR
	public DeviceMqttClient() {
		try {
			LOG.info("MQTT Client 생성");
			mqttClient = new MqttClient(dInfo.getMqttIP(), MqttClient.generateClientId());
			mqttClient.setCallback(mqttCallback);
			mqttClient.connect();
			dInfo.setPca9685(PCA9685.getInstance());
			
			// 트랜스미터 각 기능과 채널을 매칭.
			dInfo.setRoll(new PwmFowader(dInfo.getPca9685(), PCA9685.PWM_01));
			dInfo.setPitch(new PwmFowader(dInfo.getPca9685(), PCA9685.PWM_02));
			dInfo.setThrottle(new PwmFowader(dInfo.getPca9685(), PCA9685.PWM_03));
			dInfo.setYaw(new PwmFowader(dInfo.getPca9685(), PCA9685.PWM_04));
			
			// Flight Mode PWM Value
			dInfo.setMode(new PwmFowader(dInfo.getPca9685(), PCA9685.PWM_05));
			
			// Camera Module
			dInfo.setServoCamLeftRight(new SG90ServoPCA9685Duration(dInfo.getPca9685(), PCA9685.PWM_08));// 좌우
			dInfo.setServoCamUpDown(new SG90ServoPCA9685Duration(dInfo.getPca9685(), PCA9685.PWM_09)); // 상하
			
			dInfo.init();
		} catch (Exception ex) {
			LOG.info("MqttClient 생성 및 연결 실패");
			ex.printStackTrace();
		}
	}
	
	// METHOD
	public void subscribe() {
		try {
			// PWM value subscribe
			mqttClient.subscribe(dInfo.getMtThrottleAndYaw());
			mqttClient.subscribe(dInfo.getMtPitchAndRoll());
			mqttClient.subscribe(dInfo.getMtMode());
			mqttClient.subscribe(dInfo.getMtCameraServo());
			
			// Client에게 전해줄 최신값
			mqttClient.subscribe(dInfo.getMtPwmCheck());
		} catch (MqttException ex) {
			LOG.info("구독 실패");
		}
	}
	
	public void pwmCheck(){
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("throttle", dInfo.getPwmThrottle());
		jsonObject.put("pitch", dInfo.getPwmPitch());
		jsonObject.put("yaw", dInfo.getPwmYaw());
		jsonObject.put("roll", dInfo.getPwmRoll());
		jsonObject.put("mode", dInfo.getPwmMode());
		
		String reqJson = jsonObject.toString();
		
		MqttMessage message  = new MqttMessage(reqJson.getBytes());
		message.setQos(0);
		
		try {
			mqttClient.publish(dInfo.getMtPwm(), message);
		} catch (MqttException ex) {
			LOG.info("pwmCheck() - publish 실패");
		}
	}
	
	public void communicationCheck(){
		int sleepSec = 500;
		
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("comm", dInfo.getMacAddress());
				
				String reqJson = jsonObject.toString();
				
				MqttMessage message = new MqttMessage(reqJson.getBytes());
				message.setQos(0);
				
				try {
					mqttClient.publish(dInfo.getMtCommunication(), message);
				} catch (MqttException ex) {
					LOG.info("Communication() - publish 실패");
				}
			}
		}, 0, sleepSec, TimeUnit.MILLISECONDS);
	}
	
	public void sensorValue(){
		int sleepSec = 100;
		
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("airSpeed", dInfo.getAirSpeed());
				jsonObject.put("groundSpeed", dInfo.getGroundSpeed());
				jsonObject.put("altitude", dInfo.getAltitude());
				jsonObject.put("heading", dInfo.getHeading());
				jsonObject.put("rollSensor", dInfo.getRollSensor());
				jsonObject.put("pitchSensor", dInfo.getPitchSensor());
				jsonObject.put("yawSensor", dInfo.getYawSensor());
				jsonObject.put("latitude", dInfo.getLatitude());
				jsonObject.put("longitude", dInfo.getLongitude());
				
				String reqJson = jsonObject.toString();
				
				MqttMessage message = new MqttMessage(reqJson.getBytes());
				message.setQos(0);
				
				try {
					mqttClient.publish(dInfo.getMtSensor(), message);
				} catch (MqttException ex) {
					LOG.info("sensorValue() - publish 실패");
				}
			}
		}, 0, sleepSec, TimeUnit.MILLISECONDS);
	}
	
	public void shutdown(){
		try {
			mqttClient.disconnect();
			mqttClient.close();
		} catch (MqttException ex) {
			
		}
	}
}
