/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.light;

import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.main.MainController;
import com.tomcatisbabycat.homepanel.menu.MenuController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kang
 */
public class LightController implements Initializable {

	@FXML
	private StackPane lightStackPane;
	@FXML
	private Button btnControlHome;
	@FXML
	private Button btnControlLock;
	@FXML
	private Button btnControlBack;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnControlLock.setOnAction(event -> {
			handleBtnControlLock(event);
		});
		btnControlHome.setOnAction(event -> {
			handleBtnControlHome(event);
		});
		btnControlBack.setOnAction(event->{
			handleBtnControlBack(event);
		});
	}
	private void handleBtnControlBack(ActionEvent event){
		lightStackPane.setTranslateX(0);
		
		KeyValue keyValueStackPaneLight = new KeyValue(lightStackPane.translateXProperty(), 800);
		KeyFrame keyFrameStackPaneLight = new KeyFrame(Duration.seconds(1), 
			  e->{
				LockController.lockRootPane.getChildren().remove(2);
		}, keyValueStackPaneLight);
		
		Timeline timeline = new Timeline(keyFrameStackPaneLight);
		timeline.play();
		
		try {
			LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MenuController.class.getResource("menu.fxml")));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	

	private void handleBtnControlLock(ActionEvent event) {
		//StackPane rootPane = (StackPane) lightStackPane.getScene().getRoot(); // 컨트롤을 통해서 현재 Scene을 얻고 root의 객체를 얻는다.

		lightStackPane.setTranslateX(0);
		KeyValue keyValueStackPaneLight = new KeyValue(lightStackPane.translateXProperty(), 800);
		KeyFrame keyFrameStackPaneLight = new KeyFrame(Duration.seconds(1),
			  e -> {
				  //
				  LockController.lockRootPane.getChildren().remove(lightStackPane);
			  }, keyValueStackPaneLight);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrameStackPaneLight);

		timeline.play();
	}

	private void handleBtnControlHome(ActionEvent event) {

		KeyValue keyValueStackPaneLight = new KeyValue(lightStackPane.translateXProperty(), 800);
		KeyFrame keyFrameStackPaneLight = new KeyFrame(Duration.seconds(1),
			  e -> {
				  LockController.lockRootPane.getChildren().remove(2, LockController.lockRootPane.getChildren().size());
			  }, keyValueStackPaneLight);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrameStackPaneLight);

		timeline.play();
		try {
			LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MainController.class.getResource("main.fxml")));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}	
	
}
