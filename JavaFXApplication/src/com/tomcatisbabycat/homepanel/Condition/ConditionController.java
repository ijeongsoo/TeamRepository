package com.tomcatisbabycat.homepanel.Condition;

import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.main.MainController;
import com.tomcatisbabycat.homepanel.menu.MenuController;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
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
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ConditionController implements Initializable {

      @FXML
      private StackPane conditionStackPane;
      @FXML
      private Button btnControlHome;
      @FXML
      private Button btnControlLock;
      @FXML
      private Button btnControlBack;

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
      }
      
      
      public void handleBtnControlBack(ActionEvent event) {

            //1. 먼저 conditionStack을 현재창(0)->180으로 이동시키고 제거.
            
            conditionStackPane.setTranslateX(0);
            KeyValue keyValueStackPane = new KeyValue(conditionStackPane.translateXProperty(), 800);
            KeyFrame keyFrameStackPane = new KeyFrame(Duration.seconds(1),
                    e -> {
                          LockController.lockRootPane.getChildren().remove(2);
                    }, keyValueStackPane);
            Timeline timeline = new Timeline(keyFrameStackPane);
		timeline.play();

            //2. 사실상 2번이 먼저실행되는데, menu.fxml을 로딩하여 스택페인 추가해놓고 그걸 1번인덱스에 위치시킨다.
            //그러면 기존에 1번인덱스에 있던 control스택이 2번인덱스로 밀리는 상태가 된다. 그후 1번처럼 하면 추가된 메뉴페이지가 보이게된다.
            //참고로 add(스택을 추가할 인덱스위치, 인덱스에 추가할 스택내용)
            
            try {
                  LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MenuController.class.getResource("menu.fxml")));
            } catch (IOException ex) {
                  ex.printStackTrace();
            }

      }
      
      public void handleBtnControlLock(ActionEvent event) {

            conditionStackPane.setTranslateX(0);
            KeyValue keyValue = new KeyValue(conditionStackPane.translateXProperty(), 800);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                    e -> {
                          LockController.lockRootPane.getChildren().remove(conditionStackPane); //lock스택 위에 있는 condition스택 제거
                    }, keyValue);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrame);

            timeline.play();
      }

      public void handleBtnControlHome(ActionEvent event) {

            KeyValue keyValue = new KeyValue(conditionStackPane.translateXProperty(), 800);   // 현재 화면상에 놓여진 스택인 conditonStackPane을 x좌표 800으로 치워버림
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), // 800좌표로 치우는 시간을 1초로 설정.
                    e -> {
                          LockController.lockRootPane.getChildren().remove(2, LockController.lockRootPane.getChildren().size());   //치우는 동안 이벤트를 설정하여 해당스택을 제거해버린다.(치운후에 별도로 삭제해줘도 된긴함)
                    }, keyValue);

            Timeline timeline = new Timeline();  //위와 같은 설정으로 애니메이션 진행하기 위해 Timeline객체 생성
            timeline.getKeyFrames().add(keyFrame);// 위에서 설정한 설정을 timeline객체에 셋팅

            timeline.play();   //애니메이션 실행.
            try {
                  LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MainController.class.getResource("main.fxml")));
            } catch (IOException ex) {
                  ex.printStackTrace();
            }
      }

      

}


