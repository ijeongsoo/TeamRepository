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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
	@FXML
	private ToggleSwitchSmall btnOnOff;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnMon;
	@FXML
	private Button btnTue;
	@FXML
	private Button btnWen;
	@FXML
	private Button btnThu;
	@FXML
	private Button btnFri;
	@FXML
	private Button btnSat;
	@FXML
	private Button btnSun;
	

	@FXML
	private Label lblMon;
	@FXML
	private Label lblTue;
	@FXML
	private Label lblWen;
	@FXML
	private Label lblThu;
	@FXML
	private Label lblFri;
	@FXML
	private Label lblSat;
	@FXML
	private Label lblSun;
	
	//	public boolean isMon = false;
//	public boolean isTue = false;
//	public boolean isWen = false;
//	public boolean isThu = false;
//	public boolean isFri = false;
//	public boolean isSat = false;
//	public boolean isSun = false;
	

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnAdd.setFocusTraversable(false);
		btnFri.setFocusTraversable(false);
		btnMon.setFocusTraversable(false);
		btnOnOff.setFocusTraversable(false);
		btnSat.setFocusTraversable(false);
		btnSun.setFocusTraversable(false);
		btnThu.setFocusTraversable(false);
		btnTue.setFocusTraversable(false);
		btnWen.setFocusTraversable(false);
		
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
		btnMon.setOnAction(e->{
			clickedButton(btnMon);
			if(lblMon.getText().equals("false"))
				lblMon.setText("true");
			else
				lblMon.setText("false");
		});
		btnTue.setOnAction(e->{
			clickedButton(btnTue);
			if(lblTue.getText().equals("false"))
				lblTue.setText("true");
			else
				lblTue.setText("false");
		});
		btnWen.setOnAction(e->{
			clickedButton(btnWen);
			if(lblWen.getText().equals("false"))
				lblWen.setText("true");
			else
				lblWen.setText("false");
		});
		btnThu.setOnAction(e->{
			clickedButton(btnThu);
			if(lblThu.getText().equals("false"))
				lblThu.setText("true");
			else
				lblThu.setText("false");
		});
		btnFri.setOnAction(e->{
			clickedButton(btnFri);
			if(lblFri.getText().equals("false"))
				lblFri.setText("true");
			else
				lblFri.setText("false");
		});
		btnSat.setOnAction(e->{
			clickedButton(btnSat);
			if(lblSat.getText().equals("false"))
				lblSat.setText("true");
			else
				lblSat.setText("false");
		});
		btnSun.setOnAction(e->{
			clickedButton(btnSun);
			if(lblSun.getText().equals("false"))
				lblSun.setText("true");
			else
				lblSun.setText("false");
		});
		System.gc();
	}
	
	private void clickedButton(Button btn){
//		System.out.println("1 : " + btn.getStyleClass());
//		btn.getStyleClass().remove("dayButton");
//		btn.getStyleClass().add("dayButtonClicked");
//		System.out.println("2 : " + btn.getStyleClass());
		
		String styleClass = btn.getStyleClass().get(1).toString();
		
//		System.out.println("stylClass " + styleClass );
		if(styleClass.equals("dayButton")){
			btn.getStyleClass().remove("dayButton");
			btn.getStyleClass().add("dayButtonClicked");
		}else if(styleClass.equals("dayButtonClicked")){
			btn.getStyleClass().remove("dayButtonClicked");
			btn.getStyleClass().add("dayButton");
		}
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
		System.out.println("스케쥴Add 제거");
	}
	
	
}
