/**
 *
 * @author 2Team(Lee, Kang, Cho)
 */
package com.tomcatisbabycat.garfish.hardware;

import com.pi4j.io.gpio.Pin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PwmFowader {
	// LOGGER
	private static final Logger LOG = LoggerFactory.getLogger(PwmFowader.class);
	
	// FIELD	
	private PCA9685 pca9685;
	private Pin pwm; //pca9685.PWM_00~pca9685.PWM_15
	
	
	// CONSTRUCTOR
	public PwmFowader(PCA9685 pca9685, Pin pwm ){
		this.pca9685=pca9685;
		this.pwm=pwm;
	}
	
	// METHOD
	public void setPWM(int duration){
		pca9685.setDuration(pwm, duration);
	}
}
