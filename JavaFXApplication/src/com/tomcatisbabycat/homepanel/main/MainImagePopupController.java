/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ijeongsu
 */
public class MainImagePopupController implements Initializable {

	@FXML
	private Rectangle recPopupBackground;
	@FXML
	private ImageView imgPopup;
	@FXML
	private Button btnExit;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Timeline timeline = new Timeline();
		recPopupBackground.setOpacity(0);
		imgPopup.setOpacity(0);
		KeyValue keyvalue = new KeyValue(recPopupBackground.opacityProperty(), 0.7);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(200), (event) -> {
			Timeline timeline2 = new Timeline();
			KeyValue keyvalue2 = new KeyValue(imgPopup.opacityProperty(), 1);
			KeyFrame keyFrame2 = new KeyFrame(Duration.millis(200), keyvalue2);
			timeline2.getKeyFrames().add(keyFrame2);
			timeline2.play();
		}, keyvalue);

		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
	}

	
	
	

}
