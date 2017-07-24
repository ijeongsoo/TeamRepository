/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.samplestatus;

import com.tomcatisbabycat.homepanel.sensor.GasSensor;
import com.tomcatisbabycat.homepanel.sensor.PhotoresistorSensor;
import com.tomcatisbabycat.homepanel.sensor.ThermistorSensor;
import com.tomcatisbabycat.homepanel.sensor.TrackingSensor;
import com.tomcatisbabycat.homepanel.sensor.UltrasonicSensor;

/**
 *
 * @author kang
 */
public class SensorStatus {
	private static SensorStatus sensorStatus = new SensorStatus();
	
	private GasSensor gasSensor = GasSensor.getInstance();
	private PhotoresistorSensor photoresistorSensor = PhotoresistorSensor.getInstance();
	private ThermistorSensor thermistorSensor = ThermistorSensor.getInstance();
	private TrackingSensor trackingSensor = TrackingSensor.getInstance();
	private UltrasonicSensor ultrasonicSensor = UltrasonicSensor.getInstance();
	
	private double gas;
	private double photoresistor;
	private double thermistor;
	private int tracking;
	private int distance;
	
	private SensorStatus(){}
	
	public static SensorStatus getInstance(){
		return sensorStatus;
	}
	
	public double getGas(){
		gas = gasSensor.getGas();
		return gas;
	}
	public double getPhotoresistor(){
		photoresistor = photoresistorSensor.getPhotoresistor();
		return photoresistor;
	}
	public double getThermistor(){
		thermistor = thermistorSensor.getThermistor();
		return thermistor;
	}
	public int getTracking(){
		tracking = trackingSensor.getTracking();
		return tracking;
	}
	public int getUltrasonic(){
		distance = ultrasonicSensor.getDistance();
		return distance;
	}
}
