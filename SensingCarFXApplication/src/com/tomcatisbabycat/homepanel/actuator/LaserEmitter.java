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
public class LaserEmitter {
	static private LaserEmitter laserEmitter = new LaserEmitter();
	
	private String resource = "laseremitter";
	private String buzzerChange = "change";
	private String buzzerStatus = "status";
	private String onOffValue = "on";
	
	private LaserEmitter(){}
	
	static public LaserEmitter getInstance(){
		return laserEmitter;
	}

	public String getResource() {
		return resource;
	}

	public String getBuzzerChange() {
		return buzzerChange;
	}

	public String getBuzzerStatus() {
		return buzzerStatus;
	}

	public String getOnOffValue() {
		return onOffValue;
	}
	
	public void setOnOffValue(String onOffValue){
		this.onOffValue = onOffValue;
	}
}
