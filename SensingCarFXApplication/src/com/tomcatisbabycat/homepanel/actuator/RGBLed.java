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
public class RGBLed {
	static private RGBLed rgb = new RGBLed();
	
	private String resource = "rgbled";
	
	private int red;
	private int green;
	private int blue = 255;
	
	private String rgbChange = "change";
	private String rgbStatus = "status";
	
	private RGBLed(){
		
	}
	
	static public RGBLed getInstance(){
		return rgb;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public String getRgbChange() {
		return rgbChange;
	}

	public String getRgbStatus() {
		return rgbStatus;
	}

	public String getResource() {
		return resource;
	}
	
	
}
