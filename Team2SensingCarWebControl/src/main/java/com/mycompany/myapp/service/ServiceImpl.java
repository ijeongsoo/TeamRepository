package com.mycompany.myapp.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Dao;
import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.dto.Sensingcar;

@Component
public class ServiceImpl implements Service {
	private static final Logger logger = LoggerFactory.getLogger(ServiceImpl.class);
	@Autowired
	private Dao dao;

	@Override
	public int joinCheckID(String mid) {
		// TODO Auto-generated method stub
		int result = 0;
		int count = dao.memberExistSelectByMid(mid);

		if (count == 0) {
			result = 1;
		}
		return result;
	}

	@Override
	public void memberJoin(Member member) {
		dao.memberInsert(member);
	}

	@Override
	public Member login(String mid, String mpassword) {
		logger.info(mpassword);
		Member member = dao.memberSelectByMid(mid);
		if (member == null) {
			return null;
		} else {
			if (member.getMpassword().equals(mpassword)) {
				return member;
			} else {
				return null;
			}
		}

	}

	@Override
	public Member getMember(String mid) {
		Member member = dao.memberSelectByMid(mid);
		return member;
	}

	@Override
	public int registCheckIP(String sip) {
		int result = 0;
		int count = dao.sensingcarExistSelectBySip(sip);

		if (count == 0) {
			result = 1;
		}
		return result;
	}

	@Override
	public int checkCoumunication(String sip, String command) {
		int result = 1;

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);

		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + sip + "/lcd");

		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);

		if (coapResponse == null) {
			result = 0;
			coapClient.shutdown();
		}

		return result;

	}

	@Override
	public void sensingcarRegist(Sensingcar sensingcar) {
		// TODO Auto-generated method stub
		dao.sensingcarInsert(sensingcar);
	}

	@Override
	public List<Sensingcar> sensingcarListAll() {
		List<Sensingcar> list = dao.sensingcarSelectAll();
		return list;
	}

	@Override
	public Sensingcar getSensingcar(String sip) {
		Sensingcar sensingcar = dao.sensingcarSelectBySip(sip);
		return sensingcar;
	}

	@Override
	public void sensingcarRemove(int sno) {
		dao.sensingcarRemoveBySno(sno);
	}
	
	
	@Override
	public String thermistorSensor(String sip, String command) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);

		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + sip + "/thermistorsensor");

		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);

		String json = coapResponse.getResponseText();
		
		jsonObject = new JSONObject(json);
		String temp = jsonObject.getString("temperature");
		double tempD = Double.parseDouble(temp);
		double tempDD= Math.round(tempD*10d) / 10d;
		String result = String.valueOf(tempDD);
		jsonObject = new JSONObject();
		jsonObject.put("temperature", result);
		json=jsonObject.toString();
		coapClient.shutdown();
		
		return json;

	}
	
	@Override
	public String photoresistorSensor(String sip, String command) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);

		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + sip + "/photoresistor");

		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);

		String json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		String temp = jsonObject.getString("photoresistor");
		double tempD = Double.parseDouble(temp);
		int tempI= (int)tempD;
		String result = String.valueOf(tempI);
		jsonObject = new JSONObject();
		jsonObject.put("photoresistor", result);
		json=jsonObject.toString();
		coapClient.shutdown();
		
		return json;
	}

	@Override
	public String gasSensor(String sip, String command) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);

		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + sip + "/gassensor");

		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);

		String json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		String temp = jsonObject.getString("gas");
		double tempD = Double.parseDouble(temp);
		int tempI= (int)tempD;
		String result = String.valueOf(tempI);
		jsonObject = new JSONObject();
		jsonObject.put("gas", result);
		json=jsonObject.toString();
		coapClient.shutdown();
		
		return json;
	}
	
	@Override
	public String ultrasonicSensor(String sip, String command) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);

		String reqJson = jsonObject.toString();
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + sip + "/ultrasonicsensor");

		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);

		String json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		String temp = jsonObject.getString("distance");
		double tempD = Double.parseDouble(temp);
		int tempI= (int)tempD;
		String result = String.valueOf(tempI);
		jsonObject = new JSONObject();
		jsonObject.put("distance", result);
		json=jsonObject.toString();
		coapClient.shutdown();
		
		return json;
	}
	
	@Override
	public String trackingSensor(String sip, String command) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);

		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + sip + "/tracking");

		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);

		String json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		String temp = jsonObject.getString("tracking");
		if(temp.equals("black")){
			temp="0";
		}else{
			temp="1";
		}
		jsonObject = new JSONObject();
		jsonObject.put("tracking", temp);
		json=jsonObject.toString();
		coapClient.shutdown();
		
		return json;
	}
	
	@Override
	public String laserEmitter(String sip, String command, String status) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("status", status);

		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + sip + "/laseremitter");

		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);

		String json = coapResponse.getResponseText();
		coapClient.shutdown();
		
		return json;
	}
	
	@Override
	public String buzzer(String sip, String command, String status) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("status", status);

		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + sip + "/buzzer");

		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);

		String json = coapResponse.getResponseText();
		coapClient.shutdown();
		
		return json;
	}
	
	@Override
	public String rgbLed(String sip, String command, String red, String green, String blue) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		if(red!=null){
			jsonObject.put("red", red);
			jsonObject.put("green", green);
			jsonObject.put("blue", blue);
		}

		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://" + sip + "/rgbled");

		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);

		String json = coapResponse.getResponseText();
		coapClient.shutdown();
		
		return json;
	}

}
