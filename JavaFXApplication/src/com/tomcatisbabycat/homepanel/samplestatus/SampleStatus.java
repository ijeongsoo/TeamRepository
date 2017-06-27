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
	  private  double temperature= 38.5;
	  private  int moisture=80;
	  private  int dust=30;
	  private  String weather="cloudy";
	  private double wishTemperature=21;
	  private int wishMoisture=70;
	  private int wishDust=10;
	  
	  private double elecUsed;
	  private double waterUsed;
	  private double gasUsed;
	  
	  
	  private static SampleStatus samplestatus=new SampleStatus();
	  
	  
	  private SampleStatus() {
	  }
	  
	  public static SampleStatus getInstance(){
			return samplestatus;
	  }

	public double getWishTemperature() {
		return wishTemperature;
	}

	public void setWishTemperature(double wishTemperature) {
		this.wishTemperature = wishTemperature;
	}

	public int getWishMoisture() {
		return wishMoisture;
	}

	public void setWishMoisture(int wishMoisture) {
		this.wishMoisture = wishMoisture;
	}

	public int getWishDust() {
		return wishDust;
	}

	public void setWishDust(int wishDust) {
		this.wishDust = wishDust;
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

	public double getElecUsed() {
		return elecUsed;
	}

	public void setElecUsed(double elecUsed) {
		this.elecUsed = elecUsed;
	}

	public double getWaterUsed() {
		return waterUsed;
	}

	public void setWaterUsed(double waterUsed) {
		this.waterUsed = waterUsed;
	}

	public double getGasUsed() {
		return gasUsed;
	}

	public void setGasUsed(double gasUsed) {
		this.gasUsed = gasUsed;
	}
	  
	  
	  
	  
}
