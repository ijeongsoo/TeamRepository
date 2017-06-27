
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.lock;

import com.tomcatisbabycat.homepanel.Condition.ConditionController;
import com.tomcatisbabycat.homepanel.consume.ConsumeController;
import com.tomcatisbabycat.homepanel.css.CSSSelector;
import com.tomcatisbabycat.homepanel.main.MainController;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ijeongsu
 */
public class LockController implements Initializable {


	@FXML
	private StackPane stackPaneLock;
	public static StackPane lockRootPane;
	@FXML
	private AnchorPane lock;
	@FXML
	private Line secondHand;
	@FXML
	private Line minuateHand;
	@FXML
	private Line houreHand;
	@FXML
	private Label lblMainClock;

	Rotate hourRotation;
	Rotate minuateRotation;
	Rotate secondRotation;
	Calendar calendar;
	
	//hyun added
	public static Calendar cal;

	/**
	 * Initializes the controller class.
	 */
	private void animation() {
		System.gc();
		calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		String hourstr;
		if (hour < 10) {
			hourstr = "0" + hour;
		} else {
			hourstr = String.valueOf(hour);
		}

		int minuate = calendar.get(Calendar.MINUTE);
		String minuatestr;
		if (minuate < 10) {
			minuatestr = "0" + minuate;
		} else {
			minuatestr = String.valueOf(minuate);
		}

		int second = calendar.get(Calendar.SECOND);
		String secondstr;
		if (second < 10) {
			secondstr = "0" + second;
		} else {
			secondstr = String.valueOf(second);
		}

		String ampm;
		if (calendar.get(Calendar.AM_PM) == 1) {
			ampm = "PM";
		} else {
			ampm = "AM";
		}

		//시간*30+분
		secondRotation.setAngle(second * 6);
		minuateRotation.setAngle(minuate * 6 + second * 0.1);
		hourRotation.setAngle(hour * 30 + minuate * 0.5);

		lblMainClock.setText(ampm + " " + hourstr + ":" + minuatestr + ":" + secondstr);
		if(lblMainClock.getScene()!=null){
			if(!lblMainClock.getScene().getStylesheets().get(0).equals(CSSSelector.class.getResource(CSSSelector.getSeasonCSS()).toString())){
				lblMainClock.getScene().getStylesheets().add(CSSSelector.class.getResource(CSSSelector.getSeasonCSS()).toString());
				lblMainClock.getScene().getStylesheets().remove(0);
			}
		}
		//hyun added
		cal = calendar;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		lockRootPane = stackPaneLock;

		hourRotation = new Rotate();
		hourRotation.pivotXProperty().bind(houreHand.startXProperty());
		hourRotation.pivotYProperty().bind(houreHand.startYProperty());
		houreHand.getTransforms().add(hourRotation);

		minuateRotation = new Rotate();
		minuateRotation.pivotXProperty().bind(minuateHand.startXProperty());
		minuateRotation.pivotYProperty().bind(minuateHand.startYProperty());
		minuateHand.getTransforms().add(minuateRotation);

		secondRotation = new Rotate();
		secondRotation.pivotXProperty().bind(secondHand.startXProperty());
		secondRotation.pivotYProperty().bind(secondHand.startYProperty());
		secondHand.getTransforms().add(secondRotation);

		animation();
		Timeline animationTL = new Timeline();

		animationTL.getKeyFrames().add(new KeyFrame(Duration.millis(1000), (event) -> {
			animation();
		}));

		animationTL.setCycleCount(Animation.INDEFINITE);
		animationTL.play();

		PauseTransition delay = new PauseTransition(Duration.seconds(30));
		PauseTransition delay2 = new PauseTransition(Duration.seconds(5));
		delay2.setOnFinished((e2) -> {
			MainController.mainThreadInterrupt();
			if (((StackPane) lockRootPane.getChildren().get(0)).getId().equals("conditionStackPane")) {
				ConditionController.stopthread();
			}else if (((StackPane) lockRootPane.getChildren().get(0)).getId().equals("consumeStackPane")) {
				ConsumeController.stopthread();
			}else if(((StackPane) lockRootPane.getChildren().get(0)).getId().equals("lightStackPane")){
				((StackPane) lockRootPane.getChildren().get(0)).setVisible(false);
			}
			LockController.lockRootPane.getChildren().remove(0);
		});
		delay.setOnFinished(e -> {
			if (stackPaneLock.getChildren().size() != 1) {
				if (lockRootPane.getChildren().get(1).getId().equals("stackPaneLock")) {
					KeyValue kv = new KeyValue(((StackPane)lockRootPane.getChildren().get(1)).getChildren().get(1).opacityProperty(), 0);
					KeyFrame keyFrameStackPaneMenu = new KeyFrame(Duration.millis(500), (event) -> {
						Timeline timeline2 = new Timeline();
						KeyValue keyvalue1 = new KeyValue(((BoxBlur) lock.getEffect()).heightProperty(), 0);
						KeyValue keyvalue2 = new KeyValue(((BoxBlur) lock.getEffect()).widthProperty(), 0);
						KeyValue keyvalue3 = new KeyValue(((StackPane)lockRootPane.getChildren().get(1)).getChildren().get(0).opacityProperty(), 0);
						KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), (ev) -> {
							LockController.lockRootPane.getChildren().remove(1);
						}, keyvalue1, keyvalue2, keyvalue3);
						timeline2.getKeyFrames().add(keyFrame2);
						timeline2.play();
					}, kv);

					Timeline timeline = new Timeline();
					timeline.getKeyFrames().addAll(keyFrameStackPaneMenu);
					timeline.play();
				}else{
					lock.setEffect(null);
					LockController.lockRootPane.getChildren().get(LockController.lockRootPane.getChildren().indexOf(lock)).toFront();
					delay2.play();
				}
			}
		});
		delay.play();
		stackPaneLock.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				delay.stop();
				delay2.stop();
				delay.play();
				if (stackPaneLock.getChildren().size() == 1) {
					try {
						BoxBlur bb = new BoxBlur(0, 0, 1);
						lock.setEffect(bb);
						StackPane parent = FXMLLoader.load(Lock_viewController.class.getResource("lock_view.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

						LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);

						parent.getChildren().get(0).setOpacity(0);
						parent.getChildren().get(1).setOpacity(0);

						KeyValue kvEffect = new KeyValue(((BoxBlur) lock.getEffect()).widthProperty(), 10);
						KeyValue kvEffect2 = new KeyValue(((BoxBlur) lock.getEffect()).heightProperty(), 10);
						KeyValue keyValueStackPaneMenu = new KeyValue(parent.getChildren().get(0).opacityProperty(), 0.7);
						KeyFrame keyFrameStackPaneMenu = new KeyFrame(Duration.millis(500), (event) -> {
							Timeline timeline2 = new Timeline();
							KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
							KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
							timeline2.getKeyFrames().add(keyFrame2);
							timeline2.play();
						}, keyValueStackPaneMenu, kvEffect, kvEffect2);

						Timeline timeline = new Timeline();
						timeline.getKeyFrames().addAll(keyFrameStackPaneMenu);
						timeline.play();

					} catch (IOException ex) {
						ex.printStackTrace();
					}
				} else if (stackPaneLock.getChildren().size() == 2) {
					if (lockRootPane.getChildren().get(1).getId().equals("stackPaneLock") != true) {
						lock.setEffect(null);
					}
					LockController.lockRootPane.getChildren().get(LockController.lockRootPane.getChildren().indexOf(lock)).toBack();
				}
			}

			
		});

	}

}
