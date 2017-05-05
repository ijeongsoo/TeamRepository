/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main.statusthread;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

/**
 *
 * @author ijeongsu
 */
public class ClockThread extends Thread {

	private Line houreHand;
	private Line minuateHand;
	private Line secondHand;

	public ClockThread(Line houreHand, Line minuateHand, Line secondHand) {
		this.houreHand = houreHand;
		this.minuateHand = minuateHand;
		this.secondHand = secondHand;
	}

	@Override
	public void run() {
		Rotate hourRotation = new Rotate();
		hourRotation.pivotXProperty().bind(houreHand.startXProperty());
		hourRotation.pivotYProperty().bind(houreHand.startYProperty());
		houreHand.getTransforms().add(hourRotation);
		
		Rotate minuateRotation = new Rotate();
		minuateRotation.pivotXProperty().bind(minuateHand.startXProperty());
		minuateRotation.pivotYProperty().bind(minuateHand.startYProperty());
		minuateHand.getTransforms().add(minuateRotation);
		
		Rotate secondRotation = new Rotate();
		secondRotation.pivotXProperty().bind(secondHand.startXProperty());
		secondRotation.pivotYProperty().bind(secondHand.startYProperty());
		secondHand.getTransforms().add(secondRotation);
		
		Calendar calendar;
		while(true){
			calendar = Calendar.getInstance();
			int hour =calendar.get(Calendar.HOUR);
			int minuate = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			try {Thread.sleep(1000);} catch (InterruptedException ex) {}
			//시간*30+분
			hourRotation.setAngle(hour*30+minuate*0.5+second*(1/120));
			
			minuateRotation.setAngle(minuate*6+second*0.1);
			secondRotation.setAngle(second*6);
			System.out.println(hour+":"+minuate+":"+second);
			
		}
		
	}

}


