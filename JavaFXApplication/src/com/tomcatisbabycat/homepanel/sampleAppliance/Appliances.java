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
	private boolean noon = true; // true -> 오후
	private boolean on = true; // true -> on , false -> off;
	public Appliances() {
		
	}
	public Appliances(String lblName, String turnTime, boolean noon){
		this.lblName = lblName;
		this.turnTime = turnTime;
		this.noon = noon;
	}
	public Appliances(String category, String lblName, String turnTime, boolean noon, boolean on){
		this.category = category;
		this.lblName = lblName;
		this.turnTime = turnTime;
		this.noon = noon;
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

	public String getNoon() {
		if(noon){
			return "오후";
		}
		else{
			return "오전";
		}
	}

	public void setNoon(boolean noon) {
		this.noon = noon;
	}
	
	public boolean isOn(){
		return on;
	}
	
	public void setOn(boolean on){
		this.on = on;
	}
}
