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
	@Override
	public void initialize(URL url, ResourceBundle rb) {
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
		imgLight.setOpacity(((double)countLight())/9);
		
		
		btnControlLock.setOnAction(event -> {
			handleBtnControlLock(event);
		});
		btnControlHome.setOnAction(event -> {
			handleBtnControlHome(event);
		});
		btnControlBack.setOnAction(event->{
			handleBtnControlBack(event);
		});
		
		boilRoom.setOnAction((event) -> {
			if(light.isBoilRoom()==false){
				light.setBoilRoom(true);
				Platform.runLater(() -> {
					boilRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			}else{
				light.setBoilRoom(false);
				Platform.runLater(() -> {
					boilRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});
		
		multiRoom.setOnAction((event) -> {
			if(light.isMultiRoom()==false){
				light.setMultiRoom(true);
				Platform.runLater(() -> {
					multiRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			}else{
				light.setMultiRoom(false);
				Platform.runLater(() -> {
					multiRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});
		
		innerRoom.setOnAction((event) -> {
			if(light.isInnerRoom()==false){
				light.setInnerRoom(true);
				Platform.runLater(() -> {
					innerRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			}else{
				light.setInnerRoom(false);
				Platform.runLater(() -> {
					innerRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});
		
		kichinRoom.setOnAction((event) -> {
			if(light.isKeachinRoom()==false){
				light.setKeachinRoom(true);
				Platform.runLater(() -> {
					kichinRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			}else{
				light.setKeachinRoom(false);
				Platform.runLater(() -> {
					kichinRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});
		
		livingRoom.setOnAction((event) -> {
			if(light.isLivingRoom()==false){
				light.setLivingRoom(true);
				Platform.runLater(() -> {
					livingRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			}else{
				light.setLivingRoom(false);
				Platform.runLater(() -> {
					livingRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});
		
		bathRoom.setOnAction((event) -> {
			if(light.isBathRoom()==false){
				light.setBathRoom(true);
				Platform.runLater(() -> {
					bathRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			}else{
				light.setBathRoom(false);
				Platform.runLater(() -> {
					bathRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});
		
		doorRoom.setOnAction((event) -> {
			if(light.isDoorRoom()==false){
				light.setDoorRoom(true);
				Platform.runLater(() -> {
					doorRoom.setStyle("-fx-background-color:#FFCC66;");
				});
			}else{
				light.setDoorRoom(false);
				Platform.runLater(() -> {
					doorRoom.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});
		
		num1Room.setOnAction((event) -> {
			if(light.isNum1Room()==false){
				light.setNum1Room(true);
				Platform.runLater(() -> {
					num1Room.setStyle("-fx-background-color:#FFCC66;");
				});
			}else{
				light.setNum1Room(false);
				Platform.runLater(() -> {
					num1Room.setStyle("-fx-background-color:#c0c0c0;");
				});
			}
			lblLight.setText(String.valueOf(countLight()));
		});
		
		num2Room.setOnAction((event) -> {
			if(light.isNum2Room()==false){
				light.setNum2Room(true);
				Platform.runLater(() -> {
					num2Room.setStyle("-fx-background-color:#FFCC66;");
				});
			}else{
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
				imgLight.setOpacity(((double)Integer.parseInt(newValue))/9);
			}
		});
		
		
		
		
		
		
	}
	
	private int countLight(){
			int lightNum=0;
			if(light.isBathRoom())
				lightNum++;
			if(light.isBoilRoom())
				lightNum++;
			if(light.isDoorRoom())
				lightNum++;
			if(light.isInnerRoom())
				lightNum++;
			if(light.isKeachinRoom())
				lightNum++;
			if(light.isLivingRoom())
				lightNum++;
			if(light.isMultiRoom())
				lightNum++;
			if(light.isNum1Room())
				lightNum++;
			if(light.isNum2Room())
				lightNum++;
			
			return lightNum;
	}

	
	private void handleBtnControlBack(ActionEvent event){
		lightStackPane.setTranslateX(0);
		
		KeyValue keyValueStackPaneLight = new KeyValue(lightStackPane.translateXProperty(), 800);
		KeyFrame keyFrameStackPaneLight = new KeyFrame(Duration.seconds(1), 
			  e->{
				LockController.lockRootPane.getChildren().remove(2);
		}, keyValueStackPaneLight);
		
		Timeline timeline = new Timeline(keyFrameStackPaneLight);
		timeline.play();
		
		try {
			LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MenuController.class.getResource("menu.fxml")));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	

	private void handleBtnControlLock(ActionEvent event) {
		//StackPane rootPane = (StackPane) lightStackPane.getScene().getRoot(); // 컨트롤을 통해서 현재 Scene을 얻고 root의 객체를 얻는다.

		lightStackPane.setTranslateX(0);
		KeyValue keyValueStackPaneLight = new KeyValue(lightStackPane.translateXProperty(), 800);
		KeyFrame keyFrameStackPaneLight = new KeyFrame(Duration.seconds(1),
			  e -> {
				  LockController.lockRootPane.getChildren().remove(lightStackPane);
			  }, keyValueStackPaneLight);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrameStackPaneLight);

		timeline.play();
	}

	private void handleBtnControlHome(ActionEvent event) {

		KeyValue keyValueStackPaneLight = new KeyValue(lightStackPane.translateXProperty(), 800);
		KeyFrame keyFrameStackPaneLight = new KeyFrame(Duration.seconds(1),
			  e -> {
				  LockController.lockRootPane.getChildren().remove(2, LockController.lockRootPane.getChildren().size());
			  }, keyValueStackPaneLight);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrameStackPaneLight);

		timeline.play();
		try {
			LockController.lockRootPane.getChildren().add(1, FXMLLoader.load(MainController.class.getResource("main.fxml")));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}	
	
}
