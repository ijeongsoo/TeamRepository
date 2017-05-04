/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main;

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

	public WeatherThread(ImageView mainWeatherImage, ImageView mainWeatherImageBack, ImageView mainWeatherImageBack2) {
		this.mainWeatherImage = mainWeatherImage;
		this.mainWeatherImageBack = mainWeatherImageBack;
		this.mainWeatherImageBack2 = mainWeatherImageBack2;
	}

	@Override
	public void run() {
		double rotate = 0;
		double movepoint = 0;
		double rainrotate=0;
		while (true) {
			if (samplestatus.getWeather().equals("sunny")) {
				mainWeatherImage.setX(0);
				mainWeatherImageBack.setVisible(false);
				mainWeatherImage.setLayoutX(242);
				mainWeatherImage.setLayoutY(34);
				mainWeatherImageBack2.setVisible(false);
				mainWeatherImage.setImage(new Image(WeatherIconSelector.class.getResource("sunny.png").toString()));
				
				double rotateTemp = rotate;
				Platform.runLater(() -> {
					mainWeatherImage.setRotate(rotateTemp);
				});
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex) {
				}
				rotate += 10;
				if (rotate == 360) {
					rotate = 0;
				}
			} else if (samplestatus.getWeather().equals("cloudy")) {
				mainWeatherImage.setRotate(0);
				mainWeatherImageBack.setVisible(true);
				mainWeatherImageBack2.setVisible(false);
				mainWeatherImage.setLayoutX(242);
				mainWeatherImage.setLayoutY(34);
				mainWeatherImageBack.setImage(new Image(WeatherIconSelector.class.getResource("sunny.png").toString()));
				mainWeatherImage.setImage(new Image(WeatherIconSelector.class.getResource("cloud.png").toString()));
				
				while (movepoint < 15.0) {
					double movepointTemp = movepoint;
					Platform.runLater(() -> {
						mainWeatherImage.setX(movepointTemp);
					});
					try {
						Thread.sleep(10);
					} catch (InterruptedException ex) {
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
					}
					movepoint -= 0.1;
				}
			} else if (samplestatus.getWeather().equals("rainny")) {
				mainWeatherImage.setRotate(0);
				mainWeatherImageBack.setVisible(false);
				mainWeatherImageBack2.setVisible(true);
				mainWeatherImageBack2.setPreserveRatio(false);
				mainWeatherImageBack2.setLayoutX(235);
				mainWeatherImageBack2.setLayoutY(8);
				mainWeatherImageBack2.setFitWidth(133);
				mainWeatherImageBack2.setFitHeight(61);
				mainWeatherImage.setImage(new Image(WeatherIconSelector.class.getResource("umbrella.png").toString()));
				mainWeatherImageBack2.setImage(new Image(WeatherIconSelector.class.getResource("raining.png").toString()));
				
				Platform.runLater(() -> {
					mainWeatherImageBack2.toFront();
				});
				mainWeatherImage.setLayoutX(242);
				mainWeatherImage.setLayoutY(34);
				mainWeatherImage.setX(0);
				double rotateTemp = rainrotate;
				Platform.runLater(() -> {
					mainWeatherImageBack2.setRotate(rotateTemp);
				});
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex) {
				}
				rainrotate += 180;
				if (rainrotate == 360) {
					rainrotate = 0;
				}

			}else if (samplestatus.getWeather().equals("snow")) {
				mainWeatherImageBack.setVisible(false);
				mainWeatherImageBack2.setVisible(true);
				mainWeatherImageBack2.setPreserveRatio(true);
				mainWeatherImageBack2.setLayoutX(258);
				mainWeatherImageBack2.setLayoutY(75);
				mainWeatherImageBack2.setFitWidth(75);
				mainWeatherImageBack2.setFitHeight(75);
				Platform.runLater(() -> {
					mainWeatherImageBack2.toBack();
				});
				mainWeatherImage.setLayoutX(232);
				mainWeatherImage.setLayoutY(10);
				mainWeatherImage.setRotate(0);
				mainWeatherImage.setX(0);
				mainWeatherImage.setImage(new Image(WeatherIconSelector.class.getResource("clouds.png").toString()));
				mainWeatherImageBack2.setImage(new Image(WeatherIconSelector.class.getResource("snowing.png").toString()));
				
				double rotateTemp = rainrotate;
				Platform.runLater(() -> {
					mainWeatherImageBack2.setRotate(rotateTemp);
				});
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex) {
				}
				rainrotate += 180;
				if (rainrotate == 360) {
					rainrotate = 0;
				}
				

			}
		}
	}

}
