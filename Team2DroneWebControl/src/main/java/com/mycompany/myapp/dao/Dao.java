package com.mycompany.myapp.dao;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.dto.PasswdChange;

@Component
public interface Dao {

	public void memberInsert(Member member);
	public Member memberSelectByMid(String mid);
	public PasswdChange selectLink(String reset_mid);
	public void linkInsert(PasswdChange passwdChange);

}
