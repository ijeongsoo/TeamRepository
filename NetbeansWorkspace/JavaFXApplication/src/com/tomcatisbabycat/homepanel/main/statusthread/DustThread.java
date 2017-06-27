/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main.statusthread;

import com.tomcatisbabycat.homepanel.resources.icons.IconSelector;
import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
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
	

	public DustThread(ThreadGroup threadGroup, String threadName,ImageView imgMainDust, Label lblMainDust) {
		super(threadGroup, threadName);
		this.imgMainDust = imgMainDust;
		this.lblMainDust = lblMainDust;
	}

	

	

	
	@Override
	public void run() {
		Image forestImage=new Image(IconSelector.class.getResource("forest.png").toString());
		Image hillsImage=new Image(IconSelector.class.getResource("hills.png").toString());
		Image fieldsImage=new Image(IconSelector.class.getResource("fields.png").toString());
		Image capeImage=new Image(IconSelector.class.getResource("cape.png").toString());
		while(true){
			if(Thread.interrupted()){
				break;
			}
			Platform.runLater(() -> {
				lblMainDust.setText(samplestatus.getDust()+"㎍/㎥");
			});
			
			if(samplestatus.getDust()>=0&&samplestatus.getDust()<=30){
				Platform.runLater(() -> {
					imgMainDust.setImage(forestImage);
				});
			}else if(samplestatus.getDust()>30&&samplestatus.getDust()<=80){
				Platform.runLater(() -> {
					imgMainDust.setImage(hillsImage);
				});
			}else if(samplestatus.getDust()>80&&samplestatus.getDust()<=150){
				Platform.runLater(() -> {
					imgMainDust.setImage(fieldsImage);
				});
			}else{
				Platform.runLater(() -> {
					imgMainDust.setImage(capeImage);
				});
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ex) {
				break;
			}
		}
		System.out.println("더스트 스레드 종료");
		System.gc();

	}
}
