/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.schedule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kang
 */
public class ScheduleDeleteController implements Initializable {

	@FXML
	private Button btnOk;
	@FXML
	private Button btnCancel;
	@FXML
	private AnchorPane deleteAnchorPane;
	@FXML
	private Rectangle deleteBackground;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnCancel.setFocusTraversable(false);
		btnOk.setFocusTraversable(false);
		
		Timeline timeline = new Timeline();
		deleteBackground.setOpacity(0);
		deleteAnchorPane.setOpacity(0);
		KeyValue keyvalue = new KeyValue(deleteBackground.opacityProperty(), 0.7);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(200), (event) -> {
			Timeline timeline2 = new Timeline();
			KeyValue keyvalue2 = new KeyValue(deleteAnchorPane.opacityProperty(), 1);
			KeyFrame keyFrame2 = new KeyFrame(Duration.millis(200), keyvalue2);
			timeline2.getKeyFrames().add(keyFrame2);
			timeline2.play();
		}, keyvalue);

		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
	}	

	@Override
	protected void finalize() throws Throwable {
		System.out.println("스케쥴 delete제거");
	}
	
}
