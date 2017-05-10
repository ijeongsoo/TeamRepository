/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.sampleAppliance;

import java.util.Calendar;

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

	public Appliances(String category, String lblName, String turnTime, String on) {
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

	public String getTurnNoon() {
		String[] tokens = turnTime.split(":");
		int hours = getIntField(tokens, 0);
		if(hours < 12){
			return "오전";
		}else{
			return "오후";
		}
	}

	public void setTurnTime(String turnTime) {
		this.turnTime = turnTime;
	}

	public String getOn() {
		if(on.equals("true")){
			return "ON";
		}else{
			return "OFF";
		}
	}

	public void setOn(String on) {
		this.on = on;
	}

	private int getIntField(String[] tokens, int index) {
		if (tokens.length <= index || tokens[index].isEmpty()) {
			return 0;
		}
		//System.out.println(Integer.parseInt(tokens[index]));
		return Integer.parseInt(tokens[index]);
	}

}
