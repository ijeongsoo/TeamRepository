/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main.statusthread;

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
		Rotate rotation = new Rotate();
		rotation.pivotXProperty().bind(houreHand.startXProperty());
		rotation.pivotYProperty().bind(houreHand.startYProperty());
		houreHand.getTransforms().add(rotation);
		int aasdg = 0;
		while (aasdg < 13) {
			try {
				rotation.setAngle(aasdg * 20);
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
			}
			aasdg++;

		}
	}

}


