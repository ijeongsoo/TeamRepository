package com.mycompany.myapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dao.Dao;
import com.mycompany.myapp.dto.Member;

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
	public int joinCheckID(String mid) {
		int result = 0;
		int count = dao.memberExistSelectByMid(mid);

		if (count == 0) {
			result = 1;
		}
		return result;
	}
	
	@Override
	public Member getMember(String mid) {
		Member member = dao.memberSelectByMid(mid);
		return member;
	}
}
