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

      public void handleBtnControlLock(ActionEvent event) {
            //StackPane rootPane = (StackPane) cctvStackPane.getScene().getRoot(); // 컨트롤을 통해서 현재 Scene을 얻고 root의 객체를 얻는다.

            conditionStackPane.setTranslateX(0);

            KeyValue keyValue = new KeyValue(conditionStackPane.translateXProperty(), 800);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                    e -> {
                          //rootPane.getChildren().removeAll(MainController.paneBasket);
                          //rootPane.getChildren().remove(cctvStackPane);
                          Iterator iterator = LockController.lockRootPane.getChildren().iterator();
                          while (iterator.hasNext()) {
                                if (iterator.next() instanceof StackPane) {
                                      iterator.remove();
                                }
                          }
                    }, keyValue);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrame);

            timeline.play();
      }

      public void handleBtnControlHome(ActionEvent event) {
            StackPane rootPane = (StackPane) btnControlHome.getScene().getRoot(); // 지금 현재 컨트롤하려는 conditionStack을 명시해둠.

            //StackPane.setTranslateX(0);  //이미 화면상에 스택이 놓여진 상황일테니 다시 생성할 필요 없음.
            KeyValue keyValue = new KeyValue(conditionStackPane.translateXProperty(), 800);   // 현재 화면상에 놓여진 스택인 conditonStackPane을 x좌표 800으로 치워버림
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), // 800좌표로 치우는 시간을 1초로 설정.
                    e -> {
                          rootPane.getChildren().remove(conditionStackPane);   //치우는 동안 이벤트를 설정하여 해당스택을 제거해버린다.(치운후에 별도로 삭제해줘도 된긴함)
                    }, keyValue);

            Timeline timeline = new Timeline();  //위와 같은 설정으로 애니메이션 진행하기 위해 Timeline객체 생성
            timeline.getKeyFrames().add(keyFrame);// 위에서 설정한 설정을 timeline객체에 셋팅

            timeline.play();   //애니메이션 실행.
            try {
                  LockController.lockRootPane.getChildren().add(FXMLLoader.load(MainController.class.getResource("main.fxml")));
            } catch (IOException ex) {
                  Logger.getLogger(ConditionController.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
//		Thread task = new Thread() {    //스레드를 사용한 이유는 동시에 처리하기 위함. 즉 현재 창을 제거하는 애니메이션 작업과 홈화면을 불러오는 작업을 각각 스래드로 하여 동시작업.
//			@Override
//			public void run() {
//				try {
//					//Thread.sleep(1000);
//					Platform.runLater(() -> {
//						try {
//							LockController.lockRootPane.getChildren().add(FXMLLoader.load(MainController.class.getResource("main.fxml")));
//                       //lockRootPane이 가장 밑스택에 있으므로 그 위에 메인 스택을 띄움. / MainController.class가 위치한 패키지에서 main.fxml파일을 찾아 로딩하여 해당 스택을 쌓기.
//				
//                                    } catch (IOException ex) {
//							Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
//						}
//
//					});
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//			}
//
//		};
//		task.start();

      public void handleBtnControlBack(ActionEvent event) {

            //1. 먼저 conditionStack을 현재창(0)->180으로 이동시키고 제거.
            
            KeyValue keyValue = new KeyValue(conditionStackPane.translateXProperty(), 800);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                    e -> {
                          //rootPane.getChildren().removeAll(MainController.paneBasket);
                          //rootPane.getChildren().remove(cctvStackPane);
                          Iterator iterator = LockController.lockRootPane.getChildren().iterator();
                          while (iterator.hasNext()) {
                                if (iterator.next() instanceof StackPane) {
                                      iterator.remove();
                                }
                          }
                    }, keyValue);

            //2. -180에 meneStack을 생성하여 그것을 -180->0으로 이동시킴.   이때 1번과 2번 내용을 스래드로 동시에 처리함.
            Thread thread = new Thread() {
                  @Override
                  public void run() {
                        
                  }
            };

      }

}
