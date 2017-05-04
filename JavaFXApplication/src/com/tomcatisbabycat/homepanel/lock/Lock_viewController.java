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
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private Button btnUnlock;
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
		
		btnUnlock.setOnAction(event -> {
			handleBtnUnlock(event);
		});
		btnOne.setOnAction(event -> {
			handleBtnPassword(event);
		});
		btnTwo.setOnAction(event -> {
			handleBtnPassword(event);
		});
		btnThree.setOnAction(event -> {
			handleBtnPassword(event);
		});
		btnFour.setOnAction(event -> {
			handleBtnPassword(event);
		});
		btnFive.setOnAction(event -> {
			handleBtnPassword(event);
		});
		btnSix.setOnAction(event -> {
			handleBtnPassword(event);
		});
		btnSeven.setOnAction(event -> {
			handleBtnPassword(event);
		});
		btnEight.setOnAction(event -> {
			handleBtnPassword(event);
		});
		btnNine.setOnAction(event -> {
			handleBtnPassword(event);
		});
		btnZero.setOnAction(event -> {
			handleBtnPassword(event);
		});
	}

	private void handleBtnUnlock(ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(MainController.class.getResource("main.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근
			// StackPane의 자식 리스트에 AnchorPane하나만 있는상태(index-0, size 1)
			// 여기서 Main의 화면을 추가하면서 size를 이용해 인덱스를 지정하면서 추가 ~.add(int index, Node element)
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 메인페이지를 추가한 후에는 리스트의 사이즈가 2가 되있는 상태
			// System.out.println(lockRootPane.getChildren().size()) // 사이즈 확인 출력문
			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			// 차후에 화면전환 애니메이션 수정필요(5/3 02:30)
			parent.setTranslateX(800);
			KeyValue keyValue = new KeyValue(parent.translateXProperty(), 0);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),(e) -> {
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValue);

			Timeline timeline = new Timeline();
			timeline.getKeyFrames().add(keyFrame);

			timeline.play();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void correctPassword() {
		try {
			Parent parent = FXMLLoader.load(MainController.class.getResource("main.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근
			// StackPane의 자식 리스트에 AnchorPane하나만 있는상태(index-0, size 1)
			// 여기서 Main의 화면을 추가하면서 size를 이용해 인덱스를 지정하면서 추가 ~.add(int index, Node element)
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 메인페이지를 추가한 후에는 리스트의 사이즈가 2가 되있는 상태
			// System.out.println(lockRootPane.getChildren().size()) // 사이즈 확인 출력문
			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			// 차후에 화면전환 애니메이션 수정필요(5/3 02:30)
			//parent.setTranslateX(800);
			//KeyValue keyValue = new KeyValue(parent.translateXProperty(), 0);
			parent.setOpacity(0);
			KeyValue keyValue = new KeyValue(parent.opacityProperty(), 1);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),(event) -> {
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
		System.out.println(inputPassword);
		System.out.println("");

		System.out.println(inputPassword.length());
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
}

