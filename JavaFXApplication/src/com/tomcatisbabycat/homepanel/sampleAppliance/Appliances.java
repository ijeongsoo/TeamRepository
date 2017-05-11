/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.sampleAppliance;

import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author kang
 */
public class Appliances {

	private String category = "";
	private String lblName = "";
	private String turnTime = "";
	private String on = "";
	private ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
	private Light lightInstance = Light.getInstance();
	
	public Appliances() {

	}

	public Appliances(String category, String lblName, String turnTime, String on) {
		this.category = category;
		this.lblName = lblName;
		this.turnTime = turnTime;
		this.on = on;
		if(category.equals("전등")){
			lightOnOff(this.turnTime, this.lblName, this.on);
		}
	}

	public ScheduledThreadPoolExecutor getExec() {
		return exec;
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
	
	private void lightOnOff(String turnTime, String name, String on){
//		System.out.println("turnTime :" + turnTime);
//		System.out.println("name :" + name);
//		System.out.println("on :" + on);
		int sleepSec = 10;

		//final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
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
						if (name.equals("안방")) {
							lightInstance.setInnerRoom(on.equals("true"));
						} else if (name.equals("방-1")) {
							lightInstance.setNum1Room(on.equals("true"));
						} else if (name.equals("방-2")) {
							lightInstance.setNum2Room(on.equals("true"));
						} else if (name.equals("욕실")) {
							lightInstance.setBathRoom(on.equals("true"));
						} else if (name.equals("거실")) {
							lightInstance.setLivingRoom(on.equals("true"));
						} else if (name.equals("주방")) {
							lightInstance.setKeachinRoom(on.equals("true"));
						} else if (name.equals("다용도실")) {
							lightInstance.setMultiRoom(on.equals("true"));
						} else if (name.equals("보일러실")) {
							lightInstance.setBoilRoom(on.equals("true"));
						} else if (name.equals("현관")) {
							lightInstance.setDoorRoom(on.equals("true"));
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
