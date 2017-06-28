package com.mycompany.myapp.dto;

import org.springframework.web.multipart.MultipartFile;

public class Sensingcar {
	private int sno;
	private String sip;
	private String sname;
	private String sregistor;
	private MultipartFile sattach;
	private String soriginalfilename;
	private String ssavedfilename;
	private String sfilecontent;
	private String sdescription;
	public String getSip() {
		return sip;
	}
	public void setSip(String sip) {
		this.sip = sip;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSregistor() {
		return sregistor;
	}
	public void setSregistor(String sregistor) {
		this.sregistor = sregistor;
	}
	public MultipartFile getSattach() {
		return sattach;
	}
	public void setSattach(MultipartFile sattach) {
		this.sattach = sattach;
	}
	public String getSoriginalfilename() {
		return soriginalfilename;
	}
	public void setSoriginalfilename(String soriginalfilename) {
		this.soriginalfilename = soriginalfilename;
	}
	public String getSsavedfilename() {
		return ssavedfilename;
	}
	public void setSsavedfilename(String ssavedfilename) {
		this.ssavedfilename = ssavedfilename;
	}
	public String getSfilecontent() {
		return sfilecontent;
	}
	public void setSfilecontent(String sfilecontent) {
		this.sfilecontent = sfilecontent;
	}
	public String getSdescription() {
		return sdescription;
	}
	public void setSdescription(String sdescription) {
		this.sdescription = sdescription;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	
	
	
}
