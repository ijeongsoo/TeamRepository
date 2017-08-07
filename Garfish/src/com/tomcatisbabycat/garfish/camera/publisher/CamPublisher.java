package com.tomcatisbabycat.garfish.camera.publisher;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class CamPublisher implements ImageChangeListener, Runnable {

	private CamStream camStream;
	private java.awt.Image imgAwt;
	private BufferedImage bufferedImage;
	private double width, height;

	private MqttClient mqttClient;
	private boolean stop;

	public CamPublisher() {
	}

	public CamPublisher(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setSize(double width, double height) {
		this.width = width;
		this.height = height;
	}

	public void setCurrentURL(URL mjpegStreamUrl) {
		if (camStream != null) {
			camStream.removeImageChangeListener(this);
			camStream.unhook();
		}

		camStream = new CamStream(mjpegStreamUrl, 1, 1000, false);
		camStream.addImageChangeListener(this);
		camStream.start();

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		java.awt.Image imgAwt;
		while (!stop) {
			try {
				imageChanged(null);
				Thread.sleep(50);
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void imageChanged(ImageChangeEvent ie) {
		if (mqttClient == null || !mqttClient.isConnected()) {
			try {
				mqttClient = new MqttClient("tcp://106.253.56.122:1883", MqttClient.generateClientId());

				mqttClient.connect();
			} catch (MqttException e) {
			}
		} else {
			imgAwt = camStream.getCurrent();
			if (imgAwt != null) {
				Thread thread = new Thread() {
					@Override
					public void run() {
						try {
							byte[] byteArray = toByteArray(imgAwt);
							/*
							//JavaFX(o), HTML(x)
							MqttMessage message = new MqttMessage(byteArray);
							message.setQos(0);
							mqttClient.publish("/camera", message);
							 */

							//JavaFX(o), HTML(o)
							String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(byteArray);
							MqttMessage message = new MqttMessage(b64.getBytes("UTF-8"));
							message.setQos(0);
							mqttClient.publish("/devices/drone/camera", message);
							System.out.println("test");
							Thread.sleep(2000);
							//System.out.println("Send Image");
						} catch (Exception e) {
						}
					}
				};
				thread.start();
			}
		}
	}

	public byte[] toByteArray(java.awt.Image image) throws IOException {
		if (bufferedImage == null) {
			bufferedImage = new BufferedImage((int) getWidth(), (int) getHeight(), BufferedImage.TYPE_INT_RGB);
		}
		Graphics2D g = bufferedImage.createGraphics();
		g.drawImage(image, 0, 0, (int) getWidth(), (int) getHeight(), 0, 0, image.getWidth(null), image.getHeight(null), null);
		g.dispose();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "jpg", baos);
		return baos.toByteArray();
	}

	public void shutdown() throws Exception {
		stop = true;
		camStream.removeImageChangeListener(this);
		camStream.unhook();
		mqttClient.disconnect();
		mqttClient.close();
	}

	public static void main(String[] args) throws MalformedURLException {
		CamPublisher viewer = new CamPublisher(320, 240);
		viewer.setCurrentURL(new URL("http://localhost:50001/?action=stream"));
	}
}
