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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kang
 */
public class ScheduleAddController implements Initializable {

	@FXML
	private ComboBox<String> comboCategory;
	@FXML
	private ComboBox<String> comboName;

	ObservableList<String> name = FXCollections.observableArrayList();
	@FXML
	private Rectangle popupBackground;
	@FXML
	private TimeSpinner timeSpinner;
	@FXML
	private AnchorPane addAnchorPane;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Timeline timeline = new Timeline();
		popupBackground.setOpacity(0);
		addAnchorPane.setOpacity(0);
		KeyValue keyvalue = new KeyValue(popupBackground.opacityProperty(), 0.7);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(200), (event) -> {
			Timeline timeline2 = new Timeline();
			KeyValue keyvalue2 = new KeyValue(addAnchorPane.opacityProperty(), 1);
			KeyFrame keyFrame2 = new KeyFrame(Duration.millis(200), keyvalue2);
			timeline2.getKeyFrames().add(keyFrame2);
			timeline2.play();
		}, keyvalue);

		timeline.getKeyFrames().add(keyFrame);
		timeline.play();
		ObservableList<String> value = FXCollections.observableArrayList();
		value.add("전등");
		value.add("TV");
		value.add("에어컨");
		value.add("세탁기");
		comboCategory.setItems(value);
		
		comboCategory.valueProperty().addListener((observable, oldValue, newValue) -> {
			handleComboCategory(newValue);
		});
	}	

	private void handleComboCategory(String value) {
		if(value.equals("전등")){
			addLight();
		}else if(value.equals("TV")){
			addTV();
		}else if(value.equals("에어컨")){
			addAC();
		}else if(value.equals("세탁기")){
			addWM();
		}
	}

	private void addLight() {
		comboName.getItems().clear();
		
		name.add("안방");
		name.add("방-1");
		name.add("방-2");
		name.add("욕실");
		name.add("거실");
		name.add("주방");
		name.add("현관");
		name.add("다용도실");
		name.add("보일러실");
		
		comboName.setItems(name);
	}

	private void addTV() {
		comboName.getItems().clear();
		
		name.add("TV-1");
		name.add("TV-2");
		
		comboName.setItems(name);
	}

	private void addAC() {
		comboName.getItems().clear();
		
		name.add("에어컨-1");
		name.add("에어컨-2");
		
		comboName.setItems(name);
	}

	private void addWM() {
		comboName.getItems().clear();
		
		name.add("세탁기-1");
		name.add("세탁기-2");
		
		comboName.setItems(name);
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("scheduleAddController is OUT!!");
	}
	
	
}
