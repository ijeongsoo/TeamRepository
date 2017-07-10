package com.tomcatisbabycat.homepanel.actuator;

public class Camera {
	static private Camera camera = new Camera();
	
	private String resource = "camera";
	
	private Camera(){}
	
	static public Camera getInstance(){
		return camera;
	}
	
	private int leftRightAngle;
	private int upDownAngle;
	
	private String cameraChange = "change";
	private String cameraStatus = "status";

	public int getLeftRightAngle() {
		return leftRightAngle;
	}

	public void setLeftRightAngle(int leftRightAngle) {
		this.leftRightAngle = leftRightAngle;
	}

	public int getUpDownAngle() {
		return upDownAngle;
	}

	public void setUpDownAngle(int upDownAngle) {
		this.upDownAngle = upDownAngle;
	}
	public String getCameraChange() {
		return cameraChange;
	}

	public String getCameraStatus() {
		return cameraStatus;
	}
	
	public String getResource(){
		return resource;
	}
}