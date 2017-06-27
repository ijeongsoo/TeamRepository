/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main.statusthread;

import com.tomcatisbabycat.homepanel.resources.weatherIcon.WeatherIconSelector;
import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ijeongsu
 */
public class WeatherThread extends Thread {
	private SampleStatus samplestatus=SampleStatus.getInstance();
	private ImageView mainWeatherImage;
	private ImageView mainWeatherImageBack;
	private ImageView mainWeatherImageBack2;

	public WeatherThread(ThreadGroup threadGroup, String threadName,ImageView mainWeatherImage, ImageView mainWeatherImageBack, ImageView mainWeatherImageBack2) {
		super(threadGroup, threadName);
		this.mainWeatherImage = mainWeatherImage;
		this.mainWeatherImageBack = mainWeatherImageBack;
		this.mainWeatherImageBack2 = mainWeatherImageBack2;
	}

	@Override
	public void run() {
		double rotate = 0;
		double movepoint = 0;
		double rainrotate=0;
		boolean stop=false;
		
		Image sunnyImage=new Image(WeatherIconSelector.class.getResource("sunny.png").toString());
		Image cloudImage=new Image(WeatherIconSelector.class.getResource("cloud.png").toString());
		Image umbrellaImage=new Image(WeatherIconSelector.class.getResource("umbrella.png").toString());
		Image rainImage=new Image(WeatherIconSelector.class.getResource("raining.png").toString());
		Image cloudsImage=new Image(WeatherIconSelector.class.getResource("clouds.png").toString());
		Image snowImage=new Image(WeatherIconSelector.class.getResource("snowing.png").toString());
		while (true) {
			if(Thread.interrupted()||stop==true){
				break;
			}
			if (samplestatus.getWeather().equals("sunny")) {
				mainWeatherImage.setX(0);
				mainWeatherImageBack.setVisible(false);
				mainWeatherImage.setLayoutX(254);
				mainWeatherImage.setLayoutY(22);
				mainWeatherImageBack2.setVisible(false);
				mainWeatherImage.setImage(sunnyImage);
				
				double rotateTemp = rotate;
				Platform.runLater(() -> {
					mainWeatherImage.setRotate(rotateTemp);
				});
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex) {
					break;
				}
				rotate += 10;
				if (rotate == 360) {
					rotate = 0;
				}
			} else if (samplestatus.getWeather().equals("cloudy")) {
				mainWeatherImage.setRotate(0);
				mainWeatherImageBack.setVisible(true);
				mainWeatherImageBack2.setVisible(false);
				mainWeatherImage.setLayoutX(250);
				mainWeatherImage.setLayoutY(26);
				mainWeatherImageBack.setImage(sunnyImage);
				mainWeatherImage.setImage(cloudImage);
				
				while (movepoint < 15.0) {
					double movepointTemp = movepoint;
					Platform.runLater(() -> {
						mainWeatherImage.setX(movepointTemp);
					});
					try {
						Thread.sleep(10);
					} catch (InterruptedException ex) {
						stop=true;
						break;
					}
					movepoint += 0.1;
				}
				while (movepoint > -15.0) {
					double movepointTemp = movepoint;
					Platform.runLater(() -> {
						mainWeatherImage.setX(movepointTemp);
					});
					try {
						Thread.sleep(10);
					} catch (InterruptedException ex) {
						stop=true;
						break;
					}
					movepoint -= 0.1;
				}
			} else if (samplestatus.getWeather().equals("rainny")) {
				mainWeatherImage.setRotate(0);
				mainWeatherImageBack.setVisible(false);
				mainWeatherImageBack2.setVisible(true);
				mainWeatherImageBack2.setPreserveRatio(false);
				mainWeatherImageBack2.setLayoutX(243);
				mainWeatherImageBack2.setLayoutY(8);
				mainWeatherImageBack2.setFitWidth(141);
				mainWeatherImageBack2.setFitHeight(53);
				mainWeatherImage.setImage(umbrellaImage);
				mainWeatherImageBack2.setImage(rainImage);
				
				Platform.runLater(() -> {
					mainWeatherImageBack2.toFront();
				});
				mainWeatherImage.setLayoutX(250);
				mainWeatherImage.setLayoutY(26);
				mainWeatherImage.setX(0);
				double rotateTemp = rainrotate;
				Platform.runLater(() -> {
					mainWeatherImageBack2.setRotate(rotateTemp);
				});
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex) {
					break;
				}
				rainrotate += 180;
				if (rainrotate == 360) {
					rainrotate = 0;
				}

			}else if (samplestatus.getWeather().equals("snow")) {
				mainWeatherImageBack.setVisible(false);
				mainWeatherImageBack2.setVisible(true);
				mainWeatherImageBack2.setPreserveRatio(true);
				mainWeatherImageBack2.setLayoutX(270);
				mainWeatherImageBack2.setLayoutY(67);
				mainWeatherImageBack2.setFitWidth(75);
				mainWeatherImageBack2.setFitHeight(75);
				Platform.runLater(() -> {
					mainWeatherImageBack2.toBack();
				});
				mainWeatherImage.setLayoutX(250);
				mainWeatherImage.setLayoutY(2);
				mainWeatherImage.setRotate(0);
				mainWeatherImage.setX(0);
				mainWeatherImage.setImage(cloudsImage);
				mainWeatherImageBack2.setImage(snowImage);
				
				double rotateTemp = rainrotate;
				Platform.runLater(() -> {
					mainWeatherImageBack2.setRotate(rotateTemp);
				});
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex) {
					break;
				}
				rainrotate += 180;
				if (rainrotate == 360) {
					rainrotate = 0;
				}
				

			}
		}
		System.out.println("weather 스레드 종료");
	}

}
