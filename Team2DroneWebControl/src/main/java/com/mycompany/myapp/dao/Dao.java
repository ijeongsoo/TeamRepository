package com.mycompany.myapp.dao;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Member;

@Component
public interface Dao {

	public void memberInsert(Member member);
	public int memberExistSelectByMid(String mid);
	Member memberSelectByMid(String mid);

}
