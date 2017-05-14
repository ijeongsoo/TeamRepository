/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.light;

import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.main.MainController;
import com.tomcatisbabycat.homepanel.menu.MenuController;
import com.tomcatisbabycat.homepanel.sampleAppliance.Light;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author kang
 */
public class LightController implements Initializable {

	@FXML
	private StackPane lightStackPane;
	@FXML
	private Button btnControlHome;
	@FXML
	private Button btnControlLock;
	@FXML
	private Button btnControlBack;
	@FXML
	private Button num1Room;
	@FXML
	private Button num2Room;
	@FXML
	private Button bathRoom;
	@FXML
	private Button doorRoom;
	@FXML
	private Button multiRoom;
	@FXML
	private Button kichinRoom;
	@FXML
	private Button livingRoom;
	@FXML
	private Button innerRoom;
	@FXML
	private Button boilRoom;

	private Light light = Light.getInstance();
	@FXML
	private Label lblLight;
	@FXML
	private ImageView imgLight;

	/**
	 * Initializes the controller class.
	 */
	
	private void lightThread(){
		if (!light.isNum2Room()) {
			num2Room.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			num2Room.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isNum1Room()) {
			num1Room.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			num1Room.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isMultiRoom()) {
			multiRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			multiRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isBoilRoom()) {
			boilRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			boilRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isLivingRoom()) {
			livingRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			livingRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isKeachinRoom()) {
			kichinRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			kichinRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isInnerRoom()) {
			innerRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			innerRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isDoorRoom()) {
			doorRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			doorRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isBathRoom()) {
			bathRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			bathRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		lblLight.setText(String.valueOf(countLight()));
		imgLight.setOpacity(((double) countLight()) / 9);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnControlBack.setFocusTraversable(false);
		btnControlHome.setFocusTraversable(false);
		btnControlLock.setFocusTraversable(false);
		num1Room.setFocusTraversable(false);
		num2Room.setFocusTraversable(false);
		bathRoom.setFocusTraversable(false);
		doorRoom.setFocusTraversable(false);
		multiRoom.setFocusTraversable(false);
		kichinRoom.setFocusTraversable(false);
		livingRoom.setFocusTraversable(false);
		innerRoom.setFocusTraversable(false);
		boilRoom.setFocusTraversable(false);
		
		if (!light.isNum2Room()) {
			num2Room.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			num2Room.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isNum1Room()) {
			num1Room.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			num1Room.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isMultiRoom()) {
			multiRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			multiRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isBoilRoom()) {
			boilRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			boilRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isLivingRoom()) {
			livingRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			livingRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isKeachinRoom()) {
			kichinRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			kichinRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isInnerRoom()) {
			innerRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			innerRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isDoorRoom()) {
			doorRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			doorRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		if (!light.isBathRoom()) {
			bathRoom.setStyle("-fx-background-color:#c0c0c0;");
		} else {
			bathRoom.setStyle("-fx-background-color:#FFCC66;");
		}

		lblLight.setText(String.valueOf(countLight()));
		imgLight.setOpacity(((double) countLight()) / 9);

		btnControlLock.setOnAction(event -> {
			handleBtnControlLock(event);
		});
		btnControlHome.setOnAction(event -> {
			handleBtnControlHome(event);
		});
		btnControlBack.setOnAction(event -> {
			handleBtnControlBack(event);
		});

		boilRoom.setOnAction((event) -> {
			if (light.isBoilRoom() == false) {
				light.setBoilRoom(true);
				Platform.runLater(() -> {
					boilRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			} else {
				light.setBoilRoom(false);
				Platform.runLater(() -> {
					boilRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});

		multiRoom.setOnAction((event) -> {
			if (light.isMultiRoom() == false) {
				light.setMultiRoom(true);
				Platform.runLater(() -> {
					multiRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			} else {
				light.setMultiRoom(false);
				Platform.runLater(() -> {
					multiRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});

		innerRoom.setOnAction((event) -> {
			if (light.isInnerRoom() == false) {
				light.setInnerRoom(true);
				Platform.runLater(() -> {
					innerRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			} else {
				light.setInnerRoom(false);
				Platform.runLater(() -> {
					innerRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});

		kichinRoom.setOnAction((event) -> {
			if (light.isKeachinRoom() == false) {
				light.setKeachinRoom(true);
				Platform.runLater(() -> {
					kichinRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			} else {
				light.setKeachinRoom(false);
				Platform.runLater(() -> {
					kichinRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});

		livingRoom.setOnAction((event) -> {
			if (light.isLivingRoom() == false) {
				light.setLivingRoom(true);
				Platform.runLater(() -> {
					livingRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			} else {
				light.setLivingRoom(false);
				Platform.runLater(() -> {
					livingRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});

		bathRoom.setOnAction((event) -> {
			if (light.isBathRoom() == false) {
				light.setBathRoom(true);
				Platform.runLater(() -> {
					bathRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			} else {
				light.setBathRoom(false);
				Platform.runLater(() -> {
					bathRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});

		doorRoom.setOnAction((event) -> {
			if (light.isDoorRoom() == false) {
				light.setDoorRoom(true);
				Platform.runLater(() -> {
					doorRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			} else {
				light.setDoorRoom(false);
				Platform.runLater(() -> {
					doorRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});

		num1Room.setOnAction((event) -> {
			if (light.isNum1Room() == false) {
				light.setNum1Room(true);
				Platform.runLater(() -> {
					num1Room.setStyle("-fx-background-color:#FFCC66;");
				});
			} else {
				light.setNum1Room(false);
				Platform.runLater(() -> {
					num1Room.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});

		num2Room.setOnAction((event) -> {
			if (light.isNum2Room() == false) {
				light.setNum2Room(true);
				Platform.runLater(() -> {
					num2Room.setStyle("-fx-background-color:#FFCC66;");
				});
			} else {
				light.setNum2Room(false);
				Platform.runLater(() -> {
					num2Room.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});

		lblLight.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				imgLight.setOpacity(((double) Integer.parseInt(newValue)) / 9);
			}
		});
		
		Thread thread = new Thread(){
			@Override
			public void run() {
				while(lightStackPane.isVisible()){
					Platform.runLater(() -> {
						lightThread();
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
					}
				}
				System.out.println("light쓰레드 죽음");
			}
			
		};
		thread.setDaemon(true);
		thread.start();

	}

	private int countLight() {
		int lightNum = 0;
		if (light.isBathRoom()) {
			lightNum++;
		}
		if (light.isBoilRoom()) {
			lightNum++;
		}
		if (light.isDoorRoom()) {
			lightNum++;
		}
		if (light.isInnerRoom()) {
			lightNum++;
		}
		if (light.isKeachinRoom()) {
			lightNum++;
		}
		if (light.isLivingRoom()) {
			lightNum++;
		}
		if (light.isMultiRoom()) {
			lightNum++;
		}
		if (light.isNum1Room()) {
			lightNum++;
		}
		if (light.isNum2Room()) {
			lightNum++;
		}

		return lightNum;
	}

	private void handleBtnControlBack(ActionEvent event) {

		try {
			StackPane parent = FXMLLoader.load(MenuController.class.getResource("menu.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);
			//parent.setTranslateX(800);

			KeyValue keyValueStackPane = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPane = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				lightStackPane.setVisible(false);
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPane);

			// 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
			//KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			//KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPane);
			timeline.play();
		} catch (IOException ex) {
		}

		// 메인 페이지가 추가된상태에서 현재 리스트의 사이즈는 2 이다 , 이 사이즈를 가지고 다음에 추가할 메뉴의 인덱스를 설정하면서 메뉴페이지를 추가한다.
	}

	private void handleBtnControlLock(ActionEvent event) {
		lightStackPane.setVisible(false);
		LockController.lockRootPane.getChildren().remove(lightStackPane);

	}

	private void handleBtnControlHome(ActionEvent event) {

		try {
			StackPane parent = FXMLLoader.load(MainController.class.getResource("main.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			// 추가를한 이순간에는 리스트의 사이즈가 3이다. 아래코드에서 메인페이지를 제거하면 사이즈가 2로 바뀐다
			// 현재상태에서 메뉴의 인덱스는 2

			// 수업시간에 했던 화면 오른쪽에서 왼쪽으로 1초동안 이동하는 애니매이션
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);
			//parent.setTranslateX(800);

			KeyValue keyValueStackPane = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPane = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				lightStackPane.setVisible(false);
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPane);

			// 삭제될 메인페이지의 이벤트를 처리하는 부분, 차후에 애니메이션 설정에따라 사용할지도?!
			//KeyValue keyValueStackPaneMain = new KeyValue(stackPaneMain.translateXProperty(), -800);
			//KeyFrame keyFrameStackPaneMain = new KeyFrame(Duration.seconds(1), keyValueStackPaneMain);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPane);
			timeline.play();
		} catch (IOException ex) {
		}

	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("라이트 제거");
	}
	

}
