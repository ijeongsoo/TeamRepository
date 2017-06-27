/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.sampleAppliance;

import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.schedule.ToggleSwitch;
import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author kang
 */
public class Appliances {

	private String category = "";
	private String lblName = "";
	private String turnTime = "";
	private String on = "";
	private ScheduledThreadPoolExecutor exec;
	private ScheduledThreadPoolExecutor execDay;
	private Light lightInstance = Light.getInstance();
	private ToggleSwitch tog;

	private String mon;
	private String tue;
	private String wen;
	private String thu;
	private String fri;
	private String sat;
	private String sun;

	private int monInt = 10;
	private int tueInt = 10;
	private int wenInt = 10;
	private int thuInt = 10;
	private int friInt = 10;
	private int satInt = 10;
	private int sunInt = 10;

	private int dayOfWeeks = 10;

	public Appliances() {

	}

	public Appliances(String category, String lblName, String turnTime, String on,
		  String a, String b, String c, String d, String e, String f, String g) {
		this.category = category;
		this.lblName = lblName;
		this.turnTime = turnTime;
		this.on = on;
		this.mon = a;
		this.tue = b;
		this.wen = c;
		this.thu = d;
		this.fri = e;
		this.sat = f;
		this.sun = g;

		setDayInt();
	}
	public void setToggle(ToggleSwitch tog){
		this.tog = tog;
	}
	public ToggleSwitch getToggle(){
		return this.tog;
	}

	public void setDayInt() {
		if (mon.equals("true")) {
			monInt = 2;
		}
		if (tue.equals("true")) {
			tueInt = 3;
		}
		if (wen.equals("true")) {
			wenInt = 4;
		}
		if (thu.equals("true")) {
			thuInt = 5;
		}
		if (fri.equals("true")) {
			friInt = 6;
		}
		if (sat.equals("true")) {
			satInt = 7;
		}
		if (sun.equals("true")) {
			sunInt = 1;
		}
	}

	public ScheduledThreadPoolExecutor getExec() {
		return exec;
	}

	public void setExec() {
		this.exec = null;
	}

	public ScheduledThreadPoolExecutor getExecDay() {
		return execDay;
	}

	public void setExecDay() {
		this.execDay = null;
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
		if (hours < 12) {
			return "오전";
		} else {
			return "오후";
		}
	}

	public void setTurnTime(String turnTime) {
		this.turnTime = turnTime;
	}

	public String getOn() {
		if (on.equals("true")) {
			return "ON";
		} else {
			return "OFF";
		}
	}

	public void setOn(String on) {
		this.on = on;
	}

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	public String getTue() {
		return tue;
	}

	public void setTue(String tue) {
		this.tue = tue;
	}

	public String getWen() {
		return wen;
	}

	public void setWen(String wen) {
		this.wen = wen;
	}

	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public String getFri() {
		return fri;
	}

	public void setFri(String fri) {
		this.fri = fri;
	}

	public String getSat() {
		return sat;
	}

	public void setSat(String sat) {
		this.sat = sat;
	}

	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
	}

	private int getIntField(String[] tokens, int index) {
		if (tokens.length <= index || tokens[index].isEmpty()) {
			return 0;
		}
		//System.out.println(Integer.parseInt(tokens[index]));
		return Integer.parseInt(tokens[index]);
	}

		public void dayChecker() {
		int sleepSec = 60;
		int first = 10;
		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		this.execDay = exec;
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("dayChecker is Running....");
				try {
					Calendar cal = LockController.cal;

					if (cal.get(Calendar.DAY_OF_WEEK) != dayOfWeeks) {
						dayOfWeeks = cal.get(Calendar.DAY_OF_WEEK);
						setExec();
						//setExecDay();
					} else {
						dayOfWeeks = dayOfWeeks;
					}
//					System.out.println("getExec ;" + getExec());
//					System.out.println("getExecDay ;" + getExecDay());
//					System.out.println("getExecShut ;" + getExec().isShutdown());
//					System.out.println("getExecDayShut ;" + getExec().isShutdown());
					System.out.println("dayOfWeeks " + dayOfWeeks);
					if (monInt == dayOfWeeks) {
						//lightOnOff();
						throw new RuntimeException();
					} else if (tueInt == dayOfWeeks) {
						//lightOnOff();
						throw new RuntimeException();
					} else if (wenInt == dayOfWeeks) {
						//lightOnOff();
						throw new RuntimeException();
					} else if (thuInt == dayOfWeeks) {
						//lightOnOff();
						throw new RuntimeException();
					} else if (friInt == dayOfWeeks) {
						//lightOnOff();
						throw new RuntimeException();
					} else if (satInt == dayOfWeeks) {
						//lightOnOff();
						throw new RuntimeException();
					} else if (sunInt == dayOfWeeks) {
						//lightOnOff();
						throw new RuntimeException();
					}

				} catch (RuntimeException e) {
					exec.shutdown();
					System.out.println("dayChecker is Shutdown");
					exec.setRemoveOnCancelPolicy(true);
					System.gc();
					//this.exec = null;
					//System.out.println("getExec " + getExec());
						lightOnOff();
//					else {
//						dayChecker();
//					}
					
				}
			}

			@Override
			protected void finalize() throws Throwable {
				System.out.println("exec-day is OUT");
				//lightOnOff();
			}

		}, first, sleepSec, TimeUnit.SECONDS);
	}

	public void lightOnOff() {
		int sleepSec = 10;
		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		this.exec = exec;
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("lightOnOff is Running....");
					Calendar cal = Calendar.getInstance();
					String[] tokens = turnTime.split(":");
					int tHour = getIntField(tokens, 0);
					int tMin = getIntField(tokens, 1);
					System.out.println(tHour + " " + tMin + " : " + cal.get(Calendar.HOUR_OF_DAY) + " " + cal.get(Calendar.MINUTE));
					System.out.println();
					//Calendar cal2 = LockController.cal;
					if (cal.get(Calendar.DAY_OF_WEEK) != dayOfWeeks) {
						throw new RuntimeException();
					}
					
					
					if ((cal.get(Calendar.HOUR_OF_DAY) == tHour) && (cal.get(Calendar.MINUTE) == tMin)) {
						if (lblName.equals("안방")) {
							lightInstance.setInnerRoom(getOn().equals("ON"));
						} else if (lblName.equals("방-1")) {
							lightInstance.setNum1Room(getOn().equals("ON"));
						} else if (lblName.equals("방-2")) {
							lightInstance.setNum2Room(getOn().equals("ON"));
						} else if (lblName.equals("욕실")) {
							lightInstance.setBathRoom(getOn().equals("ON"));
						} else if (lblName.equals("거실")) {
							lightInstance.setLivingRoom(getOn().equals("ON"));
						} else if (lblName.equals("주방")) {
							lightInstance.setKeachinRoom(getOn().equals("ON"));
						} else if (lblName.equals("다용도실")) {
							lightInstance.setMultiRoom(getOn().equals("ON"));
						} else if (lblName.equals("보일러실")) {
							lightInstance.setBoilRoom(getOn().equals("ON"));
						} else if (lblName.equals("현관")) {
							lightInstance.setDoorRoom(getOn().equals("ON"));
						} else {
							System.out.println("diffrent device!!!");
						}

						throw new RuntimeException();
					}
				} catch (Exception ex) {
					exec.shutdown();
					System.out.println("lightOnOFF is Shutdown");
					exec.setRemoveOnCancelPolicy(true);
					dayChecker();
					System.gc();
				}

			}

			@Override
			protected void finalize() throws Throwable {
				System.out.println("exec-light is OUT");
				//dayChecker();
			}

		}, 0, sleepSec, TimeUnit.SECONDS);
	}

//	public void lightOnOff(String turnTime, String name, String on){
//		int sleepSec = 10;
//		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
//		this.exec = exec;
//		exec.scheduleAtFixedRate(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					System.out.println("lightOnOff is Running....");
//					Calendar cal = Calendar.getInstance();
//					String[] tokens = turnTime.split(":");
//					int tHour = getIntField(tokens, 0);
//					int tMin = getIntField(tokens, 1);
//					System.out.println(tHour + " " + tMin + " : " + cal.get(Calendar.HOUR_OF_DAY) + " " + cal.get(Calendar.MINUTE));
//					System.out.println();
//
//					if ((cal.get(Calendar.HOUR_OF_DAY) == tHour) && (cal.get(Calendar.MINUTE) == tMin)) {
//						if (name.equals("안방")) {
//							lightInstance.setInnerRoom(on.equals("ON"));
//						} else if (name.equals("방-1")) {
//							lightInstance.setNum1Room(on.equals("ON"));
//						} else if (name.equals("방-2")) {
//							lightInstance.setNum2Room(on.equals("ON"));
//						} else if (name.equals("욕실")) {
//							lightInstance.setBathRoom(on.equals("ON"));
//						} else if (name.equals("거실")) {
//							lightInstance.setLivingRoom(on.equals("ON"));
//						} else if (name.equals("주방")) {
//							lightInstance.setKeachinRoom(on.equals("ON"));
//						} else if (name.equals("다용도실")) {
//							lightInstance.setMultiRoom(on.equals("ON"));
//						} else if (name.equals("보일러실")) {
//							lightInstance.setBoilRoom(on.equals("ON"));
//						} else if (name.equals("현관")) {
//							lightInstance.setDoorRoom(on.equals("ON"));
//						} else {
//							System.out.println("diffrent device!!!");
//						}
//
//						throw new RuntimeException();
//					}
//				} catch (Exception ex) {
//					exec.shutdown();
//					System.gc();
//				}
//
//			}
//			
//
//			@Override
//			protected void finalize() throws Throwable {
//				System.out.println("exec is OUT");
//			}
//			
//		}, 0, sleepSec, TimeUnit.SECONDS);
//	}
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Appliances is Out!!!");
	}

}
