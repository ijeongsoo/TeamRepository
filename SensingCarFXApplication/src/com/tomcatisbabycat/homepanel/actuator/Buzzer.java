package com.tomcatisbabycat.homepanel.actuator;


public class Buzzer {
	static private Buzzer buzzer = new Buzzer();
	
	private String resource = "buzzer";
	private String buzzerChange = "change";
	private String buzzerStatus = "status";
	private String onOffValue = "on";
	
	private Buzzer(){
	}
	
	static public Buzzer getInstance(){
		return buzzer;
	}

	public String getResource() {
		return resource;
	}

	public String getBuzzerChange() {
		return buzzerChange;
	}

	public String getBuzzerStatus() {
		return buzzerStatus;
	}

	public String getOnOffValue() {
		return onOffValue;
	}

	public void setOnOffValue(String onOffValue) {
		this.onOffValue = onOffValue;
	}
	
}
