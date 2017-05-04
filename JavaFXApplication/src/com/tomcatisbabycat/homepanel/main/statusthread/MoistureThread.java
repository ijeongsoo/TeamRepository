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
public class MoistureThread extends Thread{
	private SampleStatus samplestatus=SampleStatus.getInstance();
	private ImageView imgMainMoisture;
	private Label lblMainMoisture;

	public MoistureThread(ThreadGroup threadGroup, String threadName,ImageView imgMainMoisture, Label lblMainMoisture) {
		super(threadGroup, threadName);
		this.imgMainMoisture = imgMainMoisture;
		this.lblMainMoisture = lblMainMoisture;
	}

	

	
	@Override
	public void run() {
		Image cactusImage=new Image(IconSelector.class.getResource("cactus.png").toString());
		Image dropsImage=new Image(IconSelector.class.getResource("drops.png").toString());
		while(true){
			if(Thread.interrupted()){
				break;
			}
			Platform.runLater(() -> {
				lblMainMoisture.setText(samplestatus.getMoisture()+"%");
			});
			
			if(samplestatus.getMoisture()<50.0){
				Platform.runLater(() -> {
					imgMainMoisture.setImage(cactusImage);
				});
			}else{
				Platform.runLater(() -> {
					imgMainMoisture.setImage(dropsImage);
				});
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ex) {
				break;
			}
		}
		System.out.println("moisture스레드 종료");
	}

}
