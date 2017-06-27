/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.sampleAppliance;

/**
 *
 * @author ijeongsu
 */
public class Light {
	private boolean boilRoom=false;
	private boolean multiRoom=false;
	private boolean innerRoom=false;
	private boolean keachinRoom=false;
	private boolean livingRoom=false;
	private boolean bathRoom=false;
	private boolean doorRoom=false;
	private boolean num1Room=false;
	private boolean num2Room=false;
	
	private static Light light = new Light();
	
	private Light(){
		
	}
	
	public static Light getInstance(){
		return light;
	}

	public boolean isBoilRoom() {
		return boilRoom;
	}

	public void setBoilRoom(boolean boilRoom) {
		this.boilRoom = boilRoom;
	}

	public boolean isMultiRoom() {
		return multiRoom;
	}

	public void setMultiRoom(boolean multiRoom) {
		this.multiRoom = multiRoom;
	}

	public boolean isInnerRoom() {
		return innerRoom;
	}

	public void setInnerRoom(boolean innerRoom) {
		this.innerRoom = innerRoom;
	}

	public boolean isKeachinRoom() {
		return keachinRoom;
	}

	public void setKeachinRoom(boolean keachinRoom) {
		this.keachinRoom = keachinRoom;
	}

	public boolean isLivingRoom() {
		return livingRoom;
	}

	public void setLivingRoom(boolean livingRoom) {
		this.livingRoom = livingRoom;
	}

	public boolean isBathRoom() {
		return bathRoom;
	}

	public void setBathRoom(boolean bathRoom) {
		this.bathRoom = bathRoom;
	}

	public boolean isDoorRoom() {
		return doorRoom;
	}

	public void setDoorRoom(boolean doorRoom) {
		this.doorRoom = doorRoom;
	}

	public boolean isNum1Room() {
		return num1Room;
	}

	public void setNum1Room(boolean num1Room) {
		this.num1Room = num1Room;
	}

	public boolean isNum2Room() {
		return num2Room;
	}

	public void setNum2Room(boolean num2Room) {
		this.num2Room = num2Room;
	}

	public static Light getLight() {
		return light;
	}

	public static void setLight(Light light) {
		Light.light = light;
	}
	
	
		  
		  
}
