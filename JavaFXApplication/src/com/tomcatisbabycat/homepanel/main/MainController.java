/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main;

import com.tomcatisbabycat.homepanel.resources.images.ImageResourceFinder;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import com.tomcatisbabycat.homepanel.menu.*;
import com.tomcatisbabycat.homepanel.resources.weatherIcon.WeatherIconSelector;
import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ijeongsu
 */
public class MainController implements Initializable {

	@FXML
	private Button mainBtnLock;
	@FXML
	private Button mainBtnMenu;
	@FXML
	private ImageView mainImage;
	@FXML
	private BorderPane mainImagePane;
	@FXML
	private StackPane stackPane;

	@FXML
	private ImageView mainWeatherImage;
	@FXML
	private ImageView mainWeatherImageBack;

	private SampleStatus samplestatus = SampleStatus.getInstance();

	@FXML
	private ImageView mainWeatherImageBack2;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ImageView imgMainTemperature;
	@FXML
	private Label lblMainTemperature;
	@FXML
	private ImageView imgMainMoisture;
	@FXML
	private Label lblMainMoisture;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		//statusDetect();
		MainImageSet mainImageSet = new MainImageSet(mainImage);
		mainImageSet.mainImageSet();
		

		mainBtnMenu.setOnAction((event) -> {
			handleBtnMenu(event);
		});

		mainBtnLock.setOnAction((event) -> {
			int a = (int)(Math.random()*4);
			System.out.println(a);
			switch(a){
				case 0:samplestatus.setWeather("sunny");
				break;
				case 1:samplestatus.setWeather("cloudy");
				break;
				case 2:samplestatus.setWeather("rainny");
				break;
				case 3:samplestatus.setWeather("snow");
				break;
			}
		});

		WeatherThread weatherThread = new WeatherThread(mainWeatherImage, mainWeatherImageBack, mainWeatherImageBack2);
		weatherThread.setDaemon(true);
		weatherThread.start();
		
		TemperatureThread temperatureThread = new TemperatureThread(imgMainTemperature, lblMainTemperature);
		temperatureThread.setDaemon(true);
		temperatureThread.start();
		
		MoistureThread moistureThread = new MoistureThread(imgMainMoisture, lblMainMoisture);
		moistureThread.setDaemon(true);
		moistureThread.start();

	}

	private void handleBtnMenu(ActionEvent e) { // menu 화면 넘어가는 애니메이션 처리
		try {
			Parent parent = FXMLLoader.load(MenuController.class.getResource("menu.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			stackPane.getChildren().add(parent);
			
			parent.setTranslateX(800);
			KeyValue keyValue = new KeyValue(parent.translateXProperty(), 0);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().add(keyFrame);
			
			timeline.play();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
