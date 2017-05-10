/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main.statusthread;

import com.tomcatisbabycat.homepanel.resources.icons.IconSelector;
import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
import java.util.Calendar;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

/**
 *
 * @author ijeongsu
 */
public class ClockAndEnvThread extends Thread {

	private Line houreHand;
	private Line minuateHand;
	private Line secondHand;
	private Label lblMainClock;
	private Label lblMainYear;
	private Label lblMainMonth;
	private Label lblMainDate;
	private Label lblMainDay;
	private ImageView imgMainDust;
	private Label lblMainDust;
	private ImageView imgMainMoisture;
	private Label lblMainMoisture;
	private ImageView imgMainTemperature;
	private Label lblMainTemperature;
	private SampleStatus samplestatus = SampleStatus.getInstance();
	
	Image forestImage = new Image(IconSelector.class.getResource("forest.png").toString());
	Image hillsImage = new Image(IconSelector.class.getResource("hills.png").toString());
	Image fieldsImage = new Image(IconSelector.class.getResource("fields.png").toString());
	Image capeImage = new Image(IconSelector.class.getResource("cape.png").toString());
	Image cactusImage = new Image(IconSelector.class.getResource("cactus.png").toString());
	Image dropsImage = new Image(IconSelector.class.getResource("drops.png").toString());
	Image coldImage = new Image(IconSelector.class.getResource("temperature-2.png").toString());
	Image sosoImage = new Image(IconSelector.class.getResource("temperature-3.png").toString());
	Image hotImage = new Image(IconSelector.class.getResource("temperature-4.png").toString());
	
	

	public ClockAndEnvThread(
		  ThreadGroup threadGroup, 
		  String threadName,
		  Line houreHand, 
		  Line minuateHand, 
		  Line secondHand, 
		  Label lblMainClock,
		  Label lblMainYear,
		  Label lblMainMonth,
		  Label lblMainDate,
		  Label lblMainDay,
		  ImageView imgMainDust,
		  Label lblMainDust,
		  ImageView imgMainMoisture,
		  Label lblMainMoisture,
		  ImageView imgMainTemperature,
		  Label lblMainTemperature
		  ) {
		super(threadGroup, threadName);
		this.houreHand = houreHand;
		this.minuateHand = minuateHand;
		this.secondHand = secondHand;
		this.lblMainClock=lblMainClock;
		this.lblMainYear=lblMainYear;
		this.lblMainMonth=lblMainMonth;
		this.lblMainDate=lblMainDate;
		this.lblMainDay=lblMainDay;
		this.lblMainDust=lblMainDust;
		this.imgMainDust=imgMainDust;
		this.lblMainMoisture=lblMainMoisture;
		this.imgMainMoisture=imgMainMoisture;
		this.imgMainTemperature=imgMainTemperature;
		this.lblMainTemperature=lblMainTemperature;
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
			if(Thread.interrupted()){
				break;
			}
			calendar = Calendar.getInstance();
			int hour =calendar.get(Calendar.HOUR);
			String hourstr;
			if(hour<10) hourstr="0"+hour;
			else hourstr=String.valueOf(hour);
			
			int minuate = calendar.get(Calendar.MINUTE);
			String minuatestr;
			if(minuate<10) minuatestr="0"+minuate;
			else minuatestr=String.valueOf(minuate);
			
			int second = calendar.get(Calendar.SECOND);
			String secondstr;
			if(second<10) secondstr="0"+second;
			else secondstr=String.valueOf(second);
			
			String ampm;
			if(calendar.get(Calendar.AM_PM)==1){
				ampm="PM";
			}else{
				ampm="AM";
			}
			
			int year =calendar.get(Calendar.YEAR);
			String yearstr=String.valueOf(year);
			
			int month=calendar.get(Calendar.MONTH)+1;
			String monthstr;
			if(month<10) monthstr="0"+month;
			else monthstr=String.valueOf(month);
			
			int day=calendar.get(Calendar.DAY_OF_WEEK);

			String daystr=null;
			switch(day){
				case 1: daystr="Sun";
				break;
				case 2: daystr="Mon";
				break;
				case 3: daystr="Tue";
				break;
				case 4: daystr="Wed";
				break;
				case 5: daystr="Thu";
				break;
				case 6: daystr="Fri";
				break;
				case 7: daystr="Sat";
				break;
			}
			String daystrTemp=daystr;
			
			int date=calendar.get(Calendar.DAY_OF_MONTH);
			String datestr;
			if(date<10) datestr="0"+date;
			else datestr=String.valueOf(date);
			
			//시간*30+분
			secondRotation.setAngle(second*6);
			minuateRotation.setAngle(minuate*6+second*0.1);
			hourRotation.setAngle(hour*30+minuate*0.5);
			
			

			Platform.runLater(() -> {
				lblMainClock.setText(ampm+" "+hourstr+":"+minuatestr+":"+secondstr);
				lblMainYear.setText(yearstr);
				lblMainMonth.setText(monthstr);
				lblMainDate.setText(datestr);
				if(day==1||day==7){
					lblMainDay.setTextFill(Color.RED);
				}else{
					lblMainDay.setTextFill(Color.rgb(97, 121, 137));

				}
				lblMainDay.setText(daystrTemp);
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
			
			
			if(samplestatus.getMoisture()<50.0){
				Platform.runLater(() -> {
					imgMainMoisture.setImage(cactusImage);
				});
			}else{
				Platform.runLater(() -> {
					imgMainMoisture.setImage(dropsImage);
				});
			}
			
			
			Platform.runLater(() -> {
				lblMainTemperature.setText(samplestatus.getTemperature()+"°");
				lblMainMoisture.setText(samplestatus.getMoisture()+"%");
								lblMainDust.setText(samplestatus.getDust()+"㎍/㎥");

			});
			
			if(samplestatus.getTemperature()<20.0){
				Platform.runLater(() -> {
					imgMainTemperature.setImage(coldImage);
				});
			}else if(samplestatus.getTemperature()>=20.0&&samplestatus.getTemperature()<30.0){
				Platform.runLater(() -> {
					imgMainTemperature.setImage(sosoImage);
				});
			}else{
				Platform.runLater(() -> {
					imgMainTemperature.setImage(hotImage);
				});
			}
			
			
			
			try {Thread.sleep(1000);} catch (InterruptedException ex) {break;}
			
			
		}
		
		System.out.println("clock스레드 종료");
		System.gc();
	}
	

}


