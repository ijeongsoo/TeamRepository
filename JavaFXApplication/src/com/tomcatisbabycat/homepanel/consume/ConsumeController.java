/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.consume;

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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kang
 */
public class ConsumeController implements Initializable {
	@FXML
	private Button btnControlHome;
	@FXML
	private Button btnControlLock;
	@FXML
	private Button btnControlBack;
	@FXML
	private StackPane consumeStackPane;
	@FXML
	private StackPane graphStackPane;
	@FXML
	private Button btnTemp;
	@FXML
	private ImageView imgBtnTemp;
	@FXML
	private Label lblBtnTemp;
	@FXML
	private Button btnMoist;
	@FXML
	private ImageView imgBtnMoist;
	@FXML
	private Label lblBtnMoist;
	@FXML
	private Button btnDust;
	@FXML
	private ImageView imgBtnDust;
	@FXML
	private Label lblBtnDust;
	
	private Parent electric;
	private Parent water;
	private Parent gas;

	/**
	 * Initializes the controller class.
	 */
	
	private void handleBackground(ActionEvent event, Button btn, ImageView img, Label lbl, ImageView img2, Label lbl2, ImageView img3, Label lbl3) {
		btnTemp.getStyleClass().removeAll("conditionBtnFocused");
		btnTemp.getStyleClass().add("conditionBtn");
		btnMoist.getStyleClass().removeAll("conditionBtnFocused");
		btnMoist.getStyleClass().add("conditionBtn");
		btnDust.getStyleClass().removeAll("conditionBtnFocused");
		btnDust.getStyleClass().add("conditionBtn");

		btn.getStyleClass().removeAll("conditionBtn");
		btn.getStyleClass().add("conditionBtnFocused");

		Timeline btnImageChange = new Timeline();
		KeyValue kv = new KeyValue(img.opacityProperty(), 0);
		KeyValue kv2 = new KeyValue(lbl2.opacityProperty(), 0);
		KeyValue kv3 = new KeyValue(lbl3.opacityProperty(), 0);
		KeyFrame kf = new KeyFrame(Duration.millis(200), (e) -> {
			Timeline btnlblChange = new Timeline();
			KeyValue kiv = new KeyValue(lbl.opacityProperty(), 1);
			KeyValue kiv2 = new KeyValue(img2.opacityProperty(), 1);
			KeyValue kiv3 = new KeyValue(img3.opacityProperty(), 1);
			KeyFrame kif = new KeyFrame(Duration.millis(200), kiv, kiv2, kiv3);
			btnlblChange.getKeyFrames().add(kif);
			btnlblChange.play();
		}, kv, kv2, kv3);
		btnImageChange.getKeyFrames().add(kf);
		btnImageChange.play();
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		try {
			electric = FXMLLoader.load(getClass().getResource("electric.fxml"));
			water = FXMLLoader.load(getClass().getResource("water.fxml"));
			gas = FXMLLoader.load(getClass().getResource("gas.fxml"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		graphStackPane.getChildren().add(gas);
		graphStackPane.getChildren().add(water);
		graphStackPane.getChildren().add(electric);
		
		handleBackground(new ActionEvent(), btnTemp, imgBtnTemp, lblBtnTemp, imgBtnMoist, lblBtnMoist, imgBtnDust, lblBtnDust);

		btnTemp.setOnAction((event) -> {
			handleBackground(event, btnTemp, imgBtnTemp, lblBtnTemp, imgBtnMoist, lblBtnMoist, imgBtnDust, lblBtnDust);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(electric)).setOpacity(0);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(electric)).toFront();
			KeyValue kv = new KeyValue(graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(electric)).opacityProperty(), 1);
			KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
			Timeline timeline = new Timeline(kf);
			timeline.play();

		});
		btnMoist.setOnAction((event) -> {
			handleBackground(event, btnMoist, imgBtnMoist, lblBtnMoist, imgBtnTemp, lblBtnTemp, imgBtnDust, lblBtnDust);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(water)).setOpacity(0);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(water)).toFront();
			KeyValue kv = new KeyValue(graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(water)).opacityProperty(), 1);
			KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
			Timeline timeline = new Timeline(kf);
			timeline.play();
		});
		btnDust.setOnAction((event) -> {
			handleBackground(event, btnDust, imgBtnDust, lblBtnDust, imgBtnTemp, lblBtnTemp, imgBtnMoist, lblBtnMoist);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(gas)).setOpacity(0);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(gas)).toFront();
			KeyValue kv = new KeyValue(graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(gas)).opacityProperty(), 1);
			KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
			Timeline timeline = new Timeline(kf);
			timeline.play();
		});

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
		consumeStackPane.setTranslateX(0);
		
		KeyValue keyValueStackPaneConsume = new KeyValue(consumeStackPane.translateXProperty(), 800);
		KeyFrame keyFrameStackPaneConsume = new KeyFrame(Duration.seconds(1), 
			  e->{
				LockController.lockRootPane.getChildren().remove(2);
		}, keyValueStackPaneConsume);
		
		Timeline timeline = new Timeline(keyFrameStackPaneConsume);
		timeline.play();
		
		try {
			LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MenuController.class.getResource("menu.fxml")));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	

	private void handleBtnControlLock(ActionEvent event) {
		//StackPane rootPane = (StackPane) consumeStackPane.getScene().getRoot(); // 컨트롤을 통해서 현재 Scene을 얻고 root의 객체를 얻는다.

		consumeStackPane.setTranslateX(0);
		KeyValue keyValueStackPaneConsume = new KeyValue(consumeStackPane.translateXProperty(), 800);
		KeyFrame keyFrameStackPaneConsume = new KeyFrame(Duration.seconds(1),
			  e -> {
				  //
				  LockController.lockRootPane.getChildren().remove(consumeStackPane);
			  }, keyValueStackPaneConsume);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrameStackPaneConsume);

		timeline.play();
	}

	private void handleBtnControlHome(ActionEvent event) {

		KeyValue keyValueStackPaneConsume = new KeyValue(consumeStackPane.translateXProperty(), 800);
		KeyFrame keyFrameStackPaneConsume = new KeyFrame(Duration.seconds(1),
			  e -> {
				  LockController.lockRootPane.getChildren().remove(2, LockController.lockRootPane.getChildren().size());
			  }, keyValueStackPaneConsume);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrameStackPaneConsume);

		timeline.play();
		try {
			LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MainController.class.getResource("main.fxml")));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
