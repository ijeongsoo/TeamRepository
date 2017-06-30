package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.dto.Sensingcar;


@Component
public interface Service {
	public int joinCheckID(String mid);
	public int registCheckIP(String sip);
	public void memberJoin(Member member);
	public Member login(String mid, String mpassword);
	public Member getMember(String mid);
	
	public int checkCoumunication(String sip, String command);
	
	public void sensingcarRegist(Sensingcar sensingcar);
	public List<Sensingcar> sensingcarListAll();
	public Sensingcar getSensingcar(String sip);
}
