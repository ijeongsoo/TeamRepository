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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mycompany.myapp.dto.Member;

import com.mycompany.myapp.service.Service;

@Controller

public class LoginJoinController {
	private static final Logger logger = LoggerFactory.getLogger(LoginJoinController.class);

	@Autowired
	private Service service;
	
	@Autowired
	private ServletContext servletContext;

	
	
	//로그인
//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	public String login(@ModelAttribute Member login_info, String mid, String mpassword, Model model) {
//		Member member = service.login(mid, mpassword);
//		if (member == null) {
//			model.addAttribute("result", 0);
//			return "home";
//		} else {
//			model.addAttribute("login_info", member);
//			return "redirect:loginHome";
//		}
//	}
	

	
	
	//회원가입

	
	
	
	@RequestMapping(value = "/joinPost", method = RequestMethod.POST)
	public String joinPost(@ModelAttribute Member login_info, Member member)
			throws IllegalStateException, IOException {
		member.setMoriginalfilename(member.getMattach().getOriginalFilename());
		member.setMfileType(member.getMattach().getContentType());
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
	

	

}
