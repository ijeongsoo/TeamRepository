package com.tomcatisbabycat.garfish.camera.subscriber;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.Base64;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import sun.awt.image.IntegerComponentRaster;

public class ViewerCanvas extends Canvas {
	private GraphicsContext gc;
	private MqttClient mqttClient;
	
	public ViewerCanvas(int width, int height) throws Exception {
		super(width, height);
		gc = getGraphicsContext2D();
		Base64.Decoder base64Decoder = Base64.getDecoder();

<<<<<<< HEAD
		mqttClient = new MqttClient("tcp://192.168.0.2:1883", MqttClient.generateClientId());
=======
		mqttClient = new MqttClient("tcp://106.253.56.122:1883", MqttClient.generateClientId());
>>>>>>> origin/master
		mqttClient.setCallback(new MqttCallback() {
			//메시지를 수신할 때 호출(구독자일 경우)
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							/*
							byte[] byteArray = message.getPayload();
							java.awt.Image imgAwt = ImageIO.read(new ByteArrayInputStream(byteArray));
							Image imgFx = createFXImage(imgAwt);
							gc.drawImage(imgFx, 0, 0);
							*/
							byte[] byteArray = base64Decoder.decode(message.toString());
							java.awt.Image imgAwt = ImageIO.read(new ByteArrayInputStream(byteArray));
							Image imgFx = createFXImage(imgAwt);
							gc.drawImage(imgFx, 0, 0);
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				});
			}

			@Override
			public void deliveryComplete(IMqttDeliveryToken token) {
			}

			@Override
			public void connectionLost(Throwable cause) {
			}
		});
		mqttClient.connect();
		mqttClient.subscribe("/devices/drone/camera");
	}
	
	public Image createFXImage(java.awt.Image image) throws IOException {
		BufferedImage bufferedImage = new BufferedImage((int) getWidth(), (int) getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = bufferedImage.createGraphics();
		g.drawImage(image, 0, 0, (int) getWidth(), (int) getHeight(), 0, 0, image.getWidth(null), image.getHeight(null), null);
		g.dispose();

		int bw = bufferedImage.getWidth();
		int bh = bufferedImage.getHeight();
		
		WritableImage wimg = new WritableImage(bw, bh);
		PixelWriter pw = wimg.getPixelWriter();
		IntegerComponentRaster icr = (IntegerComponentRaster) bufferedImage.getRaster();
		int data[] = icr.getDataStorage();
		int offset = icr.getDataOffset(0);
		int scan = icr.getScanlineStride();
		
		PixelFormat<IntBuffer> pf = (bufferedImage.isAlphaPremultiplied()
			? PixelFormat.getIntArgbPreInstance()
			: PixelFormat.getIntArgbInstance());
		pw.setPixels(0, 0, bw, bh, pf, data, offset, scan);
		return wimg;
	}
	
	//연결 끊기
	public void shutdown() throws Exception {
		mqttClient.disconnect();
		mqttClient.close();
	}
}
