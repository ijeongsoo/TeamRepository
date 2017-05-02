/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.cctv;

import com.tomcatisbabycat.homepanel.main.MainController;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnControlHome.setOnAction(event -> {
			handleBtnControlHome(event);
		}); 
	}	

	private void handleBtnControlHome(ActionEvent event) {
		StackPane rootPane = (StackPane) cctvStackPane.getScene().getRoot();
		
		cctvStackPane.setTranslateX(0);
		KeyValue keyValue = new KeyValue(cctvStackPane.translateXProperty(), 800);
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(1),
				e -> {
					//rootPane.getChildren().removeAll(MainController.paneBasket);
					//rootPane.getChildren().remove(cctvStackPane);
					Iterator iterator = rootPane.getChildren().iterator();
					while(iterator.hasNext()){
						if(iterator.next() instanceof StackPane){
							iterator.remove();
						}
					}
					//rootPane.getChildren().clear(); // clear 자식으로 있는 모든 컴포넌트 삭제 ㅋㅋ
				}, keyValue);

		Timeline timeline = new Timeline();
		timeline.getKeyFrames().add(keyFrame);

		timeline.play();
	}
	
}
