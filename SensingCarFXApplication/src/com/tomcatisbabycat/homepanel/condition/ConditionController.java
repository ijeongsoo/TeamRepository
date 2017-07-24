package com.tomcatisbabycat.homepanel.condition;

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

public class ConditionController implements Initializable {

	@FXML
	private StackPane conditionStackPane;
	@FXML
	private Button btnControlHome;
	@FXML
	private Button btnControlLock;
	@FXML
	private Button btnControlBack;

	@FXML
	private Button btnTemp;
	@FXML
	private Button btnMoist;

	private Parent temperature;
	private Parent moisture;
	@FXML
	private StackPane graphStackPane;
	static StackPane staticGraphStackPane;
	@FXML
	private Label lblBtnTemp;
	@FXML
	private Label lblBtnMoist;
	@FXML
	private Label lblBtnDust;
	@FXML
	private ImageView imgBtnTemp;
	@FXML
	private ImageView imgBtnMoist;
	

	private void handleBackground(ActionEvent event, Button btn, ImageView img, Label lbl, ImageView img2, Label lbl2) {
		btnTemp.getStyleClass().removeAll("conditionBtnFocused");
		btnTemp.getStyleClass().add("conditionBtn");
		btnMoist.getStyleClass().removeAll("conditionBtnFocused");
		btnMoist.getStyleClass().add("conditionBtn");

		btn.getStyleClass().removeAll("conditionBtn");
		btn.getStyleClass().add("conditionBtnFocused");

		Timeline btnImageChange = new Timeline();
		KeyValue kv = new KeyValue(img.opacityProperty(), 0);
		KeyValue kv2 = new KeyValue(lbl2.opacityProperty(), 0);
		KeyFrame kf = new KeyFrame(Duration.millis(200), (e) -> {
			Timeline btnlblChange = new Timeline();
			KeyValue kiv = new KeyValue(lbl.opacityProperty(), 1);
			KeyValue kiv2 = new KeyValue(img2.opacityProperty(), 1);
			KeyFrame kif = new KeyFrame(Duration.millis(200), kiv, kiv2);
			btnlblChange.getKeyFrames().add(kif);
			btnlblChange.play();
		}, kv, kv2);
		btnImageChange.getKeyFrames().add(kf);
		btnImageChange.play();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		staticGraphStackPane=graphStackPane;
		
		btnControlBack.setFocusTraversable(false);
		btnControlHome.setFocusTraversable(false);
		btnControlLock.setFocusTraversable(false);
		btnMoist.setFocusTraversable(false);
		btnTemp.setFocusTraversable(false);
		
		try {
			temperature = FXMLLoader.load(getClass().getResource("temperature.fxml"));
			moisture = FXMLLoader.load(getClass().getResource("moisture.fxml"));
		} catch (IOException ex) {
		}

		graphStackPane.getChildren().add(moisture);
		graphStackPane.getChildren().add(temperature);

		handleBackground(new ActionEvent(), btnTemp, imgBtnTemp, lblBtnTemp, imgBtnMoist, lblBtnMoist);

		btnTemp.setOnAction((event) -> {
			
			if(graphStackPane.getChildren().indexOf(temperature)!=2){
			handleBackground(event, btnTemp, imgBtnTemp, lblBtnTemp, imgBtnMoist, lblBtnMoist);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(temperature)).setOpacity(0);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(temperature)).toFront();
			KeyValue kv = new KeyValue(graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(temperature)).opacityProperty(), 1);
			KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
			Timeline timeline = new Timeline(kf);
			timeline.play();
		}

		});
		btnMoist.setOnAction((event) -> {
			if(graphStackPane.getChildren().indexOf(moisture)!=2){
			handleBackground(event, btnMoist, imgBtnMoist, lblBtnMoist, imgBtnTemp, lblBtnTemp);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(moisture)).setOpacity(0);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(moisture)).toFront();
			KeyValue kv = new KeyValue(graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(moisture)).opacityProperty(), 1);
			KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
			Timeline timeline = new Timeline(kf);
			timeline.play();
			}
		});

		btnControlLock.setOnAction(event -> {
			handleBtnControlLock(event);
		});
		btnControlHome.setOnAction(event -> {
			handleBtnControlHome(event);
		});
		btnControlBack.setOnAction(event -> {
			handleBtnControlBack(event);
		});
	}

	public void handleBtnControlBack(ActionEvent event) {

		try {
			StackPane parent = FXMLLoader.load(MenuController.class.getResource("menu.fxml"));
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);

			KeyValue keyValueStackPane = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPane = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				stopthread();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPane);
			
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPane);
			timeline.play();
		} catch (IOException ex) {
		}

	}
	
	public static void stopthread(){
			staticGraphStackPane.getChildren().get(0).setVisible(false);
			staticGraphStackPane.getChildren().get(1).setVisible(false);
			staticGraphStackPane.getChildren().clear();
	}

	public void handleBtnControlLock(ActionEvent event) {
		stopthread();

		LockController.lockRootPane.getChildren().remove(conditionStackPane);
	}

	public void handleBtnControlHome(ActionEvent event) {

		try {
			StackPane parent = FXMLLoader.load(MainController.class.getResource("main.fxml"));
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);

			KeyValue keyValueStackPane = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPane = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				stopthread();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPane);
			
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPane);
			timeline.play();
		} catch (IOException ex) {
		}
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("컨트롤 나옴");
	}


	

}
