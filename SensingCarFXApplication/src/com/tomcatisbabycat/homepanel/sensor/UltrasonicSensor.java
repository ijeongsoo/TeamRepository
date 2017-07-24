/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.sensor;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.json.JSONObject;

/**
 *
 * @author kang
 */
public class UltrasonicSensor {
	private static UltrasonicSensor trackingSensor = new UltrasonicSensor();
	
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;
	private static final String IP = "192.168.3.40";
	private static final String RESOURCE = "ultrasonicsensor";
	private String coapIp = "coap://"+ IP +"/" + RESOURCE;
	
	private int distance;
	private int angle;
	
	private UltrasonicSensor(){
		init();
	}
	
	public static UltrasonicSensor getInstance(){
		return trackingSensor;
	}
	
	private void init(){
		coapClient = new CoapClient();
		coapClient.setURI(coapIp);
		
		coapObserveRelation = coapClient.observe(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				String json = response.getResponseText();
				
				JSONObject jsonObject = new JSONObject(json);
				distance = Integer.parseInt(jsonObject.getString("distance"));
				angle = Integer.parseInt(jsonObject.getString("angle"));
			}

			@Override
			public void onError() {
				System.out.println("UltrasonicSensor - onError()");
			}
		});
	}

	public int getDistance() {
		return distance;
	}
	public int getAngle(){
		return angle;
	}
	
	public static void main(String[] args) {
		UltrasonicSensor ultrasonicSensor = UltrasonicSensor.getInstance();
		
		while(true){
			System.out.println("" + ultrasonicSensor.getDistance());
			try{Thread.sleep(1000);}catch(Exception e){}
		}
	}
}
