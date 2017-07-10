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
public class ThermistorSensor {
	private static ThermistorSensor thermistorSensor = new ThermistorSensor();
	
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;
	private static final String IP = "192.168.3.40";
	private static final String RESOURCE = "thermistorsensor";
	private String coapIp = "coap://"+ IP +"/" + RESOURCE;
	
	private double thermistor;
	
	private ThermistorSensor(){
		init();
	}
	
	public static ThermistorSensor getInstance(){
		return thermistorSensor;
	}
	
	private void init(){
		coapClient = new CoapClient();
		coapClient.setURI(coapIp);
		
		coapObserveRelation = coapClient.observe(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				String json = response.getResponseText();
				
				JSONObject jsonObject = new JSONObject(json);
				thermistor = Double.parseDouble(jsonObject.getString("temperature"));
			}

			@Override
			public void onError() {
				System.out.println("ThermistorSensor - onError()");
			}
		});
	}

	public double getThermistor() {
		return thermistor;
	}
	
	public static void main(String[] args) {
		ThermistorSensor thermistorSensor = ThermistorSensor.getInstance();
		
		while(true){
			System.out.println("" + thermistorSensor.getThermistor());
			try{Thread.sleep(1000);}catch(Exception e){}
		}
	}
}
