/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.notice;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kang
 */
public class NoticeController implements Initializable {

	@FXML
	private StackPane noticeStackPane;
	@FXML
	private Button btnControlHome;
	@FXML
	private Button btnControlLock;
	@FXML
	private Button btnControlBack;
      @FXML
      private Button btnControlReg;
      @FXML
      private TextArea noticeTextArea;
      @FXML
      private ComboBox<?> yearCombo;
      @FXML
      private ComboBox<?> monthCombo;
      @FXML
      private ComboBox<?> dayCombo;
      @FXML
      private Button btnControlAdd;
      @FXML
      private TableView<?> noticeTableView;
      @FXML
      private StackPane regStackPane;
      @FXML
      private StackPane reviseStackPane;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnControlLock.setOnAction(event -> {
			handleBtnControlLock(event);
		});
		btnControlHome.setOnAction(event -> {
			handleBtnControlHome(event);
		});
		btnControlBack.setOnAction(event->{
			handleBtnControlBack(event);
		});
            btnControlAdd.setOnAction(event->{
                  handleBtnControlAdd(event);
            });
	}
	private void handleBtnControlBack(ActionEvent event){
		noticeStackPane.setTranslateX(0);
		
		KeyValue keyValueStackPaneNotice = new KeyValue(noticeStackPane.translateXProperty(), 800);
		KeyFrame keyFrameStackPaneNotice = new KeyFrame(Duration.seconds(1), 
			  e->{
				LockController.lockRootPane.getChildren().remove(2);
		}, keyValueStackPaneNotice);
		
		Timeline timeline = new Timeline(keyFrameStackPaneNotice);
		timeline.play();
		
		try {
			LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MenuController.class.getResource("menu.fxml")));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	

	private void handleBtnControlLock(ActionEvent event) {
		//StackPane rootPane = (StackPane) noticeStackPane.getScene().getRoot(); // 컨트롤을 통해서 현재 Scene을 얻고 root의 객체를 얻는다.

		noticeStackPane.setTranslateX(0);
		KeyValue keyValueStackPaneNotice = new KeyValue(noticeStackPane.translateXProperty(), 800);
		KeyFrame keyFrameStackPaneNotice = new KeyFrame(Duration.seconds(1),
			  e -> {
				  //
				  LockController.lockRootPane.getChildren().remove(noticeStackPane);
			  }, keyValueStackPaneNotice);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrameStackPaneNotice);

		timeline.play();
	}

	private void handleBtnControlHome(ActionEvent event) {

		KeyValue keyValueStackPaneNotice = new KeyValue(noticeStackPane.translateXProperty(), 800);
		KeyFrame keyFrameStackPaneNotice = new KeyFrame(Duration.seconds(1),
			  e -> {
				  LockController.lockRootPane.getChildren().remove(2, LockController.lockRootPane.getChildren().size());
			  }, keyValueStackPaneNotice);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrameStackPaneNotice);

		timeline.play();
		try {
			LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MainController.class.getResource("main.fxml")));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

      private void handleBtnControlAdd(ActionEvent event) {
           // regStackPane.getChildren().remove(0);//regStackPane 본인 제거
           reviseStackPane.getChildren().remove(0); //reviseStackPane 제거.
           
      }
      
      
	
}
