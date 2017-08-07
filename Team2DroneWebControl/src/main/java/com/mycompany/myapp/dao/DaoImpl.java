package com.mycompany.myapp.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.Member;

@Component
public class DaoImpl implements Dao {
	@Autowired
	private SqlSessionTemplate sst;

	@Override
	public void memberInsert(Member member) {
		sst.insert("member.insert", member);
	}
}
