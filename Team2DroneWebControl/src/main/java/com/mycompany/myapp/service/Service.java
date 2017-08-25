package com.mycompany.myapp.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Drone;
import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.dto.PasswdChange;

@Component
public interface Service {

	public Member getMember(String mid);
	public void memberJoin(Member member);
	public void memberUpdate(Member member);
	
	public Member login(String mid, String mpassword);
	
	public PasswdChange	selectLinkByMid(String reset_mid);
	public void savePasswdChangeLink(PasswdChange passwdChange);
	public void passwdChangeDelete(String mid);
	
	public int joinCheckID(String mid);
	
	public List<Drone> allList(String mid);
	public List<Drone> droneList(String mid);
	public List<Drone> roverList(String mid);
	public List<Drone> planeList(String mid);
	public void deleteDevice(String dmacaddr);
	public Drone getDrone(String dmacaddr);
	public void deviceUpdate(Drone drone);



}
