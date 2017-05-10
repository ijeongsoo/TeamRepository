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
import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AList {

	private ObservableList<Appliances> tv = FXCollections.observableArrayList();
	private ObservableList<Appliances> light = FXCollections.observableArrayList();
	private ObservableList<Appliances> ac = FXCollections.observableArrayList();
	private ObservableList<Appliances> wm = FXCollections.observableArrayList();

	private static AList aList = new AList();

	private static Light lightInstance = Light.getInstance();

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

	public void turnOn() {
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

	public void lightOnOff(String turnTime, String on, String name) {
		int sleepSec = 10;

		final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					Calendar cal = Calendar.getInstance();
					String[] tokens = turnTime.split(":");
					int tHour = getIntField(tokens, 0);
					int tMin = getIntField(tokens, 1);
					System.out.println(tHour + " " + tMin + " : " + cal.get(Calendar.HOUR_OF_DAY) + " " + cal.get(Calendar.MINUTE));
					System.out.println();
					
					if ((cal.get(Calendar.HOUR_OF_DAY) == tHour) && (cal.get(Calendar.MINUTE) == tMin)) {
						if(name.equals("안방")){
							lightInstance.setInnerRoom(on.equals("ON"));
						}else if(name.equals("방-1")){
							lightInstance.setNum1Room(on.equals("ON"));
						}else if(name.equals("방-2")){
							lightInstance.setNum2Room(on.equals("ON"));
						}else if(name.equals("욕실")){
							lightInstance.setBathRoom(on.equals("ON"));
						}else if(name.equals("거실")){
							lightInstance.setLivingRoom(on.equals("ON"));
						}else if(name.equals("주방")){
							lightInstance.setKeachinRoom(on.equals("ON"));
						}else if(name.equals("다용도실")){
							lightInstance.setMultiRoom(on.equals("ON"));
						}else if(name.equals("보일러실")){
							lightInstance.setBoilRoom(on.equals("ON"));
						}else if(name.equals("현관")){
							lightInstance.setDoorRoom(on.equals("ON"));
						}
						
						throw new RuntimeException();
					}
				} catch (Exception ex) {
					exec.shutdown();
				}

			}
		}, 0, sleepSec, TimeUnit.SECONDS);

	}

	private int getIntField(String[] tokens, int index) {
		if (tokens.length <= index || tokens[index].isEmpty()) {
			return 0;
		}
		//System.out.println(Integer.parseInt(tokens[index]));
		return Integer.parseInt(tokens[index]);
	}
}
