/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.samplestatus;

/**
 *
 * @author ijeongsu
 */
public class SampleStatus {
	  public  double temperature= 14.5;
	  public  int moisture=70;
	  public  int dust=37;
	  public  String weather="snow";
	  
	  private static SampleStatus samplestatus=new SampleStatus();
	  
	  
	  private SampleStatus() {
	  }
	  
	  public static SampleStatus getInstance(){
			return samplestatus;
	  }
	  
	  public double getTemperature() {
			return temperature;
	  }

	  

	  public void setTemperature(double temperature) {
			this.temperature = temperature;
	  }

	  public int getMoisture() {
			return moisture;
	  }

	  public void setMoisture(int moisture) {
			this.moisture = moisture;
	  }

	  public int getDust() {
			return dust;
	  }

	  public void setDust(int dust) {
			this.dust = dust;
	  }

	  public String getWeather() {
			return weather;
	  }

	  public void setWeather(String weather) {
			this.weather = weather;
	  }
	  
	  
}
