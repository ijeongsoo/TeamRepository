package com.mycompany.myapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.myapp.service.Service;

@Controller
public class AnywayController {
	private static final Logger logger = LoggerFactory.getLogger(SessionPageController.class);
	
	@Autowired
	private Service service;
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/check_comunication")
	public void checkComunication(String sip, String command, HttpServletResponse response) {
		try {
			int result = 0;
			int comunicationResult = service.checkCoumunication(sip, command);
			if (comunicationResult == 1) {
				result = 1;
			} else {
				result = 0;
			}

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", result);

			String json = jsonObject.toString();

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			pw = response.getWriter();

			pw.write(json);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.info(sip+"연결상태 불량");
		}
	}
	
	@RequestMapping("/file")
	public void download(String ssavedfilename, String sfilecontent, HttpServletResponse r,
			@RequestHeader("User-Agent") String userAgent) throws IOException {

		String fileName = ssavedfilename;
		String encFileName;
		if (userAgent.contains("MSIE") || userAgent.contains("Trident") || userAgent.contains("Edge")) {
			encFileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			encFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		r.addHeader("Content-Disposition", "attachment; filename=\"" + encFileName + "\"");
		// 2. 파일의 종류
		r.addHeader("Content-Type", sfilecontent);
		// 3. 파일의 크기(옵션)
		r.addHeader(fileName, fileName);
		File file = null;

		file = new File(servletContext.getRealPath("/WEB-INF/upload/" + fileName));

		long fileSize = file.length();
		r.addHeader("Content-Length", String.valueOf(fileSize));

		OutputStream os = r.getOutputStream();
		FileInputStream fis = new FileInputStream(file);
		FileCopyUtils.copy(fis, os);
		os.flush();
		fis.close();
		os.close();

	}
	
	@RequestMapping("/check_webserver_comunication")
	public void checkWebserverComunication(String startTime, HttpServletResponse response) {
		try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("startTime", startTime);
				String json = jsonObject.toString();
				response.setContentType("application/json; charset=UTF-8");
				PrintWriter pw;

				pw = response.getWriter();

				pw.write(json);
				pw.flush();
				pw.close();

		} catch (IOException e) {
		}
	}
	
	@RequestMapping("/thermistorSensor")
	public void thermistorSensor(String sip, String command, HttpServletResponse response) {
		try {
			String result = service.thermistorSensor(sip, command);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			pw = response.getWriter();

			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.info(sip+"연결상태 불량");
		}
	}
	
	@RequestMapping("/photoresistorSensor")
	public void photoresistorSensor(String sip, String command, HttpServletResponse response) {
		try {
			String result = service.photoresistorSensor(sip, command);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			pw = response.getWriter();

			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.info(sip+"연결상태 불량");
		}
	}
	
	@RequestMapping("/gasSensor")
	public void gasSensor(String sip, String command, HttpServletResponse response) {
		try {
			String result = service.gasSensor(sip, command);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			pw = response.getWriter();

			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.info(sip+"연결상태 불량");
		}
	}
	
	@RequestMapping("/ultrasonicSensor")
	public void ultrasonicSensor(String sip, String command,String angle, HttpServletResponse response) {
		try {
			String result = service.ultrasonicSensor(sip, command, angle);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			pw = response.getWriter();

			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.info(sip+"연결상태 불량");
		}
	}
	
	@RequestMapping("/trackingSensor")
	public void trackingSensor(String sip, String command, HttpServletResponse response) {
		try {
			String result = service.trackingSensor(sip, command);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			pw = response.getWriter();

			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.info(sip+"연결상태 불량");
		}
	}
	
	@RequestMapping("/laserEmitter")
	public void laserEmitter(String sip, String command, String status, HttpServletResponse response) {
		try {
			String result = service.laserEmitter(sip, command, status);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			pw = response.getWriter();

			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.info(sip+"연결상태 불량");
		}
	}
	
	@RequestMapping("/buzzer")
	public void buzzer(String sip, String command, String status, HttpServletResponse response) {
		try {
			String result = service.buzzer(sip, command, status);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			pw = response.getWriter();

			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.info(sip+"연결상태 불량");
		}
	}
	@RequestMapping("/rgbLed")
	public void rgbLed(String sip, String command, String red, String green, String blue, HttpServletResponse response) {
		try {
			String result = service.rgbLed(sip, command, red, green, blue);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			pw = response.getWriter();

			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.info(sip+"연결상태 불량");
		}
	}
	
	@RequestMapping("/backTire")
	public void backTire(String sip, String command, String speed, String direction, HttpServletResponse response) {
		try {
			String result = service.backTire(sip, command, speed, direction);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			pw = response.getWriter();

			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.info(sip+"연결상태 불량");
		}
	}
	
	@RequestMapping("/frontTire")
	public void frontTire(String sip, String command, String angle, HttpServletResponse response) {
		try {
			String result = service.frontTire(sip, command,angle);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			pw = response.getWriter();

			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.info(sip+"연결상태 불량");
		}
	}
	
	@RequestMapping("/camera")
	public void camera(String sip, String command, String leftRight, String upDown, HttpServletResponse response) {
		try {
			String result = service.camera(sip, command, leftRight, upDown);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			pw = response.getWriter();

			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.info(sip+"연결상태 불량");
		}
	}
	
	@RequestMapping("/lcd")
	public void lcd(String sip, String command, String line0, String line1, HttpServletResponse response) {
		try {
			String result = service.lcd(sip, command, line0, line1);

			response.setContentType("application/json; charset=UTF-8");
			PrintWriter pw;

			pw = response.getWriter();

			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.info(sip+"연결상태 불량");
		}
	}
}
