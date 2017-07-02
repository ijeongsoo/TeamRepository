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
	
	public String thermistorSensor(String sip, String command);
	public String photoresistorSensor(String sip, String command);
	public String gasSensor(String sip, String command);
	public String ultrasonicSensor(String sip, String command);
	public String trackingSensor(String sip, String command);
	
	
	public String laserEmitter(String sip, String command, String status);
	public String buzzer(String sip, String command, String status);
	public String rgbLed(String sip, String command, String red, String green, String blue);

	public void sensingcarRegist(Sensingcar sensingcar);
	public List<Sensingcar> sensingcarListAll();
	public Sensingcar getSensingcar(String sip);
	public void sensingcarRemove(int sno);
}
