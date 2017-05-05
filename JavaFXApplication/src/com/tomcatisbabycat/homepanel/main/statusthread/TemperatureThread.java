/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main.statusthread;

import com.tomcatisbabycat.homepanel.resources.icons.IconSelector;
import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ijeongsu
 */
public class TemperatureThread extends Thread{
	private SampleStatus samplestatus=SampleStatus.getInstance();
	private ImageView imgMainTemperature;
	private Label lblMainTemperature;

	public TemperatureThread(ImageView imgMainTemperature, Label lblMainTemperature) {
		this.imgMainTemperature = imgMainTemperature;
		this.lblMainTemperature = lblMainTemperature;
	}

	
	@Override
	public void run() {
		while(true){
			Platform.runLater(() -> {
				lblMainTemperature.setText(samplestatus.getTemperature()+"°");
			});
			
			if(samplestatus.getTemperature()<20.0){
				Platform.runLater(() -> {
					imgMainTemperature.setImage(new Image(IconSelector.class.getResource("temperature-2.png").toString()));
				});
			}else if(samplestatus.getTemperature()>=20.0&&samplestatus.getTemperature()<30.0){
				Platform.runLater(() -> {
					imgMainTemperature.setImage(new Image(IconSelector.class.getResource("temperature-3.png").toString()));
				});
			}else{
				Platform.runLater(() -> {
					imgMainTemperature.setImage(new Image(IconSelector.class.getResource("temperature-4.png").toString()));
				});
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ex) {
			}
		}

	}
}
