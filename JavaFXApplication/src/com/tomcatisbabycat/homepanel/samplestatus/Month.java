/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.samplestatus;

import java.util.Calendar;

/**
 *
 * @author ijeongsu
 */
public class Month {
	private String month;
	private double elecTotalUsed=0;
	private double waterTotalUsed=0;
	private double gasTotalUsed=0;

	public Month() {
		Calendar c =  Calendar.getInstance();
		month = String.valueOf(c.get(Calendar.MONTH)+1);
	}

	public Month(String month) {
		this.month = month;
	}

	public String getMonth() {
		return month;
	}

	public double getWaterTotalUsed() {
		return waterTotalUsed;
	}

	public void setWaterTotalUsed(double waterUsed) {
		this.waterTotalUsed += waterUsed;
	}

	public double getGasTotalUsed() {
		return gasTotalUsed;
	}

	public void setGasTotalUsed(double gasUsed) {
		this.gasTotalUsed += gasUsed;
	}

	public double getElecTotalUsed() {
		return elecTotalUsed;
	}

	public void setElecTotalUsed(double elecUsed) {
		this.elecTotalUsed += elecUsed;
	}
	

	

	@Override
	public int hashCode() {
		return Integer.parseInt(month);
	}

	@Override
	public boolean equals(Object obj) {
		return month.equals(obj.toString());
	}

	@Override
	public String toString() {
		return month;
	}
	
	
	
	
}
