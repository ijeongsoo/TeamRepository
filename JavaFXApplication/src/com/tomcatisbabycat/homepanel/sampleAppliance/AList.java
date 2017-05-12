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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AList {

	private ObservableList<Appliances> tv = FXCollections.observableArrayList();
	private ObservableList<Appliances> light = FXCollections.observableArrayList();
	private ObservableList<Appliances> ac = FXCollections.observableArrayList();
	private ObservableList<Appliances> wm = FXCollections.observableArrayList();
	
	private static AList aList = new AList();
	
	private AList() {
	}
	
	public static AList getInstance() {
		return aList;
	}
	
	public ObservableList<Appliances> getTv() {
		return tv;
	}

	public void setTv(ObservableList<Appliances> tv) {
		this.tv = tv;
	}

	public ObservableList<Appliances> getLight() {
		return light;
	}

	public void setLight(ObservableList<Appliances> light) {
		this.light = light;
	}

	public ObservableList<Appliances> getAc() {
		return ac;
	}

	public void setAc(ObservableList<Appliances> ac) {
		this.ac = ac;
	}

	public ObservableList<Appliances> getWm() {
		return wm;
	}

	public void setWm(ObservableList<Appliances> wm) {
		this.wm = wm;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("AList is Out");
	}
	
}
