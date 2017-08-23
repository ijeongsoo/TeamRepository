package com.tomcatisbabycat.garfish.hardware.camera;

import com.tomcatisbabycat.garfish.device.DeviceInfo;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class CamPublisher {
	
	private String serverURI;
	private String mqttBrokerURI;
	private String resourcePath;
	private boolean debug;
	
	private URL url;
	private HttpURLConnection httpURLConnection;
	private DataInputStream dataInputStream;
	private StreamSplit streamSplit;
	private String boundary;

	private MqttClient mqttClient;
	private Base64.Encoder encoder;
	
	private Thread thread;

	public CamPublisher(String serverURI, String mqttBrokerURI, String resourcePath, boolean debug) throws Exception {
		this.serverURI = serverURI;
		this.mqttBrokerURI = mqttBrokerURI;
		this.resourcePath = resourcePath;
		this.debug = debug;
		
		mqttClient = new MqttClient(mqttBrokerURI, MqttClient.generateClientId());
		mqttClient.connect();
		encoder = Base64.getEncoder();			
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				CamPublisher.this.stop();
			}
		});
	}

	public void start() throws Exception {		
		thread = new Thread() {
			@Override
			public void run() {
				try {
					url = new URL(serverURI);
					httpURLConnection = (HttpURLConnection) url.openConnection();
					httpURLConnection.connect();

					Hashtable httpHeaders = StreamSplit.readHeaders(httpURLConnection);		
					
					if(debug) {
						System.err.println("<HTTP Header>");
						Set<String> kes = httpHeaders.keySet();
						for(String key : kes) {
							System.out.println(key + " = " + httpHeaders.get(key));
						}
					}						
					
					String contentType = (String) httpHeaders.get("content-type");
					int bidx = contentType.indexOf("boundary=");
					boundary = "--" + contentType.substring(bidx + 9);				
					
					dataInputStream = new DataInputStream(new BufferedInputStream(httpURLConnection.getInputStream()));
					streamSplit = new StreamSplit(dataInputStream);						
					streamSplit.skipToBoundary(boundary);
					
					if (debug) {
						Thread.sleep(500);
						System.err.println("<HTTP Body>");
					}
					
					while (true) {
						if (isInterrupted()) {
							break;
						}
						
						Hashtable partHeaders = streamSplit.readHeaders();
						byte[] imageBytes = streamSplit.readToBoundary(boundary);
						
						if (debug) {
							System.out.println(boundary);
							for (Enumeration enm = partHeaders.keys(); enm.hasMoreElements();) {
								String hkey = (String) enm.nextElement();
								System.out.println(hkey + " = " + partHeaders.get(hkey));
							}
							System.out.println();
							for(int i=0; i<50; i++) {
								System.out.print(imageBytes[i]);
							}
							System.out.println();
						}
						String base64 = encoder.encodeToString(imageBytes);
						MqttMessage message = new MqttMessage(base64.getBytes("UTF-8"));
						message.setQos(0);
						mqttClient.publish(resourcePath, message);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				CamPublisher.this.stop();
			}
		};
		thread.start();
	}
	
	public void stop() {
		try {
			thread.interrupt();
			mqttClient.disconnect();
			mqttClient.close();
		} catch(Exception e) {}
	}

	public static void main(String[] args) throws Exception {
		CamPublisher camPublisher = new CamPublisher("http://127.0.0.1:50001/?action=stream", "tcp://106.253.56.122:1883", "/camera", true);
		camPublisher.start();
	}
}
