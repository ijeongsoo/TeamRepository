/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.menu;

import com.tomcatisbabycat.homepanel.Condition.ConditionController;
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
      private Button btnCondition;
      @FXML
      private Button btnControlLock;
      

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
            btnCondition.setOnAction(event -> {
                  handleBtnCondition(event);
            });
	}

	private void handleBtnControlHome(ActionEvent event) {
		StackPane rootPane = (StackPane) btnControlHome.getScene().getRoot();

		//menuStackPane.setTranslateX(0);

		KeyValue keyValue = new KeyValue(menuStackPane.translateXProperty(), 800);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
			  e -> {
				  rootPane.getChildren().remove(menuStackPane);
			  }, keyValue);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();

		Thread task = new Thread() {
			@Override
			public void run() {
				try {
					//Thread.sleep(1000);
					Platform.runLater(() -> {
						try {
							LockController.lockRootPane.getChildren().add(FXMLLoader.load(MainController.class.getResource("main.fxml")));
						} catch (IOException ex) {
							Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
						}

					});
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		};
		task.start();
	}

	private void handleBtnCCTV(ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(CctvController.class.getResource("cctv.fxml"));

			menuStackPane.getChildren().add(parent);

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
      private void handleBtnCondition(ActionEvent event){
            try {
                  Parent parent = FXMLLoader.load(ConditionController.class.getResource("condition.fxml"));
                  //condition.fxml을 로딩시키는 것으로써 condition.fxml파일이 해당 패키지안에 있지않으므로 그 경로를 찾기위해 conditionControl.class가 있는 패키지에서 getResource를 통해 condition.fxml의 경로를 찾아 로딩.

                  menuStackPane.getChildren().add(parent); //메뉴의 stackpane에 또하나의 stack(condition.fxml을 로딩한 parent)를 추가한것
                  
                  parent.setTranslateX(800); //추가된 stack을 x좌표 800에 생성시킴.frame에서 벗어나 생성된것이므로 화면에 보이지는 않는다.
                  KeyValue keyValue = new KeyValue(parent.translateXProperty(),0);//변화시킬 translateX 속성을 얻어내고, frame밖에서 생성된 stack을 화면frame으로(0좌표를 종료값으로) 갖다놓는것.
                  KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),keyValue);//화면frame으로 옮겨오는 과정에 1초의 지연시간을 주어 애니메이션 효과를 입힘.
                  
                  Timeline timeline = new Timeline();// 애니메이션을 실현하기 위해 timeline객체 생성.
                  timeline.getKeyFrames().add(keyFrame);//생성된 timeline객체에 설정한 keyframe를 셋팅.
                  
                  timeline.play();//애니메이션 실행.
                  
            } catch (IOException ex) {
                  ex.printStackTrace();
            }
      }

}
