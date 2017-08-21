package com.mycompany.myapp.dto;

import org.springframework.web.multipart.MultipartFile;

public class Drone {
	private String dname;
	private String member_mid;
	private String dmacaddr;
	private String doriginalfilename;
	private String dsavedfilename;
	private String dfilecontent;
	private int dtype;
	private MultipartFile dattach;
	
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getMember_mid() {
		return member_mid;
	}
	public void setMember_mid(String member_mid) {
		this.member_mid = member_mid;
	}
	public String getDmacaddr() {
		return dmacaddr;
	}
	public void setDmacaddr(String dmacaddr) {
		this.dmacaddr = dmacaddr;
	}
	public String getDoriginalfilename() {
		return doriginalfilename;
	}
	public void setDoriginalfilename(String doriginalfilename) {
		this.doriginalfilename = doriginalfilename;
	}
	public String getDsavedfilename() {
		return dsavedfilename;
	}
	public void setDsavedfilename(String dsavedfilename) {
		this.dsavedfilename = dsavedfilename;
	}
	public String getDfilecontent() {
		return dfilecontent;
	}
	public void setDfilecontent(String dfilecontent) {
		this.dfilecontent = dfilecontent;
	}
	public int getDtype() {
		return dtype;
	}
	public void setDtype(int dtype) {
		this.dtype = dtype;
	}
	public MultipartFile getDattach() {
		return dattach;
	}
	public void setDattach(MultipartFile dattach) {
		this.dattach = dattach;
	}
	
}
