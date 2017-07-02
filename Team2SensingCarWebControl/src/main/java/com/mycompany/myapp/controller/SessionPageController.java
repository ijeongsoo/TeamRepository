package com.mycompany.myapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.dto.Sensingcar;
import com.mycompany.myapp.service.Service;

@Controller
@SessionAttributes("login_info")
public class SessionPageController {
	private static final Logger logger = LoggerFactory.getLogger(SessionPageController.class);

	@Autowired
	private Service service;

	@Autowired
	private ServletContext servletContext;

	@RequestMapping("/logout")
	public String logout(SessionStatus sessionStatus) {
		// 세션에 있는 모든 정보를 삭제
		// @SessionAttributes를 사용할 경우 이용
		sessionStatus.setComplete();
		return "home";
	}

	@RequestMapping("/check_ip")
	public String checkIP(@ModelAttribute Member login_info,String sip, String command, Model model) {
		int result = 0;
		int duplicateResult = service.registCheckIP(sip);
		int comunicationResult = service.checkCoumunication(sip, command);
		if (duplicateResult == 1 && comunicationResult == 1) {
			result = 1;
		} else if (duplicateResult == 0) {
			result = 0;
		} else if (comunicationResult == 0) {
			result = -1;
		}

		model.addAttribute("result", result);
		return "checkIP";

	}

	

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(@ModelAttribute Member login_info,Sensingcar sensingcar) throws IllegalStateException, IOException {
		sensingcar.setSoriginalfilename(sensingcar.getSattach().getOriginalFilename());
		sensingcar.setSfilecontent(sensingcar.getSattach().getContentType());
		String fileName = new Date().getTime() + "-" + sensingcar.getSoriginalfilename();
		sensingcar.setSsavedfilename(fileName);

		String realPath = servletContext.getRealPath("/WEB-INF/upload/");
		File file = new File(realPath + fileName);
		sensingcar.getSattach().transferTo(file);
		logger.info(sensingcar.getSname());
		service.sensingcarRegist(sensingcar);

		return "redirect:loginHome";
	}

	@RequestMapping("/loginHome")
	public String loginHome(@ModelAttribute Member login_info, Model model) {

		List<Sensingcar> list = service.sensingcarListAll();
		model.addAttribute("list", list);
		return "loginHome";
	}
	
	@RequestMapping("/control")
	public String control(@ModelAttribute Member login_info, Model model, String sip) {
		
		Sensingcar sensingcar = service.getSensingcar(sip);
		model.addAttribute("sensingcar", sensingcar);
		model.addAttribute("cameraUrl", "http://"+sip+":50001?action=stream");
		return "controlPage";
	}

	
	@RequestMapping("/remove")
	public String remove(@ModelAttribute Member login_info, int sno) {
		service.sensingcarRemove(sno);
		return "redirect:loginHome";
	}

	
}
