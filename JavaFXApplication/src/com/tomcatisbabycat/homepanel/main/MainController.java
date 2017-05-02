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
import javafx.animation.Interpolator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
	  
	  private StackPane mainPane;
	  @FXML
	  private ImageView mainWeatherImage;
	  @FXML
	  private ImageView mainWeatherImageBack;


	  /**
	   * Initializes the controller class.
	   */
	  @Override
	  public void initialize(URL url, ResourceBundle rb) {
			
			mainPane = stackPane;
			Image image = new Image(ImageResourceFinder.class.getResource(ImageResourceFinder.getImageFileName()).toString());
			double originalHight=image.getHeight();
			double originalWidth=image.getWidth();
			if(originalHight>originalWidth){
				  mainImage.setFitWidth(180);
				  mainImage.setFitHeight(0);
				  double resizeHeight=(180*originalHight)/originalWidth;
				  mainImage.setImage(image);
				  Circle clip = new Circle(mainImage.getFitWidth()/2,mainImage.getFitWidth()/2, mainImage.getFitWidth()/2);
				  mainImage.setClip(clip);
			}else{
				  double resizeWidth=(180*originalWidth)/originalHight;
				  mainImage.setImage(image);
				  Circle clip = new Circle(resizeWidth/2,mainImage.getFitHeight()/2, mainImage.getFitHeight()/2);
				  mainImage.setClip(clip);
			}
			
			
			mainBtnMenu.setOnAction((event) -> {
				  handleBtnMenu(event);
			});
			
			if(SampleStatus.weather.equals("sunny")){
				  mainWeatherImage.setImage(new Image(WeatherIconSelector.class.getResource("sunny.png").toString()));
				  Thread thread = new Thread(){
				  @Override
				  public void run() {
						double rotate=0;
						while(true){
							  double rotateTemp=rotate;
							  Platform.runLater(() -> {
									mainWeatherImage.setRotate(rotateTemp);
							  });
							  try {Thread.sleep(500);} catch (InterruptedException ex) {}
							  rotate+=10;
							  if(rotate==360)
									rotate=0;
						}
				  };		  
			};
			thread.setDaemon(true);
			thread.start();
			}else if(SampleStatus.weather.equals("cloudy")){
				  mainWeatherImageBack.setImage(new Image(WeatherIconSelector.class.getResource("sunny.png").toString()));
				  mainWeatherImage.setImage(new Image(WeatherIconSelector.class.getResource("cloud.png").toString()));				  
				  Thread thread = new Thread(){
				  @Override
				  public void run() {
						double rotate=0;
						while(true){
							  while(rotate<15.0){
									double rotateTemp=rotate;
									Platform.runLater(() -> {
										  mainWeatherImage.setX(rotateTemp);
									});
									try {Thread.sleep(10);} catch (InterruptedException ex) {}
									rotate+=0.1;
							  }
							  while(rotate>-15.0){
									double rotateTemp=rotate;
									Platform.runLater(() -> {
										  mainWeatherImage.setX(rotateTemp);
									});
									try {Thread.sleep(10);} catch (InterruptedException ex) {}
									rotate-=0.1;
							  }
						}
				  };		  
			};
			thread.setDaemon(true);
			thread.start();

			}
			
			
			
	  }	
	  private void handleBtnMenu(ActionEvent e){ // menu 화면 넘어가는 애니메이션 처리
		  try {
			  Parent parent = FXMLLoader.load(MenuController.class.getResource("menu.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근
			  
			  // 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			  stackPane.getChildren().add(parent);			  	
			  
			  parent.setTranslateX(800);
			  KeyValue keyValue = new KeyValue(parent.translateXProperty(), 0);
			  KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), keyValue);
			  
			  Timeline timeline = new Timeline();
			  timeline.getKeyFrames().add(keyFrame);
			  
			  timeline.play();
		  } catch (IOException ex) {
			  ex.printStackTrace();
		  }
	  }

	  
}