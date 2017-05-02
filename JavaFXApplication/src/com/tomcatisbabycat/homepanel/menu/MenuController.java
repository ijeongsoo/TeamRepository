/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.menu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kang
 */
public class MenuController implements Initializable {

	@FXML
	private Button btnControlHome;
	@FXML
	private StackPane menuStackPane;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnControlHome.setOnAction(event -> {
			handleBtnControlHome(event);
		});
	}

	private void handleBtnControlHome(ActionEvent event) {
		StackPane rootPane = (StackPane) btnControlHome.getScene().getRoot();

		menuStackPane.setTranslateX(0);

		KeyValue keyValue = new KeyValue(menuStackPane.translateXProperty(), 800, Interpolator.EASE_BOTH);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(3),
				e -> {
					rootPane.getChildren().remove(menuStackPane);
				}, keyValue);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();
	}

}
