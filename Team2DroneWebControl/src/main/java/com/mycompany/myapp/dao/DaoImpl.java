package com.mycompany.myapp.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		System.out.println(mid);
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

}
