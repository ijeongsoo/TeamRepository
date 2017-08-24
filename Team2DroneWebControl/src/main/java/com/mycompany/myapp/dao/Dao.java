package com.mycompany.myapp.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Drone;
import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.dto.PasswdChange;

@Component
public interface Dao {

	public void memberInsert(Member member);
	public Member memberSelectByMid(String mid);
	public void memberUpdate(Member member);
	
	public PasswdChange selectLink(String reset_mid);
	public void linkInsert(PasswdChange passwdChange);
	
	
	public void passwdChangeDelete(String mid);

	public List<Drone> allSelect(String mid);
	public List<Drone> droneSelect(String mid);
	public List<Drone> roverSelect(String mid);
	public List<Drone> planeSelect(String mid);
	public void deleteDevice(String dmacaddr);
	public Drone selectDevice(String dmacaddr);
	public void droneUpdate(Drone drone);
}
