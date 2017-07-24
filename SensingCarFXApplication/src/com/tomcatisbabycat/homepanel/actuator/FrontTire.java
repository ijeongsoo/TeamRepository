package com.tomcatisbabycat.homepanel.actuator;

public class FrontTire {
	static private FrontTire frontTire = new FrontTire();
	
	private String resource = "fronttire";
	
	private FrontTire(){}
	
	static public FrontTire getInstance(){
		return frontTire;
	}
	
	private int angle;
	
	private String frontTireChange = "change";
	private String frontTireStatus = "status";

	public String getFrontTireChange() {
		return frontTireChange;
	}

	public String getFrontTireStatus() {
		return frontTireStatus;
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
