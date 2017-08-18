package com.mycompany.myapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Dao;
import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.dto.PasswdChange;

@Component
public class ServiceImpl implements Service{
	private static final Logger logger = LoggerFactory.getLogger(ServiceImpl.class);
	@Autowired
	private Dao dao;
	
	@Override
	public void memberJoin(Member member) {
		dao.memberInsert(member);
	}
	
	@Override
	public Member login(String mid, String mpassword) {
		Member member = dao.memberSelectByMid(mid);
		if (member == null) {
			return null;
		} else {
			if (member.getMpassword().equals(mpassword)) {
				return member;
			} else {
				member = new Member();
				return member;
			}
		}

	}

	@Override
	public PasswdChange selectLinkByMid(String reset_mid) {
		PasswdChange passwdChange = dao.selectLink(reset_mid);
		return passwdChange;
	}

	@Override
	public void savePasswdChangeLink(PasswdChange passwdChange) {
		dao.linkInsert(passwdChange);
	}
	
	

	@Override
	public int joinCheckID(String mid) {
		int result = 0;
		Member member = dao.memberSelectByMid(mid);

		if (member == null) {
			result = 1;
		}
		return result;
	}
	
}
