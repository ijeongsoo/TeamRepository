/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.menu;

import com.tomcatisbabycat.homepanel.cctv.CctvController;
import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.main.MainController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
	@FXML
	private Button btnCCTV;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnControlHome.setOnAction(event -> {
			handleBtnControlHome(event);
		});
		btnCCTV.setOnAction(event -> {
			handleBtnCCTV(event);
		});
	}

	private void handleBtnControlHome(ActionEvent event) {
		StackPane rootPane = (StackPane) btnControlHome.getScene().getRoot();

		menuStackPane.setTranslateX(0);

		KeyValue keyValue = new KeyValue(menuStackPane.translateXProperty(), 800);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
			  e -> {
				  rootPane.getChildren().remove(menuStackPane);
			  }, keyValue);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();

		Thread task = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					Platform.runLater(() -> {
						try {
							LockController.lockRootPane.getChildren().add(FXMLLoader.load(MainController.class.getResource("main.fxml")));
						} catch (IOException ex) {
							Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
						}

					});
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		};
		task.start();
	}

	private void handleBtnCCTV(ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(CctvController.class.getResource("cctv.fxml"));

			menuStackPane.getChildren().add(parent);

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
