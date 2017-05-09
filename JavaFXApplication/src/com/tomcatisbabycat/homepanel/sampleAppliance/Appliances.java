/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.sampleAppliance;

/**
 *
 * @author kang
 */
public class Appliances {
	private String category = "";
	private String lblName = "";
	private String turnTime = "";
	private String on = "";
	public Appliances() {
		
	}
	public Appliances(String lblName, String turnTime, boolean noon){
		this.lblName = lblName;
		this.turnTime = turnTime;
	}
	public Appliances(String category, String lblName, String turnTime, String on){
		this.category = category;
		this.lblName = lblName;
		this.turnTime = turnTime;
		this.on = on;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLblName() {
		return lblName;
	}

	public void setLblName(String lblName) {
		this.lblName = lblName;
	}

	public String getTurnTime() {
		return turnTime;
	}

	public void setTurnTime(String turnTime) {
		this.turnTime = turnTime;
	}

	public String getOn() {
		return on;
	}

	public void setOn(String on) {
		this.on = on;
	}
	
}
