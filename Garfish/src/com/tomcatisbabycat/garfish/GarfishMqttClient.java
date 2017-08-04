/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.garfish;

import com.tomcatisbabycat.garfish.hardware.PCA9685;
import com.tomcatisbabycat.garfish.hardware.PwmFowader;
import com.tomcatisbabycat.garfish.hardware.SG90ServoPCA9685Duration;
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
public class GarfishMqttClient {

	private MqttClient mqttClient;
	public String message;
	private int pwmThrottle;
	private int pwmRoll;
	private int pwmPitch;
	private int pwmYaw;
	private int pwmMode;
//	private boolean armed = false;
	
	private int angleLeftRight;
	private int angleUpDown;
	
	PCA9685 pca9685;
	PwmFowader roll;
	PwmFowader throttle;
	PwmFowader pitch;
	PwmFowader yaw;
	PwmFowader mode;
	
	SG90ServoPCA9685Duration servoCamLeftRight;
	SG90ServoPCA9685Duration servoCamUpDown;

	private MqttCallback mqttCallback = new MqttCallback() {
		@Override
		public void connectionLost(Throwable thrwbl) {
			System.out.println(thrwbl.getMessage());
			thrwbl.printStackTrace();
		}

		@Override
		public void messageArrived(String string, MqttMessage mm) throws Exception {
			message = new String(mm.getPayload());
			System.out.println(message);
			JSONObject jsonObject = new JSONObject(message);
			if(jsonObject.has("throttle")){
				pwmThrottle = jsonObject.getInt("throttle");
			}
			if(jsonObject.has("pitch")){
				pwmPitch = jsonObject.getInt("pitch");
			}
			if(jsonObject.has("roll")){
				pwmRoll = jsonObject.getInt("roll");
			}
			if(jsonObject.has("yaw")){
				pwmYaw = jsonObject.getInt("yaw");
			}
			if(jsonObject.has("mode")){
				pwmMode = jsonObject.getInt("mode");
			}
			if(jsonObject.has("leftRight")){
				angleLeftRight = jsonObject.getInt("leftRight");
			}
			if(jsonObject.has("upDown")){
				angleUpDown = jsonObject.getInt("upDown");
			}
			
			throttle.setPWM(pwmThrottle);
			pitch.setPWM(pwmPitch);
			roll.setPWM(pwmRoll);
			yaw.setPWM(pwmYaw);
			mode.setPWM(pwmMode);
			
			servoCamLeftRight.setAngle(angleLeftRight);
			servoCamUpDown.setAngle(angleUpDown);
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken imdt) {
		}
	};

	public GarfishMqttClient() {
		try {
			mqttClient = new MqttClient("tcp://52.78.170.231:1883", MqttClient.generateClientId());
			System.out.println(mqttClient);
			mqttClient.setCallback(mqttCallback);
			mqttClient.connect();
			pca9685= PCA9685.getInstance();
			roll = new PwmFowader(pca9685, PCA9685.PWM_01);
			System.out.println(roll + "roll");
			throttle = new PwmFowader(pca9685, PCA9685.PWM_03);
			System.out.println(throttle + "t");
			pitch = new PwmFowader(pca9685, PCA9685.PWM_02);
			System.out.println(pitch + "pticht");
			yaw= new PwmFowader(pca9685, PCA9685.PWM_04);
			System.out.println(yaw + "yaw");
			mode= new PwmFowader(pca9685, PCA9685.PWM_05);
			System.out.println(mode.toString() + "mode");
			 
			servoCamLeftRight = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_08); // 좌우
			servoCamUpDown = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_09); // 상하
			init();
		} catch (Exception ex) {
			System.out.println("MqttClient 생성 및 연결 실패");
			ex.printStackTrace();
		}
	}

	public void subscribe() {
		try {
			mqttClient.subscribe("/devices/drone/throttleAndYaw");
			mqttClient.subscribe("/devices/drone/pitchAndRoll");
			mqttClient.subscribe("/devices/drone/mode");
			mqttClient.subscribe("/devices/drone/cameraServo");
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
	public void init(){
		try{
			// transmitter 초기값 설정
			pwmThrottle=1000;
			pwmRoll=1500;
			pwmPitch=1500;
			pwmYaw=1500;
			pwmMode=1000;
			
			throttle.setPWM(pwmThrottle);
			roll.setPWM(pwmRoll);
			pitch.setPWM(pwmPitch);
			yaw.setPWM(pwmYaw);
			mode.setPWM(pwmMode);
			
			angleLeftRight=90;
			angleUpDown=10;
			
			
			// servo motor 초기값(90도) 설정
			servoCamLeftRight.setAngle(angleLeftRight);
			servoCamUpDown.setAngle(angleUpDown);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
