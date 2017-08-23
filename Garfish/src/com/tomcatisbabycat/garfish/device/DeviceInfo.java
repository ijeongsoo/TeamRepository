/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.garfish.device;

import com.tomcatisbabycat.garfish.hardware.PCA9685;
import com.tomcatisbabycat.garfish.hardware.PwmFowader;
import com.tomcatisbabycat.garfish.hardware.SG90ServoPCA9685Duration;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kang
 */
public class DeviceInfo {
	private static final Logger LOG = LoggerFactory.getLogger(DeviceInfo.class);
	
	private static final DeviceInfo DEVICE_INFO = new DeviceInfo();
	
	// Mqtt IP ADDR
	private static final String IP = "106.253.56.122";
	
	private String mqttIP = "tcp://" + IP + ":1883";
	
	// PWM Setting
	private int pwmThrottle;
	private int pwmRoll;
	private int pwmPitch;
	private int pwmYaw;
	private int pwmMode;
	private int angleLeftRight;
	private int angleUpDown;
	
	private PCA9685 pca9685;
	private PwmFowader roll;
	private PwmFowader throttle;
	private PwmFowader pitch;
	private PwmFowader yaw;
	private PwmFowader mode;
	private SG90ServoPCA9685Duration servoCamLeftRight;
	private SG90ServoPCA9685Duration servoCamUpDown;
	
	// Devices MAC ADDR
	private String macIP;
	
	private DatagramSocket socket;
	
	// Device IP ADDR
	private InetAddress inet;
	private String deviceIP;
	
	// UDP default Connect Port
	private int defaultPort = 20000;
	
	// Mqtt Topic
	private String mtThrottleAndYaw;
	private String mtPitchAndRoll;
	private String mtMode;
	private String mtCameraServo;
	private String mtPwmCheck;
	private String mtPwm;
	private String mtCamera;
	private String mtCommunication;
	private String mtSensor;
	
	// Sensor Value
	private String airSpeed;
	private String groundSpeed;
	private String altitude;
	private String heading;
	private String rollSensor;
	private String pitchSensor;
	private String yawSensor;
	private String latitude;
	private String longitude;
	
	private DeviceInfo(){}
	
	public static DeviceInfo getInstance(){
		return DEVICE_INFO;
	}
	
	public String getMacAddress(){
		try{
			boolean isLoopBack = true;
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();

			while(en.hasMoreElements()){
				NetworkInterface ni = en.nextElement();
				
				
				if(ni.getName().equals("eth0")){
					byte[] mac = ni.getHardwareAddress();
					
					StringBuilder sb = new StringBuilder(18);
					
					for(byte b : mac){
						if(sb.length() > 0){
							sb.append(':');
						}
						sb.append(String.format("%02X", b));
					}
					macIP = sb.toString();
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return macIP;
	}
	
	public void setLocalUsedIP(){
		try {
			for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();){
				NetworkInterface intf = en.nextElement();
				
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();){
					InetAddress inet = enumIpAddr.nextElement();
					 if (!inet.isLoopbackAddress() && !inet.isLinkLocalAddress() && inet.isSiteLocalAddress()){
						 this.inet = inet;
						 deviceIP = inet.getHostAddress().toString();
					 }
				}
			}
		} catch (SocketException ex) {
			
		}
		LOG.info("IP : " + deviceIP);
	}
	
	
	public void makeTopic(){
		mtThrottleAndYaw = "/" + macIP + "/throttleAndYaw";
		mtPitchAndRoll = "/" + macIP + "/pitchAndRoll";
		mtMode = "/" + macIP + "/mode";
		mtCameraServo = "/" + macIP + "/cameraServo";	  
		mtPwmCheck = "/" + macIP + "/pwmCheck";
		mtPwm = "/" + macIP + "/pwm";
		mtCamera = "/" + macIP + "/camera";
		mtCommunication = "/" + macIP + "/communicationCheck";
		mtSensor = "/" + macIP + "/Sensor";
		
		LOG.info(mtThrottleAndYaw);
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
			angleUpDown=140;
			
			
			// servo motor 초기값(90도) 설정
			servoCamLeftRight.setAngle(angleLeftRight);
			servoCamUpDown.setAngle(angleUpDown);
		}catch(Exception e){
			LOG.info("PWM 초기화 실패");
			e.printStackTrace();
		}
		
	}

	public String getMtThrottleAndYaw() {
		return mtThrottleAndYaw;
	}

	public String getMtPitchAndRoll() {
		return mtPitchAndRoll;
	}

	public String getMtMode() {
		return mtMode;
	}

	public String getMtCameraServo() {
		return mtCameraServo;
	}

	public String getMtPwmCheck() {
		return mtPwmCheck;
	}
	
	public String getMtPwm(){
		return mtPwm;
	}

	public String getMtCamera() {
		return mtCamera;
	}

	public String getMtCommunication() {
		return mtCommunication;
	}

	public String getMtSensor() {
		return mtSensor;
	}
	
	public String getAirSpeed() {
		return airSpeed;
	}

	public void setAirSpeed(String airSpeed) {
		this.airSpeed = airSpeed;
	}

	public String getGroundSpeed() {
		return groundSpeed;
	}

	public void setGroundSpeed(String groundSpeed) {
		this.groundSpeed = groundSpeed;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getRollSensor() {
		return rollSensor;
	}

	public void setRollSensor(String rollSensor) {
		this.rollSensor = rollSensor;
	}

	public String getPitchSensor() {
		return pitchSensor;
	}

	public void setPitchSensor(String pitchSensor) {
		this.pitchSensor = pitchSensor;
	}

	public String getYawSensor() {
		return yawSensor;
	}

	public void setYawSensor(String yawSensor) {
		this.yawSensor = yawSensor;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getMqttIP() {
		return mqttIP;
	}

	public int getPwmThrottle() {
		return pwmThrottle;
	}

	public int getPwmRoll() {
		return pwmRoll;
	}

	public int getPwmPitch() {
		return pwmPitch;
	}

	public int getPwmYaw() {
		return pwmYaw;
	}

	public int getPwmMode() {
		return pwmMode;
	}

	public int getAngleLeftRight() {
		return angleLeftRight;
	}

	public int getAngleUpDown() {
		return angleUpDown;
	}

	public PCA9685 getPca9685() {
		return pca9685;
	}

	public PwmFowader getRoll() {
		return roll;
	}

	public PwmFowader getThrottle() {
		return throttle;
	}

	public PwmFowader getPitch() {
		return pitch;
	}

	public PwmFowader getYaw() {
		return yaw;
	}

	public PwmFowader getMode() {
		return mode;
	}

	public SG90ServoPCA9685Duration getServoCamLeftRight() {
		return servoCamLeftRight;
	}

	public SG90ServoPCA9685Duration getServoCamUpDown() {
		return servoCamUpDown;
	}

	public void setPwmThrottle(int pwmThrottle) {
		this.pwmThrottle = pwmThrottle;
	}

	public void setPwmRoll(int pwmRoll) {
		this.pwmRoll = pwmRoll;
	}

	public void setPwmPitch(int pwmPitch) {
		this.pwmPitch = pwmPitch;
	}

	public void setPwmYaw(int pwmYaw) {
		this.pwmYaw = pwmYaw;
	}

	public void setPwmMode(int pwmMode) {
		this.pwmMode = pwmMode;
	}

	public void setAngleLeftRight(int angleLeftRight) {
		this.angleLeftRight = angleLeftRight;
	}

	public void setAngleUpDown(int angleUpDown) {
		this.angleUpDown = angleUpDown;
	}

	public void setPca9685(PCA9685 pca9685) {
		this.pca9685 = pca9685;
	}

	public void setRoll(PwmFowader roll) {
		this.roll = roll;
	}

	public void setThrottle(PwmFowader throttle) {
		this.throttle = throttle;
	}

	public void setPitch(PwmFowader pitch) {
		this.pitch = pitch;
	}

	public void setYaw(PwmFowader yaw) {
		this.yaw = yaw;
	}

	public void setMode(PwmFowader mode) {
		this.mode = mode;
	}

	public void setServoCamLeftRight(SG90ServoPCA9685Duration servoCamLeftRight) {
		this.servoCamLeftRight = servoCamLeftRight;
	}

	public void setServoCamUpDown(SG90ServoPCA9685Duration servoCamUpDown) {
		this.servoCamUpDown = servoCamUpDown;
	}

	public String getDeviceIP() {
		return deviceIP;
	}

	public InetAddress getInet() {
		return inet;
	}

	public int getDefaultPort() {
		return defaultPort;
	}

	public DatagramSocket getSocket() {
		return socket;
	}

	public void setSocket(DatagramSocket socket) {
		this.socket = socket;
	}
	
	
}
