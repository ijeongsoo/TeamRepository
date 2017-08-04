/**
 *
 * @author 2Team(Lee, Kang, Cho)
 */
package com.tomcatisbabycat.garfish.hardware;

import com.pi4j.io.gpio.Pin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SG90ServoPCA9685Duration {
	// LOGGER
	private static final Logger LOG = LoggerFactory.getLogger(SG90ServoPCA9685Duration.class);
	
	// FIELD
	private PCA9685 pca9685;
	private Pin pin;
	private int angle;
	private int minDuration;
	private int maxDuration;
	
	// CONSTRUCTOR
	public SG90ServoPCA9685Duration(PCA9685 pca9685, Pin pin) {
		//0도(0.8ms = 800us)
		//180도(2.7ms = 2700us)
		this(pca9685, pin, 800, 2700);
	}
	
	public SG90ServoPCA9685Duration(PCA9685 pca9685, Pin pin, int minDuration, int maxDuration) {
		this.pca9685 = pca9685;
		this.pin = pin;
		this.minDuration = minDuration;
		this.maxDuration = maxDuration;
	}
	
	// METHOD
	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		if(angle<0) {
			angle = 0;
		} else if(angle>180) {
			angle = 180;
		}
		this.angle = angle;
		int duration = minDuration + (int)(angle*(maxDuration-minDuration)/180.0);
		this.pca9685.setDuration(pin, duration);
	}
}
