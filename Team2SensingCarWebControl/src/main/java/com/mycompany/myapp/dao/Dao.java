package com.mycompany.myapp.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.dto.Sensingcar;

@Component
public interface Dao {
	public int memberExistSelectByMid(String mid);
	public String memberInsert(Member member);
	public Member memberSelectByMid(String mid);
	
	public int sensingcarExistSelectBySip(String sip);
	public Sensingcar sensingcarSelectBySip(String sip);
	public int sensingcarInsert(Sensingcar sensingcar);
	public List<Sensingcar> sensingcarSelectAll();
	public void sensingcarRemoveBySno(int sno);
}
