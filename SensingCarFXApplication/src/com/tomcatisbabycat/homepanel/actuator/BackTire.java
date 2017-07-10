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
public class BackTire {
	static private BackTire backTire = new BackTire();
	
	private String resource = "backtire";
	private String backTireCommand = "change";
	private String backTireStatus = "status";
	private String direction = "forward";
	private int realSpeed;
	
	private BackTire(){}
	
	static public BackTire getInstance(){
		return backTire;
	}

	public String getBackTireStatus() {
		return backTireStatus;
	}

	public String getResource() {
		return resource;
	}

	public String getBackTireCommand() {
		return backTireCommand;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public int getRealSpeed(){
		return realSpeed;
	}
	public void setRealSpeed(int realSpeed){
		if(realSpeed > 4095){
			realSpeed = 4000;
		}else if(realSpeed > 0 && realSpeed <500){
			realSpeed = 2200;
		}else if(realSpeed < 2200 && realSpeed > 1700){
			realSpeed = 0;
		}else if(realSpeed < 0){
			realSpeed = 0;
		}
			
		this.realSpeed = realSpeed;
	}
}
