package com.mycompany.myapp.dto;

import org.springframework.web.multipart.MultipartFile;

public class Member {
	private String mid;
	private String mpassword;
	private String mname;
	private String moriginalfilename;
	private String msavedfilename;
	private MultipartFile mattach;
	private String mfiletype;
	
	
	
	public String getMfiletype() {
		return mfiletype;
	}
	public void setMfiletype(String mfiletype) {
		this.mfiletype = mfiletype;
	}
	public String getMpassword() {
		return mpassword;
	}
	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	
	public String getMoriginalfilename() {
		return moriginalfilename;
	}
	public void setMoriginalfilename(String moriginalfilename) {
		this.moriginalfilename = moriginalfilename;
	}
	public String getMsavedfilename() {
		return msavedfilename;
	}
	public void setMsavedfilename(String msavedfilename) {
		this.msavedfilename = msavedfilename;
	}
	public MultipartFile getMattach() {
		return mattach;
	}
	public void setMattach(MultipartFile mattach) {
		this.mattach = mattach;
	}
	

}
