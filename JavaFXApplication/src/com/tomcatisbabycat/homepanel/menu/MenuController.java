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
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
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
	@FXML
	private Rectangle menuBack;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.gc();
		
		btnCCTV.setFocusTraversable(false);
		btnCondition.setFocusTraversable(false);
		btnConsume.setFocusTraversable(false);
		btnControlHome.setFocusTraversable(false);
		btnControlLock.setFocusTraversable(false);
		btnLight.setFocusTraversable(false);
		btnNotice.setFocusTraversable(false);
		btnSchedule.setFocusTraversable(false);
		btnSetting.setFocusTraversable(false);
		
		btnControlHome.setOnAction(event -> {
			handleBtnControlHome(event);
		});
		btnCCTV.setOnAction(event -> {
			handleBtnCCTV(event);
		});
		btnCondition.setOnAction(event -> {
			handleBtnCondition(event);
		});
		btnConsume.setOnAction(event -> {
			handleBtnConsume(event);
		});
		btnControlLock.setOnAction(event -> {
			handleBtnControlLock(event);
		});
		btnLight.setOnAction(event -> {
			handleBtnLight(event);
		});
		btnNotice.setOnAction(event -> {
			handleBtnNotice(event);
		});
		btnSchedule.setOnAction(event -> {
			handleBtnSchedule(event);
		});
		btnSetting.setOnAction(event -> {
			handleBtnSetting(event);
		});

	}

	private void handleBtnControlHome(ActionEvent event) {
		//menuStackPane.setTranslateX(0);
		
		try {
			StackPane parent = FXMLLoader.load(MainController.class.getResource("main.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

			// 메인 페이지가 추가된상태에서 현재 리스트의 사이즈는 2 이다 , 이 사이즈를 가지고 다음에 추가할 메뉴의 인덱스를 설정하면서 메뉴페이지를 추가한다.
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);
			//parent.setTranslateX(800);

			KeyValue keyValueStackPaneMenu = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPaneMenu = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPaneMenu);
			
			

			// 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
			//KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			//KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPaneMenu);
			timeline.play();
			
		} catch (IOException ex) {
		}

		

		// 인덱스를 이용해 페이지의 선후관계를 설정한후에 애니메이션 처리를하면 메인페이지가 지워질 메뉴페이지 아래에 생성되기 때문에 애니메이션 처리에서 영향을 받지 않는다.
		
	}

	private void handleBtnControlLock(ActionEvent event) {
		// menu에서의 Lock 이벤트처리 일단 안함
		LockController.lockRootPane.getChildren().remove(menuStackPane);
	}

	private void handleBtnCCTV(ActionEvent event) {
		try {
			StackPane parent = FXMLLoader.load(CctvController.class.getResource("cctv.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

			// 메인 페이지가 추가된상태에서 현재 리스트의 사이즈는 2 이다 , 이 사이즈를 가지고 다음에 추가할 메뉴의 인덱스를 설정하면서 메뉴페이지를 추가한다.
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);
			//parent.setTranslateX(800);

			KeyValue keyValueStackPaneMenu = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPaneMenu = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPaneMenu);
			
			

			// 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
			//KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			//KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPaneMenu);
			timeline.play();
			
		} catch (IOException ex) {
		}
		
	}

	private void handleBtnCondition(ActionEvent event) {
		try {
			StackPane parent = FXMLLoader.load(ConditionController.class.getResource("condition.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

			// 메인 페이지가 추가된상태에서 현재 리스트의 사이즈는 2 이다 , 이 사이즈를 가지고 다음에 추가할 메뉴의 인덱스를 설정하면서 메뉴페이지를 추가한다.
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);
			//parent.setTranslateX(800);

			KeyValue keyValueStackPaneMenu = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPaneMenu = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPaneMenu);
			
			

			// 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
			//KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			//KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPaneMenu);
			timeline.play();
			
		} catch (IOException ex) {
		}
	}

	private void handleBtnConsume(ActionEvent event) {
		try {
			StackPane parent = FXMLLoader.load(ConsumeController.class.getResource("consume.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

			// 메인 페이지가 추가된상태에서 현재 리스트의 사이즈는 2 이다 , 이 사이즈를 가지고 다음에 추가할 메뉴의 인덱스를 설정하면서 메뉴페이지를 추가한다.
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);
			//parent.setTranslateX(800);

			KeyValue keyValueStackPaneMenu = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPaneMenu = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPaneMenu);
			
			

			// 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
			//KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			//KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPaneMenu);
			timeline.play();
			
		} catch (IOException ex) {
		}
	}

	private void handleBtnLight(ActionEvent event) {
		try {
			StackPane parent = FXMLLoader.load(LightController.class.getResource("light.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

			// 메인 페이지가 추가된상태에서 현재 리스트의 사이즈는 2 이다 , 이 사이즈를 가지고 다음에 추가할 메뉴의 인덱스를 설정하면서 메뉴페이지를 추가한다.
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);
			//parent.setTranslateX(800);

			KeyValue keyValueStackPaneMenu = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPaneMenu = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPaneMenu);
			
			

			// 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
			//KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			//KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPaneMenu);
			timeline.play();
			
		} catch (IOException ex) {
		}
	}

	private void handleBtnNotice(ActionEvent event) {
		try {
			StackPane parent = FXMLLoader.load(NoticeController.class.getResource("notice.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

			// 메인 페이지가 추가된상태에서 현재 리스트의 사이즈는 2 이다 , 이 사이즈를 가지고 다음에 추가할 메뉴의 인덱스를 설정하면서 메뉴페이지를 추가한다.
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서' 왼쪽으로 1초동안 이동하는 애니매이션
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);
			//parent.setTranslateX(800);

			KeyValue keyValueStackPaneMenu = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPaneMenu = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPaneMenu);
			
			

			// 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
			//KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			//KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPaneMenu);
			timeline.play();
			
		} catch (IOException ex) {
		}
	}

	private void handleBtnSchedule(ActionEvent event) {
		try {
			StackPane parent = FXMLLoader.load(ScheduleController.class.getResource("schedule.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

			// 메인 페이지가 추가된상태에서 현재 리스트의 사이즈는 2 이다 , 이 사이즈를 가지고 다음에 추가할 메뉴의 인덱스를 설정하면서 메뉴페이지를 추가한다.
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);
			//parent.setTranslateX(800);

			KeyValue keyValueStackPaneMenu = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPaneMenu = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPaneMenu);
			
			

			// 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
			//KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			//KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPaneMenu);
			timeline.play();
			
		} catch (IOException ex) {
		}
	}

	private void handleBtnSetting(ActionEvent event) {
		try {
			StackPane parent = FXMLLoader.load(SettingController.class.getResource("setting.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

			// 메인 페이지가 추가된상태에서 현재 리스트의 사이즈는 2 이다 , 이 사이즈를 가지고 다음에 추가할 메뉴의 인덱스를 설정하면서 메뉴페이지를 추가한다.
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);
			//parent.setTranslateX(800);

			KeyValue keyValueStackPaneMenu = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPaneMenu = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPaneMenu);
			
			

			// 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
			//KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			//KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPaneMenu);
			timeline.play();
			
		} catch (IOException ex) {
		}
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("메뉴 제거");
	}

	
	
}
