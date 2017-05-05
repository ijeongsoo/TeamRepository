/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main;

import com.tomcatisbabycat.homepanel.controller.knob.knobfx.Knob;
import com.tomcatisbabycat.homepanel.main.statusthread.WeatherThread;
import com.tomcatisbabycat.homepanel.main.statusthread.TemperatureThread;
import com.tomcatisbabycat.homepanel.main.statusthread.MoistureThread;
import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.main.statusthread.ClockThread;
import com.tomcatisbabycat.homepanel.main.statusthread.DustThread;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import com.tomcatisbabycat.homepanel.menu.*;
import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
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
	private StackPane stackPaneMain;

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
	@FXML
	private ImageView imgMainDust;
	@FXML
	private Label lblMainDust;
	@FXML
	private Knob knob;
	@FXML
	private Line houreHand;
	@FXML
	private Line minuateHand;
	@FXML
	private Line secondHand;
	

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		
		 /*Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 100)),
                new KeyFrame(Duration.seconds(10), new KeyValue(rotation.angleProperty(), 360))
		 );
		 timeline.play();*/
		
		knob.setValue(28);
			
			knob.setOnMouseDragged((event) -> {
				  //System.out.println(Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI);
			  if((Math.atan2(knob.getHeight()/2-event.getY(),knob.getWidth()/2-event.getX())*180/Math.PI)>0)
				   knob.setValue((Math.atan2(knob.getHeight()/2-event.getY(),knob.getWidth()/2-event.getX())*180/Math.PI)/3);
			  else if(((Math.atan2(knob.getHeight()/2-event.getY(),knob.getWidth()/2-event.getX())*180/Math.PI))/3>-30)
					knob.setValue(0);
			else if(((Math.atan2(knob.getHeight()/2-event.getY(),knob.getWidth()/2-event.getX())*180/Math.PI))/3<-30)
				  knob.setValue(60);
			});
			
			knob.setOnMouseClicked((event) -> {
				  System.out.println(event.getX()+","+event.getY());
			});
			
			knob.valueProperty().addListener(new ChangeListener<Number>(){
				  @Override
				  public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						
						//tempo.setText(newValue.intValue()+"°");
				  };
			});
		
		
		
		
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
				samplestatus.setDust(160);
				break;
				case 1:samplestatus.setWeather("cloudy");
				samplestatus.setMoisture(20);
				break;
				case 2:samplestatus.setWeather("rainny");
				break;
				case 3:samplestatus.setWeather("snow");
				samplestatus.setTemperature(8.2);
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
		
		DustThread dustThread = new DustThread(imgMainDust, lblMainDust);
		dustThread.setDaemon(true);
		dustThread.start();
		
		ClockThread clockThread = new ClockThread(houreHand, minuateHand, secondHand);
		clockThread.setDaemon(true);
		clockThread.start();
		
		

	}
	
	
	

	private void handleBtnMenu(ActionEvent e) { // menu 화면 넘어가는 애니메이션 처리
		try {
			Parent parent = FXMLLoader.load(MenuController.class.getResource("menu.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			LockController.lockRootPane.getChildren().add(parent);
			
			parent.setTranslateX(800);
			
			KeyValue keyValueStackPaneMenu = new KeyValue(parent.translateXProperty(), 0);
			KeyFrame keyFrameStackPaneMenu = new KeyFrame(Duration.seconds(1), keyValueStackPaneMenu);
			
			KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);

			Timeline timeline = new Timeline();
			//timeline.getKeyFrames().addAll(keyFrameStackPaneMenu, keyFrameStackPaneMain);
			timeline.getKeyFrames().addAll(keyFrameStackPaneMenu);
			timeline.play();
			//System.out.println(timeline.getStatus());
			//System.out.println(timeline.getStatus().toString());
			// 메뉴버튼을 클릭하면 메뉴화면생성과 동시에 메인화면을 삭제한다.
			timeline.statusProperty().addListener((observable, oldValue, newValue) -> {
				//System.out.println(newValue);
				if(newValue.toString().equals("STOPPED")){
				Thread task = new Thread(){
					@Override
					public void run() {
						Platform.runLater(()->{LockController.lockRootPane.getChildren().remove(stackPaneMain);});
					}

				};
				task.start();
			}
			});
			//LockController.lockRootPane.getChildren().remove(stackPaneMain);
			
			
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
