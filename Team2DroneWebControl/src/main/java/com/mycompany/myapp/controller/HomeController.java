package com.mycompany.myapp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.File;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private MqttClient mqttClient;
	private MqttCallback mqttCallback;

	@PostConstruct
	public void init() throws MqttException {
		logger.info("init실행");
		mqttClient = new MqttClient("tcp://192.168.0.2:1883", MqttClient.generateClientId());

		mqttCallback = new MqttCallback() {
			@Override
			public void connectionLost(Throwable thrwbl) {
			}

			@Override
			public void messageArrived(String string, MqttMessage mm) throws Exception {
				String ms = mm.toString();
				byte[] mb = ms.getBytes();
				byte[] mbb = Base64.getDecoder().decode(mb);
				
				
			}

			@Override
			public void deliveryComplete(IMqttDeliveryToken imdt) {
				System.out.println("deliver" + "yComplete:" + new Date());
			}
		};

		mqttClient.setCallback(mqttCallback);

		mqttClient.connect();
		mqttClient.subscribe("/devices/drone/camera");
		logger.info("init실행");
	}

	@PreDestroy
	public void destroy() throws MqttException {
		logger.info("destroy실행");
		mqttClient.disconnect();
		mqttClient.close();
	}

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/control")
	public String control() {
		return "controlPage";
	}

	@RequestMapping("/realHome")
	public String jspTest() {
		return "realHome";
	}

	@RequestMapping("/camera2")
	public void camera(HttpServletResponse r, @RequestHeader("User-Agent") String userAgent)
			throws IOException, MqttException {
		MqttClient mclient = new MqttClient("tcp://192.168.0.2:1883", MqttClient.generateClientId());
		MqttCallback mcallback = new MqttCallback() {
			@Override
			public void connectionLost(Throwable thrwbl) {
			}

			@Override
			public void messageArrived(String string, MqttMessage mm) throws Exception {
				String ms = mm.toString();
				byte[] mb = ms.getBytes();
				byte[] mbb = Base64.getDecoder().decode(mb);
				System.out.println(new String(mbb));
				
				r.addHeader("Content-Type", "image/jpg");
				OutputStream os = r.getOutputStream();
				os.write(mbb);
				os.flush();
				//os.close();
			}

			@Override
			public void deliveryComplete(IMqttDeliveryToken imdt) {
				System.out.println("deliver" + "yComplete:" + new Date());
			}
		};

		mclient.setCallback(mcallback);

		mclient.connect();
		mclient.subscribe("/devices/drone/camera");
	}

	@RequestMapping("/throttleAndYaw")
	public void throttleAndYaw(String throttle, String yaw, HttpServletResponse response) throws MqttException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("throttle", throttle);
		jsonObject.put("yaw", yaw);

		String reqJson = jsonObject.toString();

		MqttMessage message = new MqttMessage(reqJson.getBytes());
		// MQTT 브로커로 메시지 보냄
		mqttClient.publish("/devices/drone/throttleAndYaw", message);

	}

	@RequestMapping("/pitchAndRoll")
	public void pitchAndRoll(String pitch, String roll, HttpServletResponse response) throws MqttException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("pitch", pitch);
		jsonObject.put("roll", roll);

		String reqJson = jsonObject.toString();

		MqttMessage message = new MqttMessage(reqJson.getBytes());
		// MQTT 브로커로 메시지 보냄
		mqttClient.publish("/devices/drone/pitchAndRoll", message);

	}

	@RequestMapping("/mode")
	public void pitchAndRoll(String mode, HttpServletResponse response) throws MqttException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("mode", mode);

		String reqJson = jsonObject.toString();

		MqttMessage message = new MqttMessage(reqJson.getBytes());
		// MQTT 브로커로 메시지 보냄
		mqttClient.publish("/devices/drone/mode", message);

	}

	@RequestMapping("/camera")
	public void camera(String leftRight, String upDown, HttpServletResponse response) throws MqttException {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("leftRight", leftRight);
		jsonObject.put("upDown", upDown);

		String reqJson = jsonObject.toString();

		MqttMessage message = new MqttMessage(reqJson.getBytes());
		// MQTT 브로커로 메시지 보냄
		mqttClient.publish("/devices/drone/camera", message);

	}

}
