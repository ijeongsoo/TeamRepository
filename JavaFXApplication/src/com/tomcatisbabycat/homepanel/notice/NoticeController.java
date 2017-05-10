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

    private void handleBtnControlBack(ActionEvent event) {
        noticeStackPane.setTranslateX(0);

        KeyValue keyValueStackPaneNotice = new KeyValue(noticeStackPane.translateXProperty(), 800);
        KeyFrame keyFrameStackPaneNotice = new KeyFrame(Duration.seconds(1),
                e -> {
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

}
