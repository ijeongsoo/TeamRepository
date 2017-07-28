/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.garfish;

import java.io.IOException;

/**
 *
 * @author ijeongsu
 */
public class GarfishMain {
	public static void main(String[] args) {
		GarfishMqttClient garfishMqttClient= new GarfishMqttClient();
		garfishMqttClient.subscribe();
		garfishMqttClient.init();
		
		
		System.out.println("start garfishMqttClient");
		///////////////////////////////////////////////////
		
		
		
		
		///////////////////////////////////////////////////
		System.out.println("press any key to quit");
		
		
		try {
			System.in.read();
		} catch (IOException ex) {
		}
		
		garfishMqttClient.shutdown();
	}
}
