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
public class GasSensor {
	private static GasSensor gasSensor = new GasSensor();
	
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;
	private static final String IP = "192.168.3.40";
	private static final String RESOURCE = "gassensor";
	private String coapIp = "coap://"+ IP +"/" + RESOURCE;
	
	private double gas;
	
	private GasSensor(){
		init();
	}
	
	public static GasSensor getInstance(){
		return gasSensor;
	}
	
	private void init(){
		coapClient = new CoapClient();
		coapClient.setURI(coapIp);
		
		coapObserveRelation = coapClient.observe(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				String json = response.getResponseText();
				
				JSONObject jsonObject = new JSONObject(json);
				gas = Double.parseDouble(jsonObject.getString("gas"));
			}

			@Override
			public void onError() {
				System.out.println("GasSensor - onError()");
			}
		});
	}

	public double getGas() {
		return gas;
	}
	
	public static void main(String[] args) {
		GasSensor gasSensor = GasSensor.getInstance();
		
		while(true){
			System.out.println("" + gasSensor.getGas());
			try{Thread.sleep(1000);}catch(Exception e){}
		}
	}
}
