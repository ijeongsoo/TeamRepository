/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main;

import com.tomcatisbabycat.homepanel.main.statusthread.WeatherThread;
import com.tomcatisbabycat.homepanel.lock.LockController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import com.tomcatisbabycat.homepanel.menu.*;
import com.tomcatisbabycat.homepanel.notice.NoticeList;
import com.tomcatisbabycat.homepanel.resources.icons.IconSelector;
import com.tomcatisbabycat.homepanel.resources.images.ImageResourceFinder;
import com.tomcatisbabycat.homepanel.samplestatus.SampleStatus;
import java.io.IOException;
import java.util.Calendar;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
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

	private static ThreadGroup mainThreadGroup = new ThreadGroup("mainThreadGroup");
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

	Rotate hourRotation;
	Rotate minuateRotation;
	Rotate secondRotation;
	Calendar calendar;

	private NoticeList noticeList = NoticeList.getInstance();

	Image forestImage = new Image(IconSelector.class.getResource("forest.png").toString());
	Image hillsImage = new Image(IconSelector.class.getResource("hills.png").toString());
	Image fieldsImage = new Image(IconSelector.class.getResource("fields.png").toString());
	Image capeImage = new Image(IconSelector.class.getResource("cape.png").toString());
	Image cactusImage = new Image(IconSelector.class.getResource("cactus.png").toString());
	Image dropsImage = new Image(IconSelector.class.getResource("drops.png").toString());
	Image coldImage = new Image(IconSelector.class.getResource("temperature-2.png").toString());
	Image sosoImage = new Image(IconSelector.class.getResource("temperature-3.png").toString());
	Image hotImage = new Image(IconSelector.class.getResource("temperature-4.png").toString());
	int lionImagenum = 1;
	@FXML
	private Rectangle mainBack;
	@FXML
	private Label lblNotice1;
	@FXML
	private Label lblNotice2;

	/**
	 * Initializes the controller class.
	 */
	private void animation() {

		calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		String hourstr;
		if (hour < 10) {
			hourstr = "0" + hour;
		} else {
			hourstr = String.valueOf(hour);
		}

		int minuate = calendar.get(Calendar.MINUTE);
		String minuatestr;
		if (minuate < 10) {
			minuatestr = "0" + minuate;
		} else {
			minuatestr = String.valueOf(minuate);
		}

		int second = calendar.get(Calendar.SECOND);
		String secondstr;
		if (second < 10) {
			secondstr = "0" + second;
		} else {
			secondstr = String.valueOf(second);
		}

		String ampm;
		if (calendar.get(Calendar.AM_PM) == 1) {
			ampm = "PM";
		} else {
			ampm = "AM";
		}

		int year = calendar.get(Calendar.YEAR);
		String yearstr = String.valueOf(year);

		int month = calendar.get(Calendar.MONTH) + 1;
		String monthstr;
		if (month < 10) {
			monthstr = "0" + month;
		} else {
			monthstr = String.valueOf(month);
		}

		int day = calendar.get(Calendar.DAY_OF_WEEK);

		String daystr = null;
		switch (day) {
			case 1:
				daystr = "Sun";
				break;
			case 2:
				daystr = "Mon";
				break;
			case 3:
				daystr = "Tue";
				break;
			case 4:
				daystr = "Wed";
				break;
			case 5:
				daystr = "Thu";
				break;
			case 6:
				daystr = "Fri";
				break;
			case 7:
				daystr = "Sat";
				break;
		}
		String daystrTemp = daystr;

		int date = calendar.get(Calendar.DAY_OF_MONTH);
		String datestr;
		if (date < 10) {
			datestr = "0" + date;
		} else {
			datestr = String.valueOf(date);
		}

		//시간*30+분
		secondRotation.setAngle(second * 6);
		minuateRotation.setAngle(minuate * 6 + second * 0.1);
		hourRotation.setAngle(hour * 30 + minuate * 0.5);

		lblMainClock.setText(ampm + " " + hourstr + ":" + minuatestr + ":" + secondstr);
		lblMainYear.setText(yearstr);
		lblMainMonth.setText(monthstr);
		lblMainDate.setText(datestr);
		if (day == 1 || day == 7) {
			lblMainDay.setTextFill(Color.RED);
		} else {
			lblMainDay.setTextFill(Color.rgb(97, 121, 137));

		}
		lblMainDay.setText(daystrTemp);

		lblMainDust.setText(samplestatus.getDust() + "㎍/㎥");

		if (samplestatus.getDust() >= 0 && samplestatus.getDust() <= 30) {

			imgMainDust.setImage(forestImage);

		} else if (samplestatus.getDust() > 30 && samplestatus.getDust() <= 80) {

			imgMainDust.setImage(hillsImage);

		} else if (samplestatus.getDust() > 80 && samplestatus.getDust() <= 150) {

			imgMainDust.setImage(fieldsImage);

		} else {
			imgMainDust.setImage(capeImage);
		}

		lblMainMoisture.setText(samplestatus.getMoisture() + "%");

		if (samplestatus.getMoisture() < 50.0) {

			imgMainMoisture.setImage(cactusImage);

		} else {

			imgMainMoisture.setImage(dropsImage);

		}
		lblMainTemperature.setText(samplestatus.getTemperature() + "°");

		if (samplestatus.getTemperature() < 20.0) {
			imgMainTemperature.setImage(coldImage);
		} else if (samplestatus.getTemperature() >= 20.0 && samplestatus.getTemperature() < 30.0) {
			imgMainTemperature.setImage(sosoImage);
		} else {
			imgMainTemperature.setImage(hotImage);
		}

		String todayDate = yearstr + "-" + monthstr + "-" + datestr;
		int cnt = 0;
		for (int i = 0; i < noticeList.getMemoList().size(); i++) {
			if (noticeList.getMemoList().get(i).getDate().equals(todayDate)) {
				cnt += 1;
				if (cnt == 1) {
					lblNotice1.setText(noticeList.getMemoList().get(i).getContents());
				}
				if (cnt == 2) {
					lblNotice2.setText(noticeList.getMemoList().get(i).getContents());
				}
			}
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.gc();
		mainBtnLock.setFocusTraversable(false);
		mainBtnMenu.setFocusTraversable(false);

		hourRotation = new Rotate();
		hourRotation.pivotXProperty().bind(houreHand.startXProperty());
		hourRotation.pivotYProperty().bind(houreHand.startYProperty());
		houreHand.getTransforms().add(hourRotation);

		minuateRotation = new Rotate();
		minuateRotation.pivotXProperty().bind(minuateHand.startXProperty());
		minuateRotation.pivotYProperty().bind(minuateHand.startYProperty());
		minuateHand.getTransforms().add(minuateRotation);

		secondRotation = new Rotate();
		secondRotation.pivotXProperty().bind(secondHand.startXProperty());
		secondRotation.pivotYProperty().bind(secondHand.startYProperty());
		secondHand.getTransforms().add(secondRotation);
		animation();

		MainImageSet mainImageSet = new MainImageSet(mainImage);
		mainImageSet.mainImageSet();

		mainBtnMenu.setFocusTraversable(false);
		mainBtnLock.setFocusTraversable(false);

		mainBtnMenu.setOnAction((event) -> {
			handleBtnMenu(event);

		});
		mainBtnLock.setOnAction((event) -> {
			handleBtnLock(event);

		});

		mainImage.setOnMouseClicked((event) -> {
			try {

				Parent parent = FXMLLoader.load(getClass().getResource("mainImagePopup.fxml"));
				stackPaneMain.getChildren().add(parent);
				ImageView popupImage = (ImageView) parent.lookup("#imgPopup");
				popupImage.setImage(new Image(ImageResourceFinder.class.getResource(ImageResourceFinder.getImageFileName()).toString()));
				Button btnExit = (Button) parent.lookup("#btnExit");
				Rectangle recPopupBackground = (Rectangle) parent.lookup("#recPopupBackground");
				
				Timeline timeline1 = new Timeline();
				recPopupBackground.setOpacity(0);
				popupImage.setOpacity(0);
				btnExit.setOpacity(0);
				KeyValue keyvalue1 = new KeyValue(recPopupBackground.opacityProperty(), 0.7);
				KeyFrame keyFrame1 = new KeyFrame(Duration.millis(200), (event1) -> {
					Timeline timeline2 = new Timeline();
					KeyValue keyvalue2 = new KeyValue(popupImage.opacityProperty(), 1);
					KeyValue keyvalue21 = new KeyValue(btnExit.opacityProperty(), 1);
					KeyFrame keyFrame2 = new KeyFrame(Duration.millis(200), keyvalue2, keyvalue21);
					timeline2.getKeyFrames().add(keyFrame2);
					timeline2.play();
				}, keyvalue1);

				timeline1.getKeyFrames().add(keyFrame1);
				timeline1.play();
				
				btnExit.setOnAction(e -> {
					Timeline timeline = new Timeline();
					KeyValue kv = new KeyValue(btnExit.opacityProperty(), 0);
					KeyValue keyvalue = new KeyValue(popupImage.opacityProperty(), 0);
					KeyFrame keyFrame = new KeyFrame(Duration.millis(200), (e1) -> {
						Timeline timeline2 = new Timeline();
						KeyValue keyvalue2 = new KeyValue(recPopupBackground.opacityProperty(), 0);
						KeyFrame keyFrame2 = new KeyFrame(Duration.millis(200), (e2) -> {
							stackPaneMain.getChildren().remove(2);

						}, keyvalue2);
						timeline2.getKeyFrames().add(keyFrame2);
						timeline2.play();
					}, keyvalue, kv);

					timeline.getKeyFrames().add(keyFrame);
					timeline.play();
				});

			} catch (IOException ie) {
			}
		});

		WeatherThread weatherThread = new WeatherThread(mainThreadGroup, "weatherThread", mainWeatherImage, mainWeatherImageBack, mainWeatherImageBack2);
		weatherThread.setDaemon(true);
		weatherThread.start();

		Thread thread = new Thread(mainThreadGroup, "ClockAndEnvThread") {
			@Override
			public void run() {
				while (true) {
					if (Thread.interrupted()) {
						break;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						break;
					}
					Platform.runLater(() -> {
						animation();
					});
				}

			}
		};

		Thread thread2 = new Thread(mainThreadGroup, "lionAnimation") {
			@Override
			public void run() {
				while (true) {
					if (Thread.interrupted()) {
						break;
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException ex) {
						break;
					}
					Platform.runLater(() -> {
						lionAnimation();
					});
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
		thread2.setDaemon(true);
		thread2.start();
//		ClockAndEnvThread clockThread = new ClockAndEnvThread(mainThreadGroup, "clockThread", houreHand, minuateHand, secondHand, lblMainClock, lblMainYear, lblMainMonth, lblMainDate, lblMainDay,imgMainDust, lblMainDust, imgMainMoisture, lblMainMoisture, imgMainTemperature, lblMainTemperature);
//		clockThread.setDaemon(true);
//		clockThread.start();
//		RionThread rionThread = new RionThread(mainThreadGroup, "rionThread", imgRion);
//		rionThread.setDaemon(true);
//		rionThread.start();

	}

	private void handleBtnMenu(ActionEvent e) { // menu 화면 넘어가는 애니메이션 처리
		try {
			StackPane parent = FXMLLoader.load(MenuController.class.getResource("menu.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근

			// 메인 페이지가 추가된상태에서 현재 리스트의 사이즈는 2 이다 , 이 사이즈를 가지고 다음에 추가할 메뉴의 인덱스를 설정하면서 메뉴페이지를 추가한다.
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);

			KeyValue keyValueStackPaneMenu = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPaneMenu = new KeyFrame(Duration.millis(500), (event) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				mainThreadInterrupt();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPaneMenu);

			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPaneMenu);
			timeline.play();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void handleBtnLock(ActionEvent event) {
		// main에서의 Lock 이벤트처리 일단 안함
		mainThreadInterrupt();
		LockController.lockRootPane.getChildren().remove(stackPaneMain);
	}

	public static void mainThreadInterrupt() {
		mainThreadGroup.interrupt();
//		animationTL.stop();
//		lionAnimationTL.stop();

	}

	private void lionAnimation() {
		imgRion.setImage(new Image(ImageResourceFinder.class.getResource("rion-" + lionImagenum + ".png").toString()));
		lionImagenum++;
		if (lionImagenum == 16) {
			lionImagenum = 1;
		}
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("메인제거");
	}

}
