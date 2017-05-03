/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.lock;

import com.tomcatisbabycat.homepanel.main.MainController;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
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
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kang
 */
public class LockController implements Initializable {

	@FXML
	private Button btnUnlock;
	@FXML
	private StackPane stackPaneLock;
	
	public static StackPane lockRootPane;
	@FXML
	private Button btnTest;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		lockRootPane = stackPaneLock; // 다른 클래스에서도 접근할 수 있게 public static 변수에 객체를 넘겨주었다.
		
		btnUnlock.setOnAction(event->{
			handleBtnUnlock(event);
		});
		btnTest.setOnAction(event->{
			handleBtnTest(event);
		});
	}	

	private void handleBtnUnlock(ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(MainController.class.getResource("main.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근
			// StackPane의 자식 리스트에 AnchorPane하나만 있는상태(index-0, size 1)
			// 여기서 Main의 화면을 추가하면서 size를 이용해 인덱스를 지정하면서 추가 ~.add(int index, Node element)
			lockRootPane.getChildren().add(lockRootPane.getChildren().size(), parent);
			// 메인페이지를 추가한 후에는 리스트의 사이즈가 2가 되있는 상태
			// System.out.println(lockRootPane.getChildren().size()) // 사이즈 확인 출력문
			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			// 차후에 화면전환 애니메이션 수정필요(5/3 02:30)
			parent.setTranslateX(800);
			KeyValue keyValue = new KeyValue(parent.translateXProperty(), 0);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);


			Timeline timeline = new Timeline();
			timeline.getKeyFrames().add(keyFrame);
			
			timeline.play();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void handleBtnTest(ActionEvent event) {
		System.out.println("test 버튼이 눌렸습니다.");
	}
	
}
