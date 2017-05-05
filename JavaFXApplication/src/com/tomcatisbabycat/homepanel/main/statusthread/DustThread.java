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
public class DustThread extends Thread{
	private SampleStatus samplestatus=SampleStatus.getInstance();
	private ImageView imgMainDust;
	private Label lblMainDust;

	public DustThread(ImageView imgMainDust, Label lblMainDust) {
		this.imgMainDust = imgMainDust;
		this.lblMainDust = lblMainDust;
	}

	

	

	
	@Override
	public void run() {
		while(true){
			Platform.runLater(() -> {
				lblMainDust.setText(samplestatus.getDust()+"");
			});
			
			if(samplestatus.getDust()>=0&&samplestatus.getDust()<=30){
				Platform.runLater(() -> {
					imgMainDust.setImage(new Image(IconSelector.class.getResource("forest.png").toString()));
				});
			}else if(samplestatus.getDust()>30&&samplestatus.getDust()<=80){
				Platform.runLater(() -> {
					imgMainDust.setImage(new Image(IconSelector.class.getResource("hills.png").toString()));
				});
			}else if(samplestatus.getDust()>80&&samplestatus.getDust()<=150){
				Platform.runLater(() -> {
					imgMainDust.setImage(new Image(IconSelector.class.getResource("fields.png").toString()));
				});
			}else{
				Platform.runLater(() -> {
					imgMainDust.setImage(new Image(IconSelector.class.getResource("cape.png").toString()));
				});
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ex) {
			}
		}

	}
}
