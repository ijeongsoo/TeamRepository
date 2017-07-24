/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.actuator;

/**
 *
 * @author kang
 */
public class LCD {
	private static LCD lcd = new LCD();
	
	private String lcdCommand = "change";
	private String lcdStatus = "status";
	
	private String line0;
	private String line1;
	
	private String resource = "lcd";
	
	
	private LCD(){}
	
	public static LCD getInstance(){
		return lcd;
	}

	public String getLcdCommand() {
		return lcdCommand;
	}

	public String getLcdStatus() {
		return lcdStatus;
	}

	public String getResource() {
		return resource;
	}

	public String getLine0() {
		return line0;
	}

	public void setLine0(String line0) {
		this.line0 = line0;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}
}
