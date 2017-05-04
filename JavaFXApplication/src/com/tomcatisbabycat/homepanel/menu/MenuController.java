/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.menu;

import com.tomcatisbabycat.homepanel.Condition.ConditionController;
import com.tomcatisbabycat.homepanel.cctv.CctvController;
import com.tomcatisbabycat.homepanel.consume.ConsumeController;
import com.tomcatisbabycat.homepanel.light.LightController;
import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.main.MainController;
import com.tomcatisbabycat.homepanel.notice.NoticeController;
import com.tomcatisbabycat.homepanel.schedule.ScheduleController;
import com.tomcatisbabycat.homepanel.setting.SettingController;
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
	@FXML
	private Button btnConsume;
	@FXML
	private Button btnLight;
	@FXML
	private Button btnNotice;
	@FXML
	private Button btnSchedule;
      @FXML
      private Button btnSetting;

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
		btnConsume.setOnAction(event->{
			handleBtnConsume(event);
		});
		btnControlLock.setOnAction(event->{
			handleBtnControlLock(event);
		});
		btnLight.setOnAction(event->{
			handleBtnLight(event);
		});
		btnNotice.setOnAction(event->{
			handleBtnNotice(event);
		});
		btnSchedule.setOnAction(event->{
			handleBtnSchedule(event);
		});
            btnSetting.setOnAction(event->{
                  handleBtnSchedule(event);
            });
            
	}
	private void handleBtnControlHome(ActionEvent event) {
		//menuStackPane.setTranslateX(0);

		KeyValue keyValue = new KeyValue(menuStackPane.translateXProperty(), 800);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
			  e -> {
				  // 키프레임에 이벤트를 넣어서 이벤트가 종료된시점에 메뉴페이지를 삭제 한다. 애니메이션이 동작하기전에 아래 코드에서 인덱스 1에 메인페이지를 추가하면서 현재 있는 메뉴페이지의 
				  // 인덱스는 2가 되고 리스트의 사이즈는 잠시동안 3이 되었다가 애니메이션이 종료되면서 메뉴페이지를 삭제하면 리스트의 사이즈는 2가 된다.
				  LockController.lockRootPane.getChildren().remove(2);
			  }, keyValue);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();
		
		// 인덱스를 이용해 페이지의 선후관계를 설정한후에 애니메이션 처리를하면 메인페이지가 지워질 메뉴페이지 아래에 생성되기 때문에 애니메이션 처리에서 영향을 받지 않는다.
		try {
			LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MainController.class.getResource("main.fxml")));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	private void handleBtnControlLock(ActionEvent event){
		// menu에서의 Lock 이벤트처리 일단 안함
		LockController.lockRootPane.getChildren().remove(menuStackPane);
	}

	private void handleBtnCCTV(ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(CctvController.class.getResource("cctv.fxml"));
			
			// 메뉴페이지 위에다 CCTV페이지를 추가한다 현재 리스트의 사이즈는 2이고, 메뉴페이지는 1 인덱스를 가지고 있다
			LockController.lockRootPane.getChildren().add(2, parent);
			// 페이지를 추가하면 리스트의 사이즈가 잠시 3으로 늘어난다.
			
			parent.setTranslateX(800);

			KeyValue keyValueStackPaneCCTV = new KeyValue(parent.translateXProperty(), 0);
			KeyFrame keyFrameStackPaneCCTV = new KeyFrame(Duration.seconds(1), keyValueStackPaneCCTV);
			
			Timeline timeline = new Timeline(keyFrameStackPaneCCTV);
			timeline.play();
			
			timeline.statusProperty().addListener((observable, oldValue, newValue) -> {
				if(newValue.toString().equals("STOPPED")){
					// 애니메이션이 끝났을때 메뉴페이지 삭제
					// 삭제를 하면 리스트의 사이즈는 2로 변하고 인덱스 1 에는 cctv페이지가 들어간다.
					LockController.lockRootPane.getChildren().remove(1);
				}
			});
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
      private void handleBtnCondition(ActionEvent event){
            try {
                  Parent parent = FXMLLoader.load(ConditionController.class.getResource("condition.fxml"));
                  //condition.fxml을 로딩시키는 것으로써 condition.fxml파일이 해당 패키지안에 있지않으므로 그 경로를 찾기위해 conditionControl.class가 있는 패키지에서 getResource를 통해 condition.fxml의 경로를 찾아 로딩.

                  LockController.lockRootPane.getChildren().add(2, parent); //메뉴의 stackpane에 또하나의 stack(condition.fxml을 로딩한 parent)를 추가한것
                  
                  parent.setTranslateX(800); //추가된 stack을 x좌표 800에 생성시킴.frame에서 벗어나 생성된것이므로 화면에 보이지는 않는다.
                  KeyValue keyValue = new KeyValue(parent.translateXProperty(),0);//변화시킬 translateX 속성을 얻어내고, frame밖에서 생성된 stack을 화면frame으로(0좌표를 종료값으로) 갖다놓는것.
                  KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),keyValue);//화면frame으로 옮겨오는 과정에 1초의 지연시간을 주어 애니메이션 효과를 입힘.
                  
                  Timeline timeline = new Timeline();// 애니메이션을 실현하기 위해 timeline객체 생성.
                  timeline.getKeyFrames().add(keyFrame);//생성된 timeline객체에 설정한 keyframe를 셋팅.
                  timeline.play();//애니메이션 실행.
                  
                  timeline.statusProperty().addListener((observable, oldValue, newValue) -> {
				if(newValue.toString().equals("STOPPED")){
					// 애니메이션이 끝났을때 메뉴페이지 삭제
					// 삭제를 하면 리스트의 사이즈는 2로 변하고 인덱스 1 에는 cctv페이지가 들어간다.
					LockController.lockRootPane.getChildren().remove(1);
				}
			});
                  
            } catch (IOException ex) {
                  ex.printStackTrace();
            }
      }

	private void handleBtnConsume(ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(ConsumeController.class.getResource("consume.fxml"));
			
			LockController.lockRootPane.getChildren().add(2, parent);
			parent.setTranslateX(800);
			
			KeyValue keyValueStackPaneConsume = new KeyValue(parent.translateXProperty(), 0);
			KeyFrame keyFrameStackPaneConsume = new KeyFrame(Duration.seconds(1), keyValueStackPaneConsume);
			
			Timeline timeline = new Timeline(keyFrameStackPaneConsume);
			timeline.play();
			
			timeline.statusProperty().addListener((observable, oldValue, newValue) -> {
				if(newValue.toString().equals("STOPPED")){
					LockController.lockRootPane.getChildren().remove(1);
				}
			});
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	private void handleBtnLight(ActionEvent event){
		try {
			Parent parent = FXMLLoader.load(LightController.class.getResource("light.fxml"));
			
			LockController.lockRootPane.getChildren().add(2, parent);
			parent.setTranslateX(800);
			
			KeyValue keyValueStackPaneLight = new KeyValue(parent.translateXProperty(), 0);
			KeyFrame keyFrameStackPaneLight = new KeyFrame(Duration.seconds(1), keyValueStackPaneLight);
			
			Timeline timeline = new Timeline(keyFrameStackPaneLight);
			timeline.play();
			
			timeline.statusProperty().addListener((observable, oldValue, newValue) -> {
				if(newValue.toString().equals("STOPPED")){
					LockController.lockRootPane.getChildren().remove(1);
				}
			});
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	private void handleBtnNotice(ActionEvent event){
		try {
			Parent parent = FXMLLoader.load(NoticeController.class.getResource("notice.fxml"));
			
			LockController.lockRootPane.getChildren().add(2, parent);
			parent.setTranslateX(800);
			
			KeyValue keyValueStackPaneNotice = new KeyValue(parent.translateXProperty(), 0);
			KeyFrame keyFrameStackPaneNotice = new KeyFrame(Duration.seconds(1), keyValueStackPaneNotice);
			
			Timeline timeline = new Timeline(keyFrameStackPaneNotice);
			timeline.play();
			
			timeline.statusProperty().addListener((observable, oldValue, newValue) -> {
				if(newValue.toString().equals("STOPPED")){
					LockController.lockRootPane.getChildren().remove(1);
				}
			});
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	private void handleBtnSchedule(ActionEvent event){
		try {
			Parent parent = FXMLLoader.load(ScheduleController.class.getResource("schedule.fxml"));
			
			LockController.lockRootPane.getChildren().add(2, parent);
			parent.setTranslateX(800);
			
			KeyValue keyValueStackPaneSchedule = new KeyValue(parent.translateXProperty(), 0);
			KeyFrame keyFrameStackPaneSchedule = new KeyFrame(Duration.seconds(1), keyValueStackPaneSchedule);
			
			Timeline timeline = new Timeline(keyFrameStackPaneSchedule);
			timeline.play();
			
			timeline.statusProperty().addListener((observable, oldValue, newValue) -> {
				if(newValue.toString().equals("STOPPED")){
					LockController.lockRootPane.getChildren().remove(1);
				}
			});
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
      
      private void handleBtnSetting(ActionEvent event){
            try {
                  Parent parent = FXMLLoader.load(SettingController.class.getResource("setting.fxml"));
                  
                  LockController.lockRootPane.getChildren().add(2,parent);
                  parent.setTranslateX(800);
                  
                  KeyValue keyValue = new KeyValue(parent.translateXProperty(),0);
                  KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
                  
                  Timeline timeline = new Timeline(keyFrame);
                  timeline.play();
                  
                  timeline.statusProperty().addListener((observable, oldValue, newValue)->{
                        if(newValue.toString().equals("STOPPED")){
                              LockController.lockRootPane.getChildren().remove(1);
                        }
                  });
            } catch (IOException ex) {
                  ex.printStackTrace();
            }
      }

}
