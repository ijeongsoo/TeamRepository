/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.cctv;

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
public class CctvController implements Initializable {

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
            btnControlMedia2.setOnAction(event -> {
                  handleBtnControlMedia(event);
            });

            //cctv영상 재생
            //1영상(큰화면) 재생.
            Media media1 = new Media(getClass().getResource("media/cctvSample1.mp4").toString());
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

            //2영상(작은화면) 재생
            Media media2 = new Media(getClass().getResource("media/cctvSample2.mp4").toString());
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

            //3영상(작은화면) 재생
            Media media3 = new Media(getClass().getResource("media/cctvSample3.mp4").toString());
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

            //4영상(작은화면) 재생
            Media media4 = new Media(getClass().getResource("media/cctvSample4.mp4").toString());
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

      private void handleBtnControlMedia(ActionEvent event) {

            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), btnControlMedia2); // 
            scaleTransition.setByX(1.5);
            scaleTransition.setByY(1.5);
            scaleTransition.play();
            
            
            KeyValue keyValueStackPaneMedia2 = new KeyValue(stackPaneMedia2.translateXProperty(), 23);
            KeyFrame keyFrameStackPaneMedia2 = new KeyFrame(Duration.seconds(1), keyValueStackPaneMedia2);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrameStackPaneMedia2);

            timeline.play();
            

      }

}
