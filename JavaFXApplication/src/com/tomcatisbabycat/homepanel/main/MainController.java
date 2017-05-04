/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main;

import com.tomcatisbabycat.homepanel.main.statusthread.WeatherThread;
import com.tomcatisbabycat.homepanel.main.statusthread.TemperatureThread;
import com.tomcatisbabycat.homepanel.main.statusthread.MoistureThread;
import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.main.statusthread.ClockThread;
import com.tomcatisbabycat.homepanel.main.statusthread.DustThread;
import com.tomcatisbabycat.homepanel.main.statusthread.RionThread;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import com.tomcatisbabycat.homepanel.menu.*;
import com.tomcatisbabycat.homepanel.resources.images.ImageResourceFinder;
import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ijeongsu
 */
public class MainController implements Initializable {

	@FXML
	private Button mainBtnLock;
	@FXML
	private Button mainBtnMenu;
	@FXML
	private ImageView mainImage;
	@FXML
	private BorderPane mainImagePane;
	@FXML
	private StackPane stackPaneMain;

	@FXML
	private ImageView mainWeatherImage;
	@FXML
	private ImageView mainWeatherImageBack;

	private SampleStatus samplestatus = SampleStatus.getInstance();

	@FXML
	private ImageView mainWeatherImageBack2;
	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ImageView imgMainTemperature;
	@FXML
	private Label lblMainTemperature;
	@FXML
	private ImageView imgMainMoisture;
	@FXML
	private Label lblMainMoisture;
	@FXML
	private ImageView imgMainDust;
	@FXML
	private Label lblMainDust;
	@FXML
	private Line houreHand;
	@FXML
	private Line minuateHand;
	@FXML
	private Line secondHand;

	private ThreadGroup mainThreadGroup;
	@FXML
	private Label lblMainClock;
	@FXML
	private Label lblMainYear;
	@FXML
	private Label lblMainMonth;
	@FXML
	private Label lblMainDay;
	@FXML
	private Label lblMainDate;
	@FXML
	private ImageView imgRion;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		PauseTransition delay = new PauseTransition(Duration.seconds(10));
		delay.setOnFinished( event -> handleBtnLock(event));
		delay.play();
		/*Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), 100)),
                new KeyFrame(Duration.seconds(10), new KeyValue(rotation.angleProperty(), 360))
		 );
		 timeline.play();*/

		//statusDetect();
		MainImageSet mainImageSet = new MainImageSet(mainImage);
		mainImageSet.mainImageSet();

		mainBtnMenu.setOnAction((event) -> {
			handleBtnMenu(event);
		});

		mainBtnLock.setOnAction((event) -> {
			handleBtnLock(event);
		});
		
		stackPaneMain.setOnMouseClicked((event) -> {
			delay.stop();
			delay.play();
		});

		mainImage.setOnMouseClicked((event) -> {
			try {
				
				Parent parent = FXMLLoader.load(getClass().getResource("mainImagePopup.fxml"));
				stackPaneMain.getChildren().add( parent);
				ImageView popupImage = (ImageView) parent.lookup("#imgPopup");
				popupImage.setImage(new Image(ImageResourceFinder.class.getResource(ImageResourceFinder.getImageFileName()).toString()));
				Button btnExit = (Button) parent.lookup("#btnExit");
				Rectangle recPopupBackground = (Rectangle) parent.lookup("#recPopupBackground");

				btnExit.setOnAction(e -> {
					Timeline timeline = new Timeline();
					KeyValue keyvalue = new KeyValue(popupImage.opacityProperty(), 0);
					KeyFrame keyFrame = new KeyFrame(Duration.millis(200), (e1) -> {
						Timeline timeline2 = new Timeline();
						KeyValue keyvalue2 = new KeyValue(recPopupBackground.opacityProperty(), 0);
						KeyFrame keyFrame2 = new KeyFrame(Duration.millis(200),(e2) -> {
							
							stackPaneMain.getChildren().remove(1);
						}, keyvalue2);
						timeline2.getKeyFrames().add(keyFrame2);
						timeline2.play();
					}, keyvalue);

					timeline.getKeyFrames().add(keyFrame);
					timeline.play();
				});


			} catch (IOException ie) {
			}
		});

		mainThreadGroup = new ThreadGroup("mainThreadGroup");

		WeatherThread weatherThread = new WeatherThread(mainThreadGroup, "weatherThread", mainWeatherImage, mainWeatherImageBack, mainWeatherImageBack2);
		weatherThread.setDaemon(true);
		weatherThread.start();

		TemperatureThread temperatureThread = new TemperatureThread(mainThreadGroup, "temperatureThread", imgMainTemperature, lblMainTemperature);
		temperatureThread.setDaemon(true);
		temperatureThread.start();

		MoistureThread moistureThread = new MoistureThread(mainThreadGroup, "moistureThread", imgMainMoisture, lblMainMoisture);
		moistureThread.setDaemon(true);
		moistureThread.start();

		DustThread dustThread = new DustThread(mainThreadGroup, "dustThread", imgMainDust, lblMainDust);
		dustThread.setDaemon(true);
		dustThread.start();

		ClockThread clockThread = new ClockThread(mainThreadGroup, "clockThread", houreHand, minuateHand, secondHand, lblMainClock, lblMainYear, lblMainMonth, lblMainDate, lblMainDay);
		clockThread.setDaemon(true);
		clockThread.start();
		
		RionThread rionThread= new RionThread(mainThreadGroup, "rionThread", imgRion);
		rionThread.setDaemon(true);
		rionThread.start();

	}

	private void handleBtnMenu(ActionEvent e) { // menu 화면 넘어가는 애니메이션 처리
		try {
			Parent parent = FXMLLoader.load(MenuController.class.getResource("menu.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

			// 메인 페이지가 추가된상태에서 현재 리스트의 사이즈는 2 이다 , 이 사이즈를 가지고 다음에 추가할 메뉴의 인덱스를 설정하면서 메뉴페이지를 추가한다.
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			parent.setTranslateX(800);

			KeyValue keyValueStackPaneMenu = new KeyValue(parent.translateXProperty(), 0);
			KeyFrame keyFrameStackPaneMenu = new KeyFrame(Duration.seconds(1), keyValueStackPaneMenu);

			// 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
			//KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			//KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPaneMenu);
			timeline.play();

			timeline.statusProperty().addListener((observable, oldValue, newValue) -> {
				//System.out.println(newValue);
				if (newValue.toString().equals("STOPPED")) {
					// 애니메이션이 끝난순간, 인덱스번호를 사용해 메인페이지를 제거한다
					mainThreadGroup.interrupt();
					LockController.lockRootPane.getChildren().remove(1);
					// 메인페이지를 삭제하는 순간, 리스트의 사이즈는 2로 바뀌고 메뉴페이지의 인덱스는 1로 바뀐다
					//System.out.println("메뉴의 인덱스" + LockController.lockRootPane.getChildren().indexOf(parent)); // 인덱스 확인 출력문
				}
			});
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	private void handleBtnLock(ActionEvent event){
		// main에서의 Lock 이벤트처리 일단 안함
		LockController.lockRootPane.getChildren().remove(stackPaneMain);
		System.gc();
	}

}
