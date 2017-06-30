package com.mycompany.myapp.service;

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
public class ServiceImpl implements Service{
	private static final Logger logger = LoggerFactory.getLogger(ServiceImpl.class);
	@Autowired
	private Dao dao;
	
	@Override
	public int joinCheckID(String mid) {
		// TODO Auto-generated method stub
		int result=0;
		int count =dao.memberExistSelectByMid(mid);
		
		if(count==0){
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
		Member member=dao.memberSelectByMid(mid);
		if(member==null){
			return null;
		}else{
			if(member.getMpassword().equals(mpassword)){
				return member;
			}else{
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
		int result=0;
		int count =dao.sensingcarExistSelectBySip(sip);
		
		if(count==0){
			result = 1;
		}
		return result;
	}
	
	@Override
	public int checkCoumunication(String sip, String command) {
		int result=1;
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);

		String reqJson = jsonObject.toString();

		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://"+sip+"/lcd");
		
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		
		if(coapResponse==null){
			result=0;
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
		Sensingcar sensingcar =dao.sensingcarSelectBySip(sip);
		return sensingcar;
	}
	
	

}
