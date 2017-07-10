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
public class PhotoresistorSensor {
	private static PhotoresistorSensor photoresistorSensor = new PhotoresistorSensor();
	
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;
	private static final String IP = "192.168.3.40";
	private static final String RESOURCE = "photoresistor";
	private String coapIp = "coap://"+ IP +"/" + RESOURCE;
	
	private double photoresistor;
	
	private PhotoresistorSensor(){
		init();
	}
	
	public static PhotoresistorSensor getInstance(){
		return photoresistorSensor;
	}
	
	private void init(){
		coapClient = new CoapClient();
		coapClient.setURI(coapIp);
		
		coapObserveRelation = coapClient.observe(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				String json = response.getResponseText();
				
				JSONObject jsonObject = new JSONObject(json);
				photoresistor = Double.parseDouble(jsonObject.getString("photoresistor"));
			}

			@Override
			public void onError() {
				System.out.println("PhotoresistorSensor - onError()");
			}
		});
	}

	public double getPhotoresistor() {
		return photoresistor;
	}
	
	public static void main(String[] args) {
		PhotoresistorSensor photoresistorSensor = PhotoresistorSensor.getInstance();
		
		while(true){
			System.out.println("" + photoresistorSensor.getPhotoresistor());
			try{Thread.sleep(1000);}catch(Exception e){}
		}
	}
}
