package com.mycompany.myapp.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Drone;
import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.dto.PasswdChange;

@Component
public class DaoImpl implements Dao {
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public void memberInsert(Member member) {
		sst.insert("member.insert", member);
	}
	
	@Override
	public Member memberSelectByMid(String mid) {
		Member member=sst.selectOne("member.selectByMid", mid);
		return member;
	}

	@Override
	public PasswdChange selectLink(String reset_mid) {
		PasswdChange passwdChange = sst.selectOne("passwdchange.selectByMid",reset_mid);
		return passwdChange;
	}

	@Override
	public void linkInsert(PasswdChange passwdChange) {
		sst.insert("passwdchange.insert", passwdChange);
	}

	@Override
	public void memberUpdate(Member member) {
		sst.update("member.update", member);
	}

	@Override
	public void passwdChangeDelete(String mid) {
		sst.delete("passwdchange.delete", mid);
		
	}

	@Override
	public List<Drone> allSelect(String mid) {
		List<Drone> list = sst.selectList("drone.selectAll", mid);
		return list;
	}

	@Override
	public List<Drone> droneSelect(String mid) {
		List<Drone> list = sst.selectList("drone.selectDrone", mid);
		return list;
	}

	@Override
	public List<Drone> roverSelect(String mid) {
		List<Drone> list = sst.selectList("drone.selectRover", mid);
		return list;
	}

	@Override
	public List<Drone> planeSelect(String mid) {
		List<Drone> list = sst.selectList("drone.selectPlane", mid);
		return list;
	}

}
