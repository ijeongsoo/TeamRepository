package com.tomcatisbabycat.homepanel.actuator;

public class Ultrasonic {
	static private Ultrasonic ultrasonic = new Ultrasonic();
	
	private String resource = "ultrasonicsensor";
	
	private Ultrasonic(){}
	
	static public Ultrasonic getInstance(){
		return ultrasonic;
	}
	
	private int angle;
	
	private String ultrasonicChange = "change";
	private String ultrasonicStatus = "status";

	public String getUltrasonicChange() {
		return ultrasonicChange;
	}

	public String getUltrasonicStatus() {
		return ultrasonicStatus;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	public String getResource(){
		return resource;
	}
}
