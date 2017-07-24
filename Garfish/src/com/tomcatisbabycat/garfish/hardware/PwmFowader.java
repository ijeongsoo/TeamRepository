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

	public static void main(String[] args) throws Exception {
		
		System.out.println("start");
		PCA9685 pca9685= PCA9685.getInstance();
		PwmFowader roll = new PwmFowader(pca9685, PCA9685.PWM_01);
		PwmFowader throttle = new PwmFowader(pca9685, PCA9685.PWM_03);
		PwmFowader pitch = new PwmFowader(pca9685, PCA9685.PWM_02);
		PwmFowader yaw= new PwmFowader(pca9685, PCA9685.PWM_04);
		PwmFowader mod= new PwmFowader(pca9685, PCA9685.PWM_05);
		
		
		roll.setPWM(1500);
		pitch.setPWM(1500);
		yaw.setPWM(1500);
		throttle.setPWM(1000);
		mod.setPWM(1000);
		
		
		Thread.sleep(2000);
		
		
		yaw.setPWM(2000);
		
		
		Thread.sleep(4000);
				
		yaw.setPWM(1500);
		int i=1000;
		for(i=1000; i<1300; i+=10){
			System.out.println(i);
			throttle.setPWM(i);
			
			Thread.sleep(1000);
		}
		
		System.out.println("종료");
		throttle.setPWM(1000);
		
		System.in.read();
		
	}
	

}
