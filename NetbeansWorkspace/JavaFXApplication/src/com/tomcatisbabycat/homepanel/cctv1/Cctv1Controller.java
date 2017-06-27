/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.cctv1;

import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.main.MainController;
import com.tomcatisbabycat.homepanel.menu.MenuController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kang
 */
public class Cctv1Controller implements Initializable {

      @FXML
      private StackPane cctvStackPane;
      @FXML
      private Button btnControlHome;
      @FXML
      private Button btnControlLock;
      @FXML
      private Button btnControlBack;

      @FXML
      private Button btnControlMedia1;
      @FXML
      private Button btnControlMedia2;
      @FXML
      private Button btnControlMedia4;
      @FXML
      private Button btnControlMedia3;
      @FXML
      private StackPane stackPaneMedia1;
      @FXML
      private MediaView mediaView1;
      @FXML
      private StackPane stackPaneMedia2;
      @FXML
      private MediaView mediaView2;
      @FXML
      private StackPane stackPaneMedia4;
      @FXML
      private MediaView mediaView4;
      @FXML
      private StackPane stackPaneMedia3;
      @FXML
      private MediaView mediaView3;

      StackPane stackPaneMedia;
      Button btnControlMedia;

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
            btnControlMedia1.setOnAction(event -> {
                  handleBtnControlMedia1(event);
            });
            btnControlMedia2.setOnAction(event -> {
                  handleBtnControlMedia2(event);
            });
            btnControlMedia3.setOnAction(event -> {
                  handleBtnControlMedia3(event);
            });
            btnControlMedia4.setOnAction(event -> {
                  handleBtnControlMedia4(event);
            });

            //cctv영상 재생
            //1영상 재생.
            Media media1 = new Media(getClass().getResource("media/01.mp4").toString());
            MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
            mediaView1.setMediaPlayer(mediaPlayer1);
            mediaPlayer1.setOnReady(new Runnable() {
                  @Override
                  public void run() {
                        setAutoPlay(true);
                  }

                  private void setAutoPlay(boolean b) {
                        mediaPlayer1.play();
                  }               
            });

            //2영상 재생
            Media media2 = new Media(getClass().getResource("media/02.mp4").toString());
            MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
            mediaView2.setMediaPlayer(mediaPlayer2);
            mediaPlayer2.setOnReady(new Runnable() {
                  @Override
                  public void run() {
                        setAutoPlay(true);
                  }

                  private void setAutoPlay(boolean b) {
                        mediaPlayer2.play();
                  }
            });

            //3영상 재생
            Media media3 = new Media(getClass().getResource("media/03.mp4").toString());
            MediaPlayer mediaPlayer3 = new MediaPlayer(media3);
            mediaView3.setMediaPlayer(mediaPlayer3);
            mediaPlayer3.setOnReady(new Runnable() {
                  @Override
                  public void run() {
                        setAutoPlay(true);
                  }

                  private void setAutoPlay(boolean b) {

                        mediaPlayer3.play();

                  }
            });

            //4영상 재생
            Media media4 = new Media(getClass().getResource("media/04.mp4").toString());
            MediaPlayer mediaPlayer4 = new MediaPlayer(media4);
            mediaView4.setMediaPlayer(mediaPlayer4);
            mediaPlayer4.setOnReady(new Runnable() {
                  @Override
                  public void run() {
                        setAutoPlay(true);
                  }

                  private void setAutoPlay(boolean b) {
                        mediaPlayer4.play();
                  }
            });

      }

      private void handleBtnControlBack(ActionEvent event) {
            cctvStackPane.setTranslateX(0);

            KeyValue keyValueStackPaneCCTV = new KeyValue(cctvStackPane.translateXProperty(), 800);
            KeyFrame keyFrameStackPaneCCTV = new KeyFrame(Duration.seconds(1),
                    e -> {
                          LockController.lockRootPane.getChildren().remove(2);
                    }, keyValueStackPaneCCTV);

            Timeline timeline = new Timeline(keyFrameStackPaneCCTV);
            timeline.play();
           
            try {
                  LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MenuController.class.getResource("menu.fxml")));
            } catch (IOException ex) {
                  ex.printStackTrace();
            }
      }

      private void handleBtnControlLock(ActionEvent event) {
            //StackPane rootPane = (StackPane) cctvStackPane.getScene().getRoot(); // 컨트롤을 통해서 현재 Scene을 얻고 root의 객체를 얻는다.

            cctvStackPane.setTranslateX(0);
            KeyValue keyValueStackPaneCCTV = new KeyValue(cctvStackPane.translateXProperty(), 800);
            KeyFrame keyFrameStackPaneCCTV = new KeyFrame(Duration.seconds(1),
                    e -> {
                          //
                          LockController.lockRootPane.getChildren().remove(cctvStackPane);
                    }, keyValueStackPaneCCTV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrameStackPaneCCTV);

            timeline.play();
      }

      private void handleBtnControlHome(ActionEvent event) {

            KeyValue keyValueStackPaneCCTV = new KeyValue(cctvStackPane.translateXProperty(), 800);
            KeyFrame keyFrameStackPaneCCTV = new KeyFrame(Duration.seconds(1),
                    e -> {
                          LockController.lockRootPane.getChildren().remove(2, LockController.lockRootPane.getChildren().size());
                    }, keyValueStackPaneCCTV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrameStackPaneCCTV);

            timeline.play();
            try {
                  LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MainController.class.getResource("main.fxml")));
            } catch (Exception ex) {
                  ex.printStackTrace();
            }

      }

      private void handleBtnControlMedia1(ActionEvent event) {
            //1. 2영상(작은화면)이 큰화면으로 이동.
            //작은화면을 클릭하면 큰화면 위치로 이동 (x,y좌표 모두 이동함)
            if (stackPaneMedia1.getTranslateX() != -390.0) {  // 이동후에는 더이상의 이동이 생기지 않도록 하기 위해
                  stackPaneMedia1.setTranslateX(0);
                  stackPaneMedia1.setTranslateY(0);
                  KeyValue keyValueStackPaneMedia2x = new KeyValue(stackPaneMedia1.translateXProperty(), -390);
                  KeyFrame keyFrameStackPaneMedia2x = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia2x);
                  KeyValue keyValueStackPaneMedia2y = new KeyValue(stackPaneMedia1.translateYProperty(), 105);
                  KeyFrame keyFrameStackPaneMedia2y = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia2y);

                  Timeline timeline1 = new Timeline();
                  timeline1.getKeyFrames().add(keyFrameStackPaneMedia2x);
                  timeline1.getKeyFrames().add(keyFrameStackPaneMedia2y);
                  timeline1.play();
            } else {
                  return;
            }

            //화면이 이동하면서 사이즈가 커짐.
            if (btnControlMedia1.scaleXProperty().getValue() < 1.2) {   //사이즈가 누를때마다 커지는 것을 막기 위해
                  ScaleTransition scaleTransition1 = new ScaleTransition(Duration.seconds(1), btnControlMedia1); // 
                  scaleTransition1.setByX(1.4);  //1.4배로 커짐
                  scaleTransition1.setByY(1.6); //1.6배로 커짐.
                  scaleTransition1.play();
            } else {
                  return;
            }
            stackPaneMedia = stackPaneMedia1;
            btnControlMedia = btnControlMedia1;

            //2. 기존의 1영상(큰화면)이 작은화면으로 이동.
            stackPaneMedia.setTranslateX(0);
            stackPaneMedia.setTranslateY(0);
            KeyValue keyValueStackPaneMedia1x = new KeyValue(stackPaneMedia.translateXProperty(), 390);
            KeyFrame keyFrameStackPaneMedia1x = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia1x);
            KeyValue keyValueStackPaneMedia1y = new KeyValue(stackPaneMedia.translateYProperty(), -105);
            KeyFrame keyFrameStackPaneMedia1y = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia1y);

            Timeline timeline2 = new Timeline();
            timeline2.getKeyFrames().add(keyFrameStackPaneMedia1x);
            timeline2.getKeyFrames().add(keyFrameStackPaneMedia1y);
            timeline2.play();

            //화면이 이동하면서 사이즈가 작아짐.
            if (btnControlMedia.scaleXProperty().getValue() > 0.8) {  //사이즈가 누를때마다 작아지는 것을 막기위해.
                  ScaleTransition scaleTransition2 = new ScaleTransition(Duration.seconds(1), btnControlMedia); // 
                  scaleTransition2.setByX(-1.4);  //1.4배로 작아짐
                  scaleTransition2.setByY(-1.4); //1.4배로 작아짐.
                  scaleTransition2.play();
            } else {
                  return;
            }
            stackPaneMedia2 = stackPaneMedia;
            btnControlMedia2 = btnControlMedia;
      }

      private void handleBtnControlMedia2(ActionEvent event) {

            //1. 2영상(작은화면)이 큰화면으로 이동.
            //작은화면을 클릭하면 큰화면 위치로 이동 (x,y좌표 모두 이동함)
            if (stackPaneMedia2.getTranslateX() != -390.0) {  // 이동후에는 더이상의 이동이 생기지 않도록 하기 위해
                  stackPaneMedia2.setTranslateX(0);
                  stackPaneMedia2.setTranslateY(0);
                  KeyValue keyValueStackPaneMedia2x = new KeyValue(stackPaneMedia2.translateXProperty(), -390);
                  KeyFrame keyFrameStackPaneMedia2x = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia2x);
                  KeyValue keyValueStackPaneMedia2y = new KeyValue(stackPaneMedia2.translateYProperty(), 105);
                  KeyFrame keyFrameStackPaneMedia2y = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia2y);

                  Timeline timeline1 = new Timeline();
                  timeline1.getKeyFrames().add(keyFrameStackPaneMedia2x);
                  timeline1.getKeyFrames().add(keyFrameStackPaneMedia2y);
                  timeline1.play();
            } else {
                  return;
            }

            //화면이 이동하면서 사이즈가 커짐.
            if (btnControlMedia2.scaleXProperty().getValue() < 1.2) {   //사이즈가 누를때마다 커지는 것을 막기 위해
                  ScaleTransition scaleTransition1 = new ScaleTransition(Duration.seconds(1), btnControlMedia2); // 
                  scaleTransition1.setByX(1.4);  //1.4배로 커짐
                  scaleTransition1.setByY(1.6); //1.6배로 커짐.
                  scaleTransition1.play();
                  
            } else {
                  return;
            }
            stackPaneMedia = stackPaneMedia2;
           btnControlMedia = btnControlMedia2;

            //2. 기존의 1영상(큰화면)이 작은화면으로 이동.
            stackPaneMedia.setTranslateX(0);
            stackPaneMedia.setTranslateY(0);
            KeyValue keyValueStackPaneMedia1x = new KeyValue(stackPaneMedia.translateXProperty(), 390);
            KeyFrame keyFrameStackPaneMedia1x = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia1x);
            KeyValue keyValueStackPaneMedia1y = new KeyValue(stackPaneMedia.translateYProperty(), -105);
            KeyFrame keyFrameStackPaneMedia1y = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia1y);

            Timeline timeline2 = new  Timeline();
            timeline2.getKeyFrames().add(keyFrameStackPaneMedia1x);
            timeline2.getKeyFrames().add(keyFrameStackPaneMedia1y);
            timeline2.play();

            //화면이 이동하면서 사이즈가 작아짐.
            if (btnControlMedia.scaleXProperty().getValue() > 0.8) {  //사이즈가 누를때마다 작아지는 것을 막기위해.
                  ScaleTransition scaleTransition2 = new ScaleTransition(Duration.seconds(1), btnControlMedia); // 
                  scaleTransition2.setByX(-1.4);  //1.4배로 작아짐
                  scaleTransition2.setByY(-1.4); //1.4배로 작아짐.
                  scaleTransition2.play();
            } else {
                  return;
            }
            stackPaneMedia3 = stackPaneMedia;
            btnControlMedia3 = btnControlMedia;
      }

      private void handleBtnControlMedia3(ActionEvent event) {
            //1. 3영상(작은화면)이 큰화면으로 이동.
            //작은화면을 클릭하면 큰화면 위치로 이동 (x,y좌표 모두 이동함)
            if (stackPaneMedia3.getTranslateX() != -390.0) {
                  stackPaneMedia3.setTranslateX(0);
                  stackPaneMedia3.setTranslateY(0);
                  KeyValue keyValueStackPaneMedia2x = new KeyValue(stackPaneMedia3.translateXProperty(), -390);
                  KeyFrame keyFrameStackPaneMedia2x = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia2x);
                  KeyValue keyValueStackPaneMedia2y = new KeyValue(stackPaneMedia3.translateYProperty(), -30);
                  KeyFrame keyFrameStackPaneMedia2y = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia2y);

                  Timeline timeline1 = new Timeline();
                  timeline1.getKeyFrames().add(keyFrameStackPaneMedia2x);
                  timeline1.getKeyFrames().add(keyFrameStackPaneMedia2y);
                  timeline1.play();
            } else {
                  return;
            }

            //화면이 이동하면서 사이즈가 커짐.
            if (btnControlMedia3.scaleXProperty().getValue() < 1.2) {
                  ScaleTransition scaleTransition1 = new ScaleTransition(Duration.seconds(1), btnControlMedia3); // 
                  scaleTransition1.setByX(1.4);  //1.4배로 커짐
                  scaleTransition1.setByY(1.6); //1.6배로 커짐.
                  scaleTransition1.play();
            } else {
                  return;
            }
            stackPaneMedia = stackPaneMedia3;
            btnControlMedia = btnControlMedia3;

            //2. 기존의 1영상(큰화면)이 작은화면으로 이동.
            stackPaneMedia.setTranslateX(0);
            stackPaneMedia.setTranslateY(0);
            KeyValue keyValueStackPaneMedia1x = new KeyValue(stackPaneMedia.translateXProperty(), 390);
            KeyFrame keyFrameStackPaneMedia1x = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia1x);
            KeyValue keyValueStackPaneMedia1y = new KeyValue(stackPaneMedia.translateYProperty(), 30);
            KeyFrame keyFrameStackPaneMedia1y = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia1y);

            Timeline timeline2 = new Timeline();
            timeline2.getKeyFrames().add(keyFrameStackPaneMedia1x);
            timeline2.getKeyFrames().add(keyFrameStackPaneMedia1y);
            timeline2.play();

            //화면이 이동하면서 사이즈가 작아짐
            if (btnControlMedia.scaleXProperty().getValue() > 0.8) {
                  ScaleTransition scaleTransition2 = new ScaleTransition(Duration.seconds(1), btnControlMedia); // http://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/BorderPane.html
                  scaleTransition2.setByX(-1.4);  //1.4배로 작아짐
                  scaleTransition2.setByY(-1.4); //1.4배로 작아짐
                  scaleTransition2.play();
            } else {
                  return;
            }
            stackPaneMedia4 = stackPaneMedia;
            btnControlMedia4 = btnControlMedia;
      }

      private void handleBtnControlMedia4(ActionEvent event) {
            //1. 4영상(작은화면)이 큰화면으로 이동.
            //작은화면을 클릭하면 큰화면 위치로 이동 (x,y좌표 모두 이동함)
            if (stackPaneMedia4.getTranslateX() != -390.0) {
                  stackPaneMedia4.setTranslateX(0);
                  stackPaneMedia4.setTranslateY(0);
                  KeyValue keyValueStackPaneMedia2x = new KeyValue(stackPaneMedia4.translateXProperty(), -390);
                  KeyFrame keyFrameStackPaneMedia2x = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia2x);
                  KeyValue keyValueStackPaneMedia2y = new KeyValue(stackPaneMedia4.translateYProperty(), -50);
                  KeyFrame keyFrameStackPaneMedia2y = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia2y);

                  Timeline timeline1 = new Timeline();
                  timeline1.getKeyFrames().add(keyFrameStackPaneMedia2x);
                  timeline1.getKeyFrames().add(keyFrameStackPaneMedia2y);
                  timeline1.play();
            } else {
                  return;
            }

            //화면이 이동하면서 사이즈가 커짐.
            if (btnControlMedia4.scaleXProperty().getValue() < 1.2) {
                  ScaleTransition scaleTransition1 = new ScaleTransition(Duration.seconds(1), btnControlMedia4); // 
                  scaleTransition1.setByX(1.4);  //1.4배로 커짐
                  scaleTransition1.setByY(1.6); //1.6배로 커짐.
                  scaleTransition1.play();
            } else {
                  return;
            }
            stackPaneMedia = stackPaneMedia4;
            btnControlMedia = btnControlMedia4;

            //2. 기존의 1영상(큰화면)이 작은화면으로 이동.
            stackPaneMedia.setTranslateX(0);
            stackPaneMedia.setTranslateY(0);
            KeyValue keyValueStackPaneMedia1x = new KeyValue(stackPaneMedia.translateXProperty(), 390);
            KeyFrame keyFrameStackPaneMedia1x = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia1x);
            KeyValue keyValueStackPaneMedia1y = new KeyValue(stackPaneMedia.translateYProperty(), 30);
            KeyFrame keyFrameStackPaneMedia1y = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia1y);

            Timeline timeline2 = new Timeline();
            timeline2.getKeyFrames().add(keyFrameStackPaneMedia1x);
            timeline2.getKeyFrames().add(keyFrameStackPaneMedia1y);
            timeline2.play();

            //화면이 이동하면서 사이즈가 작아짐
            if (btnControlMedia.scaleXProperty().getValue() > 0.8) {
                  ScaleTransition scaleTransition2 = new ScaleTransition(Duration.seconds(1), btnControlMedia);
                  scaleTransition2.setByX(-1.4);  //1.4배로 작아짐
                  scaleTransition2.setByY(-1.4); //1.4배로 작아짐
                  scaleTransition2.play();
            } else {
                  return;
            }
            stackPaneMedia1 = stackPaneMedia;
            btnControlMedia1 = btnControlMedia;

      }

}
