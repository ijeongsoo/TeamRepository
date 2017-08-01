/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.garfish.hardware;

import com.pi4j.io.gpio.Pin;

/**
 *
 * @author ijeongsu
 */
public class PwmFowader {
	//field	
	private PCA9685 pca9685;
	private Pin pwm; //pca9685.PWM_00~pca9685.PWM_15
	
	
	//constructor
	public PwmFowader(PCA9685 pca9685, Pin pwm ){
		this.pca9685=pca9685;
		this.pwm=pwm;
	}
	
	//method
	
	public void setPWM(int duration){
		pca9685.setDuration(pwm, duration);
		
	}
}
