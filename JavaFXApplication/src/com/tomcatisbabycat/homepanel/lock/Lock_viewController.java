/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.lock;

import com.tomcatisbabycat.homepanel.main.MainController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kang
 */
public class Lock_viewController implements Initializable {

	@FXML
	private StackPane stackPaneLock;
	@FXML
	private Button btnOne;
	@FXML
	private Button btnTwo;
	@FXML
	private Button btnThree;
	@FXML
	private Button btnFour;
	@FXML
	private Button btnFive;
	@FXML
	private Button btnSix;
	@FXML
	private Button btnSeven;
	@FXML
	private Button btnEight;
	@FXML
	private Button btnNine;
	@FXML
	private Button btnZero;
	@FXML
	private Button btnCancel;

	private String inputPassword = "";
	@FXML
	private Label lblPassword;
	@FXML
	private Circle circle1;
	@FXML
	private Circle circle2;
	@FXML
	private Circle circle3;
	@FXML
	private Circle circle4;

	/**
	 * Initializes the controller class. sha256
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		btnOne.setOnAction(event -> {
			handleBtnPassword(event);
			final Animation ani = new Transition() {
				{
					setCycleDuration(Duration.millis(100));
					setOnFinished(e->{
						btnOne.getStyleClass().add("lockBtnPassword");
					});
					setInterpolator(Interpolator.EASE_OUT);
				}
				
				@Override
				protected void interpolate(double frac) {
					//Color vColor = new Color(41.0, 110.0, 168.0, 1 - frac); 
					//Color vColor = new Color(0.16, 0.43, 0.66, 1 - frac);
					//Color vColor = new Color(0, 0, 0, 1 - frac);
					Color vColor = new Color(1, 1, 1, 1 - frac);
					btnOne.setBackground(new Background(new BackgroundFill(vColor, new CornerRadii(30), Insets.EMPTY)));
				}
			};
			ani.play();
		});
		btnTwo.setOnAction(event -> {
			handleBtnPassword(event);
			final Animation ani = new Transition() {
				{
					setCycleDuration(Duration.millis(100));
					setOnFinished(e->{
						btnTwo.getStyleClass().add("lockBtnPassword");
					});
					setInterpolator(Interpolator.EASE_OUT);
				}
				
				@Override
				protected void interpolate(double frac) {
					//Color vColor = new Color(41.0, 110.0, 168.0, 1 - frac); 
					//Color vColor = new Color(0.16, 0.43, 0.66, 1 - frac);
					//Color vColor = new Color(0, 0, 0, 1 - frac);
					Color vColor = new Color(1, 1, 1, 1 - frac);
					btnTwo.setBackground(new Background(new BackgroundFill(vColor, new CornerRadii(30), Insets.EMPTY)));
				}
			};
			ani.play();
		});
		btnThree.setOnAction(event -> {
			handleBtnPassword(event);
			final Animation ani = new Transition() {
				{
					setCycleDuration(Duration.millis(100));
					setOnFinished(e->{
						btnThree.getStyleClass().add("lockBtnPassword");
					});
					setInterpolator(Interpolator.EASE_OUT);
				}
				
				@Override
				protected void interpolate(double frac) {
					//Color vColor = new Color(41.0, 110.0, 168.0, 1 - frac); 
					//Color vColor = new Color(0.16, 0.43, 0.66, 1 - frac);
					//Color vColor = new Color(0, 0, 0, 1 - frac);
					Color vColor = new Color(1, 1, 1, 1 - frac);
					btnThree.setBackground(new Background(new BackgroundFill(vColor, new CornerRadii(30), Insets.EMPTY)));
				}
			};
			ani.play();
		});
		btnFour.setOnAction(event -> {
			handleBtnPassword(event);
			final Animation ani = new Transition() {
				{
					setCycleDuration(Duration.millis(100));
					setOnFinished(e->{
						btnFour.getStyleClass().add("lockBtnPassword");
					});
					setInterpolator(Interpolator.EASE_OUT);
				}
				
				@Override
				protected void interpolate(double frac) {
					//Color vColor = new Color(41.0, 110.0, 168.0, 1 - frac); 
					//Color vColor = new Color(0.16, 0.43, 0.66, 1 - frac);
					//Color vColor = new Color(0, 0, 0, 1 - frac);
					Color vColor = new Color(1, 1, 1, 1 - frac);
					btnFour.setBackground(new Background(new BackgroundFill(vColor, new CornerRadii(30), Insets.EMPTY)));
				}
			};
			ani.play();
		});
		btnFive.setOnAction(event -> {
			handleBtnPassword(event);
			final Animation ani = new Transition() {
				{
					setCycleDuration(Duration.millis(100));
					setOnFinished(e->{
						btnFive.getStyleClass().add("lockBtnPassword");
					});
					setInterpolator(Interpolator.EASE_OUT);
				}
				
				@Override
				protected void interpolate(double frac) {
					//Color vColor = new Color(41.0, 110.0, 168.0, 1 - frac); 
					//Color vColor = new Color(0.16, 0.43, 0.66, 1 - frac);
					//Color vColor = new Color(0, 0, 0, 1 - frac);
					Color vColor = new Color(1, 1, 1, 1 - frac);
					btnFive.setBackground(new Background(new BackgroundFill(vColor, new CornerRadii(30), Insets.EMPTY)));
				}
			};
			ani.play();
		});
		btnSix.setOnAction(event -> {
			handleBtnPassword(event);
			final Animation ani = new Transition() {
				{
					setCycleDuration(Duration.millis(100));
					setOnFinished(e->{
						btnSix.getStyleClass().add("lockBtnPassword");
					});
					setInterpolator(Interpolator.EASE_OUT);
				}
				
				@Override
				protected void interpolate(double frac) {
					//Color vColor = new Color(41.0, 110.0, 168.0, 1 - frac); 
					//Color vColor = new Color(0.16, 0.43, 0.66, 1 - frac);
					//Color vColor = new Color(0, 0, 0, 1 - frac);
					Color vColor = new Color(1, 1, 1, 1 - frac);
					btnSix.setBackground(new Background(new BackgroundFill(vColor, new CornerRadii(30), Insets.EMPTY)));
				}
			};
			ani.play();
		});
		btnSeven.setOnAction(event -> {
			handleBtnPassword(event);
			final Animation ani = new Transition() {
				{
					setCycleDuration(Duration.millis(100));
					setOnFinished(e->{
						btnSeven.getStyleClass().add("lockBtnPassword");
					});
					setInterpolator(Interpolator.EASE_OUT);
				}
				
				@Override
				protected void interpolate(double frac) {
					//Color vColor = new Color(41.0, 110.0, 168.0, 1 - frac); 
					//Color vColor = new Color(0.16, 0.43, 0.66, 1 - frac);
					//Color vColor = new Color(0, 0, 0, 1 - frac);
					Color vColor = new Color(1, 1, 1, 1 - frac);
					btnSeven.setBackground(new Background(new BackgroundFill(vColor, new CornerRadii(30), Insets.EMPTY)));
				}
			};
			ani.play();
		});
		btnEight.setOnAction(event -> {
			handleBtnPassword(event);
			final Animation ani = new Transition() {
				{
					setCycleDuration(Duration.millis(100));
					setOnFinished(e->{
						btnEight.getStyleClass().add("lockBtnPassword");
					});
					setInterpolator(Interpolator.EASE_OUT);
				}
				
				@Override
				protected void interpolate(double frac) {
					//Color vColor = new Color(41.0, 110.0, 168.0, 1 - frac); 
					//Color vColor = new Color(0.16, 0.43, 0.66, 1 - frac);
					//Color vColor = new Color(0, 0, 0, 1 - frac);
					Color vColor = new Color(1, 1, 1, 1 - frac);
					btnEight.setBackground(new Background(new BackgroundFill(vColor, new CornerRadii(30), Insets.EMPTY)));
				}
			};
			ani.play();
		});
		btnNine.setOnAction(event -> {
			handleBtnPassword(event);
			final Animation ani = new Transition() {
				{
					setCycleDuration(Duration.millis(100));
					setOnFinished(e->{
						btnNine.getStyleClass().add("lockBtnPassword");
					});
					setInterpolator(Interpolator.EASE_OUT);
				}
				
				@Override
				protected void interpolate(double frac) {
					//Color vColor = new Color(41.0, 110.0, 168.0, 1 - frac); 
					//Color vColor = new Color(0.16, 0.43, 0.66, 1 - frac);
					//Color vColor = new Color(0, 0, 0, 1 - frac);
					Color vColor = new Color(1, 1, 1, 1 - frac);
					btnNine.setBackground(new Background(new BackgroundFill(vColor, new CornerRadii(30), Insets.EMPTY)));
				}
			};
			ani.play();
		});
		btnZero.setOnAction(event -> {
			handleBtnPassword(event);
			final Animation ani = new Transition() {
				{
					setCycleDuration(Duration.millis(100));
					setOnFinished(e->{
						btnZero.getStyleClass().add("lockBtnPassword");
					});
					setInterpolator(Interpolator.EASE_OUT);
				}
				
				@Override
				protected void interpolate(double frac) {
					//Color vColor = new Color(41.0, 110.0, 168.0, 1 - frac); 
					//Color vColor = new Color(0.16, 0.43, 0.66, 1 - frac);
					//Color vColor = new Color(0, 0, 0, 1 - frac);
					Color vColor = new Color(1, 1, 1, 1 - frac);
					btnZero.setBackground(new Background(new BackgroundFill(vColor, new CornerRadii(30), Insets.EMPTY)));
				}
			};
			ani.play();
		});
	}

	private void correctPassword() {
		try {
			Parent parent = FXMLLoader.load(MainController.class.getResource("main.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근
			
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			parent.setOpacity(0);
			KeyValue keyValue = new KeyValue(parent.opacityProperty(), 1);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500),(event) -> {
				LockController.lockRootPane.getChildren().remove(1);
			},keyValue);

			Timeline timeline = new Timeline();
			timeline.getKeyFrames().add(keyFrame);

			timeline.play();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void handleBtnPassword(ActionEvent event) {
		// event를 통해 어떤 버튼의 이벤트가 발생한거지 getSource를 통해 해당 객체를 가져온다.
		Button passBtn = (Button) event.getSource();
		inputPassword += passBtn.getText();
		
		
		
		fillCircle(inputPassword.length());

		SHA256 sha256 = new SHA256();
		//System.out.println(sha256.getPassWord());
		//System.out.println(inputPassword.length() + " length");
		//System.out.println(sha256.getPassWord());
		if (inputPassword.length() == 4) {
			String getSHA = EncryptSHA256.encryptSHA256(inputPassword);

			if (getSHA.equals(sha256.getPassWord())) {
				System.out.println("패스워드가 일치합니다.");
				lblPassword.setText("패스워드가 일치합니다.");
				inputPassword = "";
				Thread t = new Thread() {
					@Override
					public void run() {
						//System.out.println(Thread.currentThread().getName());
						try {
							Thread.sleep(1000);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						fillCircle(inputPassword.length());
					}
				};
				t.start();
				correctPassword();
			} else {
				System.out.println("패스워드가 일치하지 않습니다. 다시 입력하세요");
				lblPassword.setText("패스워드가 일치하지 않습니다. 다시 입력하세요");
				inputPassword = "";
				Thread t = new Thread() {
					@Override
					public void run() {
						//System.out.println(Thread.currentThread().getName());
						try {
							Thread.sleep(1000);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						fillCircle(inputPassword.length());
					}
				};
				t.start();
			}
		}
	}

	private void fillCircle(int length) {
		ArrayList<Circle> circles = new ArrayList<Circle>();
		circles.add(circle1);
		circles.add(circle2);
		circles.add(circle3);
		circles.add(circle4);
//		System.out.println(circles.get(0));
//		System.out.println(circles.get(1));
//		System.out.println(circles.get(2));
//		System.out.println(circles.get(3));
		System.out.println(Thread.currentThread().getName());
		for (int i = 0; i < length; i++) {
			circles.get(i).setFill(Color.rgb(41, 110, 168));
		}
		for (int j = 3; j > length - 1; j--) {
			System.out.println(circles.get(j));
			circles.get(j).setFill(Color.TRANSPARENT);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("aaabbb");
	}
	
}

