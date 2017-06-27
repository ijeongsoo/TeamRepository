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
	static public StackPane staticGraphStackPane;
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
		btnControlBack.setFocusTraversable(false);
		btnControlHome.setFocusTraversable(false);
		btnControlLock.setFocusTraversable(false);
		btnDust.setFocusTraversable(false);
		btnMoist.setFocusTraversable(false);
		btnTemp.setFocusTraversable(false);
		
		staticGraphStackPane=graphStackPane;
		
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
			if(graphStackPane.getChildren().indexOf(electric)!=2){
			handleBackground(event, btnTemp, imgBtnTemp, lblBtnTemp, imgBtnMoist, lblBtnMoist, imgBtnDust, lblBtnDust);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(electric)).setOpacity(0);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(electric)).toFront();
			KeyValue kv = new KeyValue(graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(electric)).opacityProperty(), 1);
			KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
			Timeline timeline = new Timeline(kf);
			timeline.play();
			}

		});
		btnMoist.setOnAction((event) -> {
			if(graphStackPane.getChildren().indexOf(water)!=2){
			handleBackground(event, btnMoist, imgBtnMoist, lblBtnMoist, imgBtnTemp, lblBtnTemp, imgBtnDust, lblBtnDust);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(water)).setOpacity(0);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(water)).toFront();
			KeyValue kv = new KeyValue(graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(water)).opacityProperty(), 1);
			KeyFrame kf = new KeyFrame(Duration.millis(200), kv);
			Timeline timeline = new Timeline(kf);
			timeline.play();
			}
		});
		btnDust.setOnAction((event) -> {
			if(graphStackPane.getChildren().indexOf(gas)!=2){
			handleBackground(event, btnDust, imgBtnDust, lblBtnDust, imgBtnTemp, lblBtnTemp, imgBtnMoist, lblBtnMoist);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(gas)).setOpacity(0);
			graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(gas)).toFront();
			KeyValue kv = new KeyValue(graphStackPane.getChildren().get(graphStackPane.getChildren().indexOf(gas)).opacityProperty(), 1);
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
		btnControlBack.setOnAction(event->{
			handleBtnControlBack(event);
		});
		
		
		
	}
	private void handleBtnControlBack(ActionEvent event){
		try {
			StackPane parent = FXMLLoader.load(MenuController.class.getResource("menu.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);
			//parent.setTranslateX(800);

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

			// 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
			//KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			//KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPane);
			timeline.play();
		} catch (IOException ex) {
		}
	}
	
	public static void stopthread(){
			staticGraphStackPane.getChildren().get(0).setVisible(false);
			staticGraphStackPane.getChildren().get(1).setVisible(false);
			staticGraphStackPane.getChildren().get(2).setVisible(false);
			staticGraphStackPane.getChildren().clear();
	}

	public void handleBtnControlLock(ActionEvent event) {
		stopthread();

		LockController.lockRootPane.getChildren().remove(consumeStackPane);
	}

	private void handleBtnControlHome(ActionEvent event) {

		try {
			StackPane parent = FXMLLoader.load(MainController.class.getResource("main.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);
			//parent.setTranslateX(800);

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

			// 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
			//KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			//KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPane);
			timeline.play();
		} catch (IOException ex) {
		}

	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("컨슘 제거");
	}
	

}
