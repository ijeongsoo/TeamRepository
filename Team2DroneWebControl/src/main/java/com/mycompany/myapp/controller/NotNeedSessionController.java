package com.mycompany.myapp.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.myapp.dto.Drone;
import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.dto.PasswdChange;
import com.mycompany.myapp.service.Service;

@Controller
@SessionAttributes("login_info")
public class NotNeedSessionController {
	private static final Logger logger = LoggerFactory.getLogger(NotNeedSessionController.class);

	@Autowired
	private Service service;
	
	@Autowired
	private ServletContext servletContext;

	

	@RequestMapping("/")
	public String home(@ModelAttribute Member login_info) {
		return "root";
	}

	@RequestMapping("/join")
	public String join(@ModelAttribute Member login_info) {
		return "join";
	}

	@RequestMapping(value = "/joinPost", method = RequestMethod.POST)
	public String joinPost(@ModelAttribute Member login_info, Member member)
			throws IllegalStateException, IOException {
		member.setMoriginalfilename(member.getMattach().getOriginalFilename());
		member.setMfiletype(member.getMattach().getContentType());
		String fileName = new Date().getTime() + "-" + member.getMoriginalfilename();
		member.setMsavedfilename(fileName);
		
		String realPath = servletContext.getRealPath("/WEB-INF/upload/");
		File file = new File(realPath +"/"+ fileName);
		member.getMattach().transferTo(file);
		service.memberJoin(member);

		return "redirect:/";
	}

	@RequestMapping("/check_id")
	public String checkID( @ModelAttribute Member login_info, String mid, Model model) {
		logger.info(mid);
		int result = service.joinCheckID(mid);
		model.addAttribute("result", result);
		return "checkID";
	}
	

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute Member login_info, String mid, String mpassword, Model model) {
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
			return "redirect:loginHome?mid="+member.getMid();
		}
	}

	
	
	@RequestMapping("/manual")
	public String manual(@ModelAttribute Member login_info) {
		return "manual";
	}
	
	

}
