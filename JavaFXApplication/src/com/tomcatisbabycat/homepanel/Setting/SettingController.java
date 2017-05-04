/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.setting;

import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.menu.MenuController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class SettingController implements Initializable {

      @FXML
      private StackPane settingStackPane;
      @FXML
      private Button btnControlHome;
      @FXML
      private Button btnControlLock;
      @FXML
      private Button btnControlBack;

      /**
       * Initializes the controller class.
       */
      @Override
      public void initialize(URL url, ResourceBundle rb) {
           btnControlHome.setOnAction(event->{
                 handleBtnControlHome(event);
           });
           btnControlLock.setOnAction(event->{
                 handleBtnControlLock(event);
           });
           btnControlBack.setOnAction(event->{
                 handleBtnControlBack(event);
           });
      }

      private void handleBtnControlHome(ActionEvent event) {
           
            KeyValue keyValue = new KeyValue(settingStackPane.translateXProperty(),800);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                    e -> {
                          LockController.lockRootPane.getChildren().remove(2,LockController.lockRootPane.getChildren().size());
                    },keyValue);
            
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            
            try {
                  LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MenuController.class.getResource("main.fxml")));
            } catch (IOException ex) {
                  ex.printStackTrace();
            }
      }

      private void handleBtnControlLock(ActionEvent event) {
           
            settingStackPane.setTranslateX(0);
            KeyValue keyValue = new KeyValue(settingStackPane.translateXProperty(),800);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                    e -> {
                          LockController.lockRootPane.getChildren().remove(settingStackPane);
                    },keyValue);
            
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
           
      }

      private void handleBtnControlBack(ActionEvent event) {
            
            KeyValue keyValue = new KeyValue(settingStackPane.translateXProperty(),800);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
                    e -> {
                          LockController.lockRootPane.getChildren().remove(2,LockController.lockRootPane.getChildren().size());
                    },keyValue);
            
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            
            try {
                  LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MenuController.class.getResource("menu.fxml")));
            } catch (IOException ex) {
                  ex.printStackTrace();
            }
      }

      
      
}
