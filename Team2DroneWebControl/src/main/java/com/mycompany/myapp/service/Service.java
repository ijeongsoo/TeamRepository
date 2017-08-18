package com.mycompany.myapp.service;

import org.springframework.stereotype.Component;
import com.mycompany.myapp.dto.Member;
import com.mycompany.myapp.dto.PasswdChange;

@Component
public interface Service {

	public void memberJoin(Member member);
	public Member login(String mid, String mpassword);
	public PasswdChange	selectLinkByMid(String reset_mid);
	public void savePasswdChangeLink(PasswdChange passwdChange);

}
