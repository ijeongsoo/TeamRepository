package com.mycompany.myapp.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.dto.Sensingcar;

@Component
public class DaoImpl implements Dao {
	private static final Logger LOGGER =LoggerFactory.getLogger(DaoImpl.class);
	
	@Autowired
	private SqlSessionTemplate sst;
	
	@Override
	public int memberExistSelectByMid(String mid) {
		return sst.selectOne("member.selectExistMid", mid);
	}
	
	@Override
	public int sensingcarExistSelectBySip(String sip) {
		return sst.selectOne("sensingcar.selectExistSip", sip);
	}
	
	
	@Override
	public String memberInsert(Member member) {
		sst.insert("member.insert", member);
		return member.getMid();
	}
	


	@Override
	public Member memberSelectByMid(String mid) {
		Member member=sst.selectOne("member.selectByMid", mid);
		return member;
	}
	
	@Override
	public int sensingcarInsert(Sensingcar sensingcar) {
		sst.insert("sensingcar.insert", sensingcar);
		return sensingcar.getSno();
	}
	
	@Override
	public List<Sensingcar> sensingcarSelectAll() {
		List<Sensingcar> list = sst.selectList("sensingcar.selectAll");
		return list;
	}
	
	
}
