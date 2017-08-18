package com.mycompany.myapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.dto.PasswdChange;
import com.mycompany.myapp.service.Service;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private MqttClient mqttClient;
	private MqttCallback mqttCallback;
	private boolean flag = true;
	private String testJson = null;

	@Autowired
	private Service service;

	@PostConstruct
	public void init() throws MqttException {
		logger.info("init실행");
		mqttClient = new MqttClient("tcp://106.253.56.122:1883", MqttClient.generateClientId());

		mqttCallback = new MqttCallback() {
			@Override
			public void connectionLost(Throwable thrwbl) {
			}

			@Override
			public void messageArrived(String string, MqttMessage mm) throws Exception {

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
		return "root";
	}

	@RequestMapping("/join")
	public String join() {
		return "join";
	}

	@RequestMapping("/control")
	public String control() {
		return "controlPage";
	}

	@RequestMapping("/loginHome")
	public String loginHome() {
		return "loginHome";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(String mid, String mpassword, Model model) {
		logger.info("aaa");
		logger.info(mpassword);
		Member member = service.login(mid, mpassword);
		if (member == null) {
			model.addAttribute("result", 0);
			return "loginFail";
		} else if (member.getMid() == null) {
			model.addAttribute("result", 1);
			return "loginFail";
		} else {
			model.addAttribute("login_info", member);
			return "redirect:loginHome";
		}
	}

	@RequestMapping(value = "passwdChange", method = RequestMethod.POST)
	public void passwdChange(String reset_mid, HttpServletResponse response) {
		logger.info(reset_mid);

		JSONObject jsonObject = new JSONObject();
		
		Member member = service.joinCheckID(reset_mid);
		if(member==null){
			jsonObject.put("result", "fail");

			String json = jsonObject.toString();

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			try {
				pw = response.getWriter();
				pw.write(json);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			PasswdChange passwdChange = service.selectLinkByMid(reset_mid);
			if (passwdChange != null) {

				jsonObject.put("result", "already");

				String json = jsonObject.toString();

				response.setContentType("application/json; charset=UTF-8");
				PrintWriter pw;

				try {
					pw = response.getWriter();
					pw.write(json);
					pw.flush();
					pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				String time = String.valueOf(System.currentTimeMillis());
				String SHA = "";
				try {
					MessageDigest sh = MessageDigest.getInstance("SHA-256");
					sh.update(time.getBytes());
					byte byteData[] = sh.digest();
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < byteData.length; i++) {
						sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
					}
					SHA = sb.toString();

				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
					SHA = null;
				}

				PasswdChange passwdChange2 = new PasswdChange();
				passwdChange2.setMid(reset_mid);
				passwdChange2.setPlink(SHA);

				service.savePasswdChangeLink(passwdChange2);

				String host = "smtp.gmail.com";
				final String username = "quintessence6083";
				final String password = "54r54r!@#";
				int port = 465;

				String recipient = reset_mid;
				String subject = " Garfish사이트의" + reset_mid + "님의 비밀번호 변경 링크입니다.";
				String body = "링크 : http://localhost:8080/Team2DroneWebControl/updatePasswd/?link=" + SHA + "&mid="
						+ reset_mid;
				Properties props = System.getProperties();
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", port);
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.ssl.enable", "true");
				props.put("mail.smtp.ssl.trust", host);

				Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
					String un = username;
					String pw = password;

					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new javax.mail.PasswordAuthentication(un, pw);
					}
				});
				session.setDebug(true);
				Message mimeMessage = new MimeMessage(session);
				try {
					mimeMessage.setFrom(new InternetAddress("quintessence6083@gmail.com"));
					mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

					mimeMessage.setSubject(subject);
					mimeMessage.setText(body);
					Transport.send(mimeMessage);

					try {
						jsonObject = new JSONObject();
						jsonObject.put("result", "sussece");

						String json = jsonObject.toString();

						response.setContentType("application/json; charset=UTF-8");
						PrintWriter pw;

						pw = response.getWriter();
						pw.write(json);
						pw.flush();
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		
		

	}

	@RequestMapping("/contact_me")
	public void contact_me(String name, String phone, String email, String message, HttpServletResponse response) {
		String host = "smtp.gmail.com";
		final String username = "quintessence6083";
		final String password = "54r54r!@#";
		int port = 465;

		String recipient = "quintessence6083@gmail.com";
		String subject = name + "님의 문의사항";
		String body = "이름 :" + name + " 전화번호 :" + phone + " 메일 :" + email + " 문의 사항:" + message;
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un = username;
			String pw = password;

			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(un, pw);
			}
		});
		session.setDebug(true);
		Message mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(new InternetAddress("quintessence6083@gmail.com"));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

			mimeMessage.setSubject(subject);
			mimeMessage.setText(body);
			Transport.send(mimeMessage);

			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("result", "sussece");

				String json = jsonObject.toString();

				response.setContentType("application/json; charset=UTF-8");
				PrintWriter pw;

				pw = response.getWriter();
				pw.write(json);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/realHome")
	public String jspTest(Model model) throws MqttException {
		JSONObject jsonObject = null;
		String json = null;

		MqttClient statusMqttClient = new MqttClient("tcp://192.168.0.2:1883", MqttClient.generateClientId());

		MqttCallback statusMqttCallback = new MqttCallback() {
			@Override
			public void connectionLost(Throwable thrwbl) {
			}

			@Override
			public void messageArrived(String string, MqttMessage mm) throws Exception {

				testJson = mm.toString();
				flag = false;

				statusMqttClient.disconnect();
				statusMqttClient.close();
			}

			@Override
			public void deliveryComplete(IMqttDeliveryToken imdt) {
				System.out.println("deliver" + "yComplete:" + new Date());
			}
		};

		statusMqttClient.setCallback(statusMqttCallback);

		statusMqttClient.connect();

		jsonObject = new JSONObject();
		jsonObject.put("pwmCheck", "status");
		String reqJson = jsonObject.toString();

		MqttMessage message = new MqttMessage(reqJson.getBytes());
		// MQTT 브로커로 메시지 보냄
		statusMqttClient.subscribe("/devices/drone/pwm");
		statusMqttClient.publish("/devices/drone/pwmCheck", message);

		while (flag) {
			System.out.println("wailt");
		}
		System.out.println("완료");
		flag = false;
		System.out.println(testJson);
		JSONObject jsonObject2 = new JSONObject(testJson);
		model.addAttribute("throttle", jsonObject2.getInt("throttle"));
		model.addAttribute("yaw", jsonObject2.getInt("yaw"));
		model.addAttribute("pitch", jsonObject2.getInt("pitch"));
		model.addAttribute("roll", jsonObject2.getInt("roll"));
		model.addAttribute("mode", jsonObject2.getInt("mode"));

		return "realHome";

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
		mqttClient.publish("/devices/drone/cameraServo", message);

	}

}
