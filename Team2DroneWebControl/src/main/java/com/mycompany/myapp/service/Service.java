package com.mycompany.myapp.service;

import org.springframework.stereotype.Component;
import com.mycompany.myapp.dto.Member;

@Component
public interface Service {

	public void memberJoin(Member member);
	public int joinCheckID(String mid);
	Member getMember(String mid);


}
