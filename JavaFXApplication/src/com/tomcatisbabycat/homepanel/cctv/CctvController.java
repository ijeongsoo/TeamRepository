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
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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

      StackPane stackPaneMedia;
      Button btnControlMedia;
      @FXML
      private ImageView imageView1;
      @FXML
      private ImageView imageView2;
      @FXML
      private ImageView imageView3;
      @FXML
      private ImageView imageView4;
      @FXML
      private Button btnControlImage1;
      @FXML
      private Button btnControlImage2;
      @FXML
      private Button btnControllmage3;
      @FXML
      private Button btnControlImage4;

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
            btnControlImage2.setOnAction(event -> {
                  handleBtnControlImage2(event);
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

      private void handleBtnControlImage2(ActionEvent event) {
            
            //imageView1.setImage(new Image(getClass().getResource("image2\"+)));
      }

}
