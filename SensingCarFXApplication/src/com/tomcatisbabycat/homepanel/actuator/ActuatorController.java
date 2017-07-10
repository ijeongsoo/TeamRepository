/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.actuator;

import com.tomcatisbabycat.homepanel.camera.ViewerCanvas;
import com.tomcatisbabycat.homepanel.lock.LockController;
import com.tomcatisbabycat.homepanel.main.MainController;
import com.tomcatisbabycat.homepanel.menu.MenuController;
import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.GaugeBuilder;
import eu.hansolo.medusa.Section;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

/**
 *
 * @author kang
 */
public class ActuatorController implements Initializable{
	
	private static final String IPADDRESS = "coap://192.168.3.40/";
	private static final String IP = "192.168.3.40";
	
	@FXML
	private StackPane actuatorStackPane;
	@FXML
	private Rectangle menuBack;
	@FXML
	private Button btnControlHome;
	@FXML
	private Button btnControlLock;
	@FXML
	private Button btnControlBack;
	
	
	/////////////// CoAP 
	private CoapClient coapClient;
	Buzzer buzzer = Buzzer.getInstance();
	LaserEmitter laserEmitter = LaserEmitter.getInstance();
	RGBLed rgb = RGBLed.getInstance();
	FrontTire frontTire = FrontTire.getInstance();
	BackTire backTire = BackTire.getInstance();
	Ultrasonic ultrasonic = Ultrasonic.getInstance();
	Camera camera = Camera.getInstance();
	LCD lcd = LCD.getInstance();
	
	
	@FXML
	private Button btnLeft;
	@FXML
	private Button btnFront;
	@FXML
	private Button btnRight;
	
	
	@FXML
	private Button btnAccel;
	@FXML
	private Button btnForward;
	@FXML
	private Button btnBackward;
	@FXML
	private Button btnUltrasonicRight;
	@FXML
	private Button btnUltrasonicFront;
	@FXML
	private Button btnUltrasonicLeft;
	@FXML
	private Button btnUpDown75;
	@FXML
	private Button btnUpDown45;
	@FXML
	private Button btnUpDown10;
	@FXML
	private Button btnLeftRight45;
	@FXML
	private Button btnLeftRight90;
	@FXML
	private Button btnLeftRight135;
	@FXML
	private Button btnLeftRight170;
	@FXML
	private Button btnLeftRight10;
	@FXML
	private StackPane cameraPane;
	
	//Gauge
	@FXML
	private StackPane stackSpeed;
	
	private Gauge gauge;
	@FXML
	private Button btnBrake;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnControlLock.setOnAction(event -> {
			handleBtnControlLock(event);
			event.consume();
		});
		btnControlHome.setOnAction(event -> {
			handleBtnControlHome(event);
			event.consume();
		});
		btnControlBack.setOnAction(event -> {
			handleBtnControlBack(event);
			event.consume();
		});
		btnFront.setOnAction(event -> {
			int angle = 90;
			handleBtnControlFrontTire(event, angle);
			event.consume();
		});
		btnLeft.setOnAction(event -> {
			int angle = 60;
			handleBtnControlFrontTire(event, angle);
			event.consume();
		});
		btnRight.setOnAction(event -> {
			int angle = 130;
			handleBtnControlFrontTire(event, angle);
			event.consume();
		});
		btnForward.setOnAction(event -> {
			String direction = "forward";
			handleBtnControlBackTireDirection(event, direction);
			event.consume();
		});
		btnBackward.setOnAction(event -> {
			String direction = "backward";
			handleBtnControlBackTireDirection(event, direction);
			event.consume();
		});
		btnUltrasonicFront.setOnAction(event -> {
			int angle = 90;
			handleBtnControlUltrasonic(event, angle);
			event.consume();
		});
		btnUltrasonicLeft.setOnAction(event -> {
			int angle = 150;
			handleBtnControlUltrasonic(event, angle);
			event.consume();
		});
		btnUltrasonicRight.setOnAction(event -> {
			int angle = 30;
			handleBtnControlUltrasonic(event, angle);
			event.consume();
		});
		btnUpDown10.setOnAction(event ->{
			int upDownAngle = 10;
			handleBtnControlCameraUpDown(event, upDownAngle);
			event.consume();
		});
		btnUpDown45.setOnAction(event ->{
			int upDownAngle = 45;
			handleBtnControlCameraUpDown(event, upDownAngle);
			event.consume();
		});
		btnUpDown75.setOnAction(event ->{
			int upDownAngle = 75;
			handleBtnControlCameraUpDown(event, upDownAngle);
			event.consume();
		});
		btnLeftRight10.setOnAction(event -> {
			int leftRightAngel = 170;
			handleBtnControlCameraLeftRight(event, leftRightAngel);
			event.consume();
		});
		btnLeftRight45.setOnAction(event -> {
			int leftRightAngel = 135;
			handleBtnControlCameraLeftRight(event, leftRightAngel);
			event.consume();
		});
		btnLeftRight90.setOnAction(event -> {
			int leftRightAngel = 90;
			handleBtnControlCameraLeftRight(event, leftRightAngel);
			event.consume();
		});
		btnLeftRight135.setOnAction(event -> {
			int leftRightAngel = 45;
			handleBtnControlCameraLeftRight(event, leftRightAngel);
			event.consume();
		});
		btnLeftRight170.setOnAction(event -> {
			int leftRightAngel = 10;
			handleBtnControlCameraLeftRight(event, leftRightAngel);
			event.consume();
		});
		btnAccel.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
			handleBtnControlBackTireSpeed("accel");
			event.consume();
		});
		btnBrake.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
			handleBtnControlBackTireSpeed("break");
			event.consume();
		});

		
		// Gauge
		gauge = GaugeBuilder.create()
			  .minValue(2200)
			  .threshold(50)
                    .maxValue(4095)                                                         
                    .startAngle(320)                                                        
                    .angleRange(270)
			  .subTitle(IP)
			  .sectionsVisible(true)
			  .sections(new Section(2200, 2800, Color.rgb(0, 0, 255)),
				    new Section(2800, 3600, Color.rgb(0, 255, 0)),
				    new Section(3600, 4500, Color.rgb(255, 0, 0)))
			  .build();
		gauge.setValue(backTire.getRealSpeed());
		stackSpeed.getChildren().add(gauge);
		
		post(rgb.getRgbChange(), rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getResource());
		
		
		
		
		// Gauge
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command","status");
		String json = jsonObject.toString();
		CoapClient coapClient = new CoapClient();
		coapClient.setURI("coap://192.168.3.40/lcd");
		CoapResponse coapResponse = coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
		
		json = coapResponse.getResponseText();
		jsonObject = new JSONObject(json);
		
		//txtLine0.setText(jsonObject.getString("line0"));
		//txtLine1.setText(jsonObject.getString("line1"));
		
		ViewerCanvas viewer = new ViewerCanvas(300, 200);
		try{
			viewer.setCurrentURL(new URL("http://" + IP + ":50001/?action=stream"));
		}catch(Exception e){}
		
		cameraPane.getChildren().add(viewer);
//		
//		txtLine0.setText("test Line0");
//		txtLine1.setText("test LIne1");
		
	}
	
	/////////////// COAP
	public String post(String command, String status, String resource){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("status", status);
		
		String reqJson = jsonObject.toString();
		coapClient = new CoapClient();
		coapClient.setURI(IPADDRESS + resource);
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		
		if(coapResponse == null){
			System.out.println("Fail Response");
			System.out.println(reqJson);
			return post(command, status, resource);
		}else{
			System.out.println("Success Response - " + resource);
			return coapResponse.getResponseText();
		}
		
	}
	public String post(String command, int red, int green, int blue, String resource){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("red", String.valueOf(red));
		jsonObject.put("green", String.valueOf(green));
		jsonObject.put("blue", String.valueOf(blue));
		
		String reqJson = jsonObject.toString();
		coapClient = new CoapClient();
		coapClient.setURI(IPADDRESS + resource);
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		
		if(coapResponse == null){
			System.out.println("Fail Response");
			System.out.println(reqJson);
			return post(command, red, green, blue, resource);
		}else{
			System.out.println("Success Response - " + resource);
			return coapResponse.getResponseText();
		}
	}
	public String post(String command, int angle, String resource){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("angle", String.valueOf(angle));
		
		String reqJson = jsonObject.toString();
		coapClient = new CoapClient();
		coapClient.setURI(IPADDRESS + resource);
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		
		if(coapResponse == null){
			System.out.println("Fail Response");
			System.out.println(reqJson);
			return post(command, angle, resource);
		}else{
			System.out.println("Success Response - " + resource);
			return coapResponse.getResponseText();
		}
	}
	public String post(String command, String direction, int speed, String resource){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("direction", direction);
		jsonObject.put("speed", String.valueOf(speed));
		
		String reqJson = jsonObject.toString();
		coapClient = new CoapClient();
		coapClient.setURI(IPADDRESS + resource);
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		
		if(coapResponse == null){
			System.out.println("Fail Response");
			System.out.println(reqJson);
			return post(command, direction, speed, resource);
		}else{
			System.out.println("Success Response - " + resource);
			return coapResponse.getResponseText();
		}
	}
	public String post(String command, int upDownAngle, int leftRightAngle, String resource){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("leftright", String.valueOf(leftRightAngle));
		jsonObject.put("updown", String.valueOf(upDownAngle));
		
		String reqJson = jsonObject.toString();
		coapClient = new CoapClient();
		coapClient.setURI(IPADDRESS + resource);
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		
		if(coapResponse == null){
			System.out.println("Fail Response");
			System.out.println(reqJson);
			return post(command, upDownAngle, leftRightAngle, resource);
		}else{
			System.out.println("Success Response - " + resource);
			return coapResponse.getResponseText();
		}
	}
	public String post(String command, String line0, String line1, String resource){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("command", command);
		jsonObject.put("line0", line0);
		jsonObject.put("line1", line1);
		
		String reqJson = jsonObject.toString();
		coapClient = new CoapClient();
		coapClient.setURI(IPADDRESS + resource);
		CoapResponse coapResponse = coapClient.post(reqJson, MediaTypeRegistry.APPLICATION_JSON);
		
		if(coapResponse == null){
			System.out.println("Fail Response");
			System.out.println(reqJson);
			return post(command, line0, line1, resource);
		}else{
			System.out.println("Success Response - " + resource);
			return coapResponse.getResponseText();
		}
	}
	
	public void handleBtnControlBuzzer(ActionEvent event){
		
		post(buzzer.getBuzzerChange(), buzzer.getOnOffValue(), buzzer.getResource());
		
		if(buzzer.getOnOffValue().equals("on")){
			buzzer.setOnOffValue("off");
		}else if(buzzer.getOnOffValue().equals("off")){
			buzzer.setOnOffValue("on");
		}
	}
	
	public void handleBtnControlLaser(ActionEvent event){
		
		post(laserEmitter.getBuzzerChange(), laserEmitter.getOnOffValue(), laserEmitter.getResource());
		
		if(laserEmitter.getOnOffValue().equals("on")){
			laserEmitter.setOnOffValue("off");
		}else if(laserEmitter.getOnOffValue().equals("off")){
			laserEmitter.setOnOffValue("on");
		}
	}
	public void handleBtnControlRed(){
		rgb.setRed(255);rgb.setGreen(0);rgb.setBlue(0);
		post(rgb.getRgbChange(), rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getResource());
	}
	public void handleBtnControlGreen(){
		rgb.setRed(0);rgb.setGreen(255);rgb.setBlue(0);
		post(rgb.getRgbChange(), rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getResource());	
	}
	public void handleBtnControlBlue(){
		rgb.setRed(0);rgb.setGreen(0);rgb.setBlue(255);
		post(rgb.getRgbChange(), rgb.getRed(), rgb.getGreen(), rgb.getBlue(), rgb.getResource());
	}
	public void handleBtnControlFrontTire(ActionEvent event, int angle){
		frontTire.setAngle(angle);
		post(frontTire.getFrontTireChange(), frontTire.getAngle(), frontTire.getResource());
	}
	public void handleBtnControlBackTireSpeed(String signal){
		if(signal.equals("accel")){
			backTire.setRealSpeed(backTire.getRealSpeed() + 100);
		}else if(signal.equals("break")){
			backTire.setRealSpeed(backTire.getRealSpeed() - 100);
		}
		if(backTire.getRealSpeed() > 3600){
			post(buzzer.getBuzzerChange(), "on", buzzer.getResource());
		}else if(backTire.getRealSpeed() <= 3600){
			post(buzzer.getBuzzerChange(), "off", buzzer.getResource());
		}
		if(backTire.getRealSpeed() < 2800){
			handleBtnControlBlue();
		}else if(backTire.getRealSpeed() >= 2800 && backTire.getRealSpeed() < 3600){
			handleBtnControlGreen();
		}else if(backTire.getRealSpeed() >= 3600){
			handleBtnControlRed();
		}
		
		gauge.setValue(backTire.getRealSpeed());
		post(backTire.getBackTireCommand(), backTire.getDirection(), backTire.getRealSpeed(), backTire.getResource());
			
	}
	public void handleBtnControlBackTireDirection(ActionEvent event, String direction){
		backTire.setDirection(direction);
		
		post(backTire.getBackTireCommand(), backTire.getDirection(), backTire.getRealSpeed(), backTire.getResource());
	}
	public void handleBtnControlUltrasonic(ActionEvent event, int angle){
		ultrasonic.setAngle(angle);
		
		post(ultrasonic.getUltrasonicChange(), ultrasonic.getAngle(), ultrasonic.getResource());
	}
	public void handleBtnControlCameraUpDown(ActionEvent event, int angle){
		camera.setUpDownAngle(angle);
		
		post(camera.getCameraChange(), camera.getUpDownAngle(), camera.getLeftRightAngle(), camera.getResource());
	}
	public void handleBtnControlCameraLeftRight(ActionEvent event, int angle){
		camera.setLeftRightAngle(angle);
		
		post(camera.getCameraChange(), camera.getUpDownAngle(), camera.getLeftRightAngle(), camera.getResource());
	}
	public void handleBtnControlLCD(ActionEvent event){
		//lcd.setLine0(txtLine0.getText());
		//lcd.setLine1(txtLine1.getText());
		
		post(lcd.getLcdCommand(), lcd.getLine0(), lcd.getLine1(), lcd.getResource());
	}



	//////////////// FX

	
	public void handleBtnControlBack(ActionEvent event) {

		try {
			StackPane parent = FXMLLoader.load(MenuController.class.getResource("menu.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);

			KeyValue keyValueStackPane = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPane = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
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
	public void handleBtnControlLock(ActionEvent event) {
		LockController.lockRootPane.getChildren().remove(actuatorStackPane);
	}

	public void handleBtnControlHome(ActionEvent event) {

		try {
			StackPane parent = FXMLLoader.load(MainController.class.getResource("main.fxml")); // css와 같은방식으로 클래스를 import해서 해당 패키지 리소스에 접근
			LockController.lockRootPane.getChildren().add(LockController.lockRootPane.getChildren().size(), parent);
			
			parent.getChildren().get(0).setOpacity(0);
			parent.getChildren().get(1).setOpacity(0);

			KeyValue keyValueStackPane = new KeyValue(parent.getChildren().get(0).opacityProperty(), 1);
			KeyFrame keyFrameStackPane = new KeyFrame(Duration.millis(500), (e) -> {
				Timeline timeline2 = new Timeline();
				KeyValue keyvalue2 = new KeyValue(parent.getChildren().get(1).opacityProperty(), 1);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyvalue2);
				timeline2.getKeyFrames().add(keyFrame2);
				timeline2.play();
				LockController.lockRootPane.getChildren().remove(1);
			}, keyValueStackPane);

			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrameStackPane);
			timeline.play();
		} catch (IOException ex) {
		}
	}

	@Override
	protected void finalize() throws Throwable {
		coapClient.shutdown();
	}
	
	
}
