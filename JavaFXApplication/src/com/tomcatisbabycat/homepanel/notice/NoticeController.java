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
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
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
    private ComboBox<String> yearCombo;
    @FXML
    private ComboBox<String> monthCombo;
    @FXML
    private ComboBox<String> dayCombo;
    @FXML
    private Button btnControlAdd;
    @FXML
    private TableView<String> noticeTableView;
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
        btnControlBack.setOnAction(event -> {
            handleBtnControlBack(event);
        });
        btnControlAdd.setOnAction(event -> {
            handleBtnControlAdd(event);
        });
        btnControlReg.setOnAction(event -> {
            handleBtnControlReg(event);
        });
        
        ObservableList<String> combo1 = FXCollections.observableArrayList();   //combo박스 년도 item입력
        combo1.add("2017년");
        combo1.add("2018년");
        combo1.add("2019년");
        combo1.add("2020년");
        yearCombo.setItems(combo1);

        ObservableList<String> combo2 = FXCollections.observableArrayList();    //combo박스 월 item입력
        combo2.addAll("1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월");
        monthCombo.setItems(combo2);
        
        ObservableList<String> combo3 = FXCollections.observableArrayList();      // combo박스 일 item 입력
        combo3.addAll("1일","2일","3일","4일","5일","6일","7일","8일","9일","10일","11일","12일","13일","14일","15일","16일","17일","18일",
                "19일","20일","21일","22일","23일","24일","25일","26일","27일","28일","29일","30일","31일");
        dayCombo.setItems(combo3);
        
        
    }

    private void handleBtnControlAdd(ActionEvent event) {
        // regStackPane.getChildren().remove(0);//regStackPane 본인 제거
        // reviseStackPane.getChildren().remove(0); //reviseStackPane 제거.

    }

    private void handleBtnControlReg(ActionEvent event) {
        ObservableList<String> list1 = FXCollections.observableArrayList();
       ObservableList<String> list2 = FXCollections.observableArrayList();
        

        list1.add(noticeTextArea.getText());//tableView 첫 열(Contents에 넣어줄 list)
        
       // System.out.println(list1);
        list2.add(yearCombo.getValue()+monthCombo.getValue()+dayCombo.getValue());   //tableView 둘째열(Date에 넣어줄 list)
        //System.out.println(list2);
        //TableColumn tc = noticeTableView.getColumns().set(0,);
        
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
      
	
}
