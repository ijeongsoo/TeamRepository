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
public class TrackingSensor {
	private static TrackingSensor trackingSensor = new TrackingSensor();
	
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;
	private static final String IP = "192.168.3.40";
	private static final String RESOURCE = "tracking";
	private String coapIp = "coap://"+ IP +"/" + RESOURCE;
	
	private String responseTrackingString;
	
	private int tracking;
	
	private TrackingSensor(){
		init();
	}
	
	public static TrackingSensor getInstance(){
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
				responseTrackingString = jsonObject.getString("tracking");
				
				tracking = 1;
				
				if(responseTrackingString.equals("white")){
					tracking = 0;
				}
			}

			@Override
			public void onError() {
				System.out.println("TrackingSensor - onError()");
			}
		});
	}

	public int getTracking() {
		return tracking;
	}
	
	public static void main(String[] args) {
		TrackingSensor trackingSensor = TrackingSensor.getInstance();
		
		while(true){
			System.out.println("" + trackingSensor.getTracking());
			try{Thread.sleep(1000);}catch(Exception e){}
		}
	}
}
