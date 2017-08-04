/**
 *
 * @author 2Team(Lee, Kang, Cho)
 */
package com.tomcatisbabycat.garfish;

import com.tomcatisbabycat.garfish.camera.publisher.CamPublisher;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GarfishMain {
	public static void main(String[] args) throws MalformedURLException {
		GarfishMqttClient garfishMqttClient= new GarfishMqttClient();
		garfishMqttClient.subscribe();
		garfishMqttClient.init();
		
		CamPublisher viewer = new CamPublisher(320, 240);
		viewer.setCurrentURL(new URL("http://localhost:50001/?action=stream"));
		
		System.out.println("start garfishMqttClient");
		
		try {
			System.in.read();
		} catch (IOException ex) {
		}
		garfishMqttClient.shutdown();
	}
}
