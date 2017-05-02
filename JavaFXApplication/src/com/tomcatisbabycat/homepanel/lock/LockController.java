/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.lock;

import com.tomcatisbabycat.homepanel.main.MainController;
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

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		lockRootPane = stackPaneLock;
		
		btnUnlock.setOnAction(event->{
			handleBtnUnlock(event);
		});
	}	

	private void handleBtnUnlock(ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(MainController.class.getResource("main.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

			lockRootPane.getChildren().add(parent);
			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			// 차후에 화면전환 애니메이션 수정필요(5/3 02:30)
			parent.setTranslateX(800);
			KeyValue keyValue = new KeyValue(parent.translateXProperty(), 0);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);


			Timeline timeline = new Timeline();
			timeline.getKeyFrames().add(keyFrame);
			
			timeline.play();
			System.out.println(lockRootPane.getChildren().indexOf(lockRootPane));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
