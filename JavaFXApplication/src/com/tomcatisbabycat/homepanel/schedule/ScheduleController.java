/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.schedule;

import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.main.MainController;
import com.tomcatisbabycat.homepanel.menu.MenuController;
import com.tomcatisbabycat.homepanel.sampleAppliance.Appliances;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author kang
 */
public class ScheduleController implements Initializable {
	@FXML
	private StackPane scheduleStackPane;
	@FXML
	private Button btnControlHome;
	@FXML
	private Button btnControlLock;
	@FXML
	private Button btnControlBack;
	@FXML
	private Button btnTV;
	@FXML
	private Button btnAirCondition;
	@FXML
	private Button btnWashingMachine;
	@FXML
	private Button btnLight;
	@FXML
	private Label lblSchedule;
	@FXML
	private ListView<Appliances> scheduleListview;
	@FXML
	private Button btnAdd;
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
		btnTV.setDefaultButton(true);
		
		if(btnTV.isDefaultButton()){
			defaultButton();
		}
		btnTV.setOnAction(event->{
			handleBackground(event);
		});
		btnAirCondition.setOnAction(event->{
			handleBackground(event);
		});
		btnWashingMachine.setOnAction(event->{
			handleBackground(event)
		;});
		btnLight.setOnAction(event->{
			handleBackground(event);
		});
		scheduleListview.setCellFactory(new Callback<ListView<Appliances>, ListCell<Appliances>>() {
			@Override
			public ListCell<Appliances> call(ListView<Appliances> param) {
				ListCell<Appliances> listCell = new ListCell<Appliances>(){
					@Override
					protected void updateItem(Appliances item, boolean empty) {
						super.updateItem(item, empty);
						if(empty){
							return;
						}
						try {
							AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("appliances.fxml"));
							Label lblName = (Label) a.lookup("#name");
							Label lblTime = (Label) a.lookup("#turnTime");
							Label lblNoon = (Label) a.lookup("#noon");
							
							lblName.setText(item.getLblName());
							lblTime.setText(item.getTurnTime());
							lblNoon.setText(item.getNoon());
							
							setGraphic(a);
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
					
				};
				return listCell;
			}
		});
		
		btnAdd.setOnAction(e->{
			handleAddButton(e);
		});
	}
	private void handleBackground(ActionEvent event) {
		Button btn = (Button) event.getSource();
		btnTV.setStyle("-fx-background-color: #01C2F2;");
		btnAirCondition.setStyle("-fx-background-color: #01C2F2;");
		btnWashingMachine.setStyle("-fx-background-color: #01C2F2;");
		btnLight.setStyle("-fx-background-color: #01C2F2;");
		
		btn.setStyle("-fx-background-color: #85D03C;");
	}
	private void handleBtnControlBack(ActionEvent event){
		scheduleStackPane.setTranslateX(0);
		
		KeyValue keyValueStackPaneSchedule = new KeyValue(scheduleStackPane.translateXProperty(), 800);
		KeyFrame keyVFrameStackPaneSchedule = new KeyFrame(Duration.seconds(1), 
			  e->{
				LockController.lockRootPane.getChildren().remove(2);
		}, keyValueStackPaneSchedule);
		
		Timeline timeline = new Timeline(keyVFrameStackPaneSchedule);
		timeline.play();
		try {
			LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MenuController.class.getResource("menu.fxml")));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	private void handleBtnControlLock(ActionEvent event) {
		//StackPane rootPane = (StackPane) scheduleStackPane.getScene().getRoot(); // 컨트롤을 통해서 현재 Scene을 얻고 root의 객체를 얻는다.

		scheduleStackPane.setTranslateX(0);
		KeyValue keyValueStackPaneSchedule = new KeyValue(scheduleStackPane.translateXProperty(), 800);
		KeyFrame keyVFrameStackPaneSchedule = new KeyFrame(Duration.seconds(1),
			  e -> {
				  //
				  LockController.lockRootPane.getChildren().remove(scheduleStackPane);
			  }, keyValueStackPaneSchedule);
		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyVFrameStackPaneSchedule);
		timeline.play();
	}

	private void handleBtnControlHome(ActionEvent event) {
		KeyValue keyValueStackPaneSchedule = new KeyValue(scheduleStackPane.translateXProperty(), 800);
		KeyFrame keyVFrameStackPaneSchedule = new KeyFrame(Duration.seconds(1),
			  e -> {
				  LockController.lockRootPane.getChildren().remove(2, LockController.lockRootPane.getChildren().size());
			  }, keyValueStackPaneSchedule);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyVFrameStackPaneSchedule);
		timeline.play();
		try {
			LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MainController.class.getResource("main.fxml")));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	

	private void defaultButton() {
		btnTV.setStyle("-fx-background-color: #85D03C;");
	}

	private void handleAddButton(ActionEvent e) {
		ObservableList<Appliances> value = FXCollections.observableArrayList();
		//value.add(new Appliances("보일러실", "17:00", true));

		scheduleListview.setItems(value);
	}
}
