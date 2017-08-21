package com.mycompany.myapp.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.myapp.dto.Drone;
import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.service.Service;

@Controller
@SessionAttributes("login_info")
public class NeedSessionController {
	private static final Logger logger = LoggerFactory.getLogger(NeedSessionController.class);



	@Autowired
	private Service service;
	
	@Autowired
	private ServletContext servletContext;

	

	@RequestMapping("/logout")
	public String logout(SessionStatus sessionStatus) {
		// 세션에 있는 모든 정보를 삭제
		// @SessionAttributes를 사용할 경우 이용
		sessionStatus.setComplete();
		return "redirect:/";
	}


	@RequestMapping("/control")
	public String control(@ModelAttribute Member login_info) {
		return "controlPage";
	}

	@RequestMapping("/loginHome")
	public String loginHome(@ModelAttribute Member login_info, Model model,String mid) {
		List<Drone> allList = service.allList(mid);
		List<Drone> droneList = service.droneList(mid);
		List<Drone> roverList = service.roverList(mid);
		List<Drone> planeList = service.planeList(mid);
		model.addAttribute("allList", allList);
		model.addAttribute("droneList", droneList);
		model.addAttribute("roverList", roverList);
		model.addAttribute("planeList", planeList);
		return "loginHome";
	}




//	@RequestMapping("/realHome")
//	public String jspTest(Model model) throws MqttException {
//		JSONObject jsonObject = null;
//		String json = null;
//
//		MqttClient statusMqttClient = new MqttClient("tcp://192.168.0.2:1883", MqttClient.generateClientId());
//
//		MqttCallback statusMqttCallback = new MqttCallback() {
//			@Override
//			public void connectionLost(Throwable thrwbl) {
//			}
//
//			@Override
//			public void messageArrived(String string, MqttMessage mm) throws Exception {
//
//				testJson = mm.toString();
//				flag = false;
//
//				statusMqttClient.disconnect();
//				statusMqttClient.close();
//			}
//
//			@Override
//			public void deliveryComplete(IMqttDeliveryToken imdt) {
//				System.out.println("deliver" + "yComplete:" + new Date());
//			}
//		};
//
//		statusMqttClient.setCallback(statusMqttCallback);
//
//		statusMqttClient.connect();
//
//		jsonObject = new JSONObject();
//		jsonObject.put("pwmCheck", "status");
//		String reqJson = jsonObject.toString();
//
//		MqttMessage message = new MqttMessage(reqJson.getBytes());
//		// MQTT 브로커로 메시지 보냄
//		statusMqttClient.subscribe("/devices/drone/pwm");
//		statusMqttClient.publish("/devices/drone/pwmCheck", message);
//
//		while (flag) {
//			System.out.println("wailt");
//		}
//		System.out.println("완료");
//		flag = false;
//		System.out.println(testJson);
//		JSONObject jsonObject2 = new JSONObject(testJson);
//		model.addAttribute("throttle", jsonObject2.getInt("throttle"));
//		model.addAttribute("yaw", jsonObject2.getInt("yaw"));
//		model.addAttribute("pitch", jsonObject2.getInt("pitch"));
//		model.addAttribute("roll", jsonObject2.getInt("roll"));
//		model.addAttribute("mode", jsonObject2.getInt("mode"));
//
//		return "realHome";
//
//	}

	

}
