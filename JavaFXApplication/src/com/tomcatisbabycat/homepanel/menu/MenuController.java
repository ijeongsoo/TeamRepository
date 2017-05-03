/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.menu;

import com.tomcatisbabycat.homepanel.cctv.CctvController;
import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.main.MainController;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kang
 */
public class MenuController implements Initializable {

	@FXML
	private Button btnControlHome;
	@FXML
	private StackPane menuStackPane;
	@FXML
	private Button btnCCTV;
	@FXML
	private Button btnControlLock;
	@FXML
	private Button testBtn;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnControlHome.setOnAction(event -> {
			handleBtnControlHome(event);
		});
		btnCCTV.setOnAction(event -> {
			handleBtnCCTV(event);
		});
		testBtn.setOnAction(event -> {
			handleBtnTest(event);
		});
	}

	private void handleBtnTest(ActionEvent event) {

	}

	private void handleBtnControlHome(ActionEvent event) {
		//menuStackPane.setTranslateX(0);

		KeyValue keyValue = new KeyValue(menuStackPane.translateXProperty(), 800);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
			  e -> {
				  // 키프레임에 이벤트를 넣어서 이벤트가 종료된시점에 메뉴페이지를 삭제 한다. 애니메이션이 동작하기전에 아래 코드에서 인덱스 1에 메인페이지를 추가하면서 현재 있는 메뉴페이지의 
				  // 인덱스는 2가 되고 리스트의 사이즈는 잠시동안 3이 되었다가 애니메이션이 종료되면서 메뉴페이지를 삭제하면 리스트의 사이즈는 2가 된다.
				  LockController.lockRootPane.getChildren().remove(2);
			  }, keyValue);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();
		
		// 인덱스를 이용해 페이지의 선후관계를 설정한후에 애니메이션 처리를하면 메인페이지가 지워질 메뉴페이지 아래에 생성되기 때문에 애니메이션 처리에서 영향을 받지 않는다.
		try {
			LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MainController.class.getResource("main.fxml")));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void handleBtnCCTV(ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(CctvController.class.getResource("cctv.fxml"));
			
			// 메뉴페이지 위에다 CCTV페이지를 추가한다 현재 리스트의 사이즈는 2이고, 메뉴페이지는 1 인덱스를 가지고 있다
			LockController.lockRootPane.getChildren().add(2, parent);
			// 페이지를 추가하면 리스트의 사이즈가 잠시 3으로 늘어난다.
			
			parent.setTranslateX(800);
			
			KeyValue keyValueStackPaneCCTV = new KeyValue(parent.translateXProperty(), 0);
			KeyFrame keyFrameStackPaneCCTV = new KeyFrame(Duration.seconds(1), keyValueStackPaneCCTV);
			
			Timeline timeline = new Timeline(keyFrameStackPaneCCTV);
			timeline.play();
			
			timeline.statusProperty().addListener((observable, oldValue, newValue) -> {
				if(newValue.toString().equals("STOPPED")){
					// 애니메이션이 끝났을때 메뉴페이지 삭제
					// 삭제를 하면 리스트의 사이즈는 2로 변하고 인덱스 1 에는 cctv페이지가 들어간다.
					LockController.lockRootPane.getChildren().remove(1);
				}
			});
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
