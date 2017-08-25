package com.mycompany.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
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
	public String control(@ModelAttribute Member login_info, Model model, String dmacaddr) {
		Drone drone = service.getDrone(dmacaddr);
		model.addAttribute("drone", drone);
		return "control";
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
	
	@RequestMapping("/deleteConfirm")
	public String deleteConfirm(@ModelAttribute Member login_info, Model model,String dmacaddr) {
		System.out.println(dmacaddr);
		model.addAttribute("dmacaddr", dmacaddr);
		
		return "deleteConfirm";
	}
	
	@RequestMapping("/deviceUpdate")
	public String deviceUpdate(@ModelAttribute Member login_info, Model model,String dmacaddr) {
		Drone drone = service.getDrone(dmacaddr);
		model.addAttribute("drone", drone);
		
		return "deviceUpdate";
	}
	
	@RequestMapping("/updateDevice")
	public String updateDevice(@ModelAttribute Member login_info, Drone drone) throws IllegalStateException, IOException {
		if (!drone.getDattach().isEmpty()) {
			drone.setDoriginalfilename(drone.getDattach().getOriginalFilename());
			drone.setDfilecontent(drone.getDattach().getContentType());
			String fileName = new Date().getTime() + "-" + drone.getDoriginalfilename();
			drone.setDsavedfilename(fileName);

			String realPath = servletContext.getRealPath("/WEB-INF/upload/");
			File file = new File(realPath + fileName);
			drone.getDattach().transferTo(file);
		}

		service.deviceUpdate(drone);
		return "redirect:/";
	}
	
	@RequestMapping("/updateMember")
	public String updateMember(@ModelAttribute Member login_info, Member member, Model model) throws IllegalStateException, IOException {
		if (!member.getMattach().isEmpty()) {
			member.setMoriginalfilename(member.getMattach().getOriginalFilename());
			member.setMfiletype(member.getMattach().getContentType());
			String fileName = new Date().getTime() + "-" + member.getMoriginalfilename();
			member.setMsavedfilename(fileName);

			String realPath = servletContext.getRealPath("/WEB-INF/upload/");
			File file = new File(realPath + fileName);
			member.getMattach().transferTo(file);
		}

		service.memberUpdate(member);
		member = service.getMember(member.getMid());
		model.addAttribute("login_info",member);
		return "redirect:/";
	}
	
	
	
	@RequestMapping("/deleteDevice")
	public String deleteDevice(@ModelAttribute Member login_info, Model model,String dmacaddr) {
		
		service.deleteDevice(dmacaddr);
		
		return "redirect:/";
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
