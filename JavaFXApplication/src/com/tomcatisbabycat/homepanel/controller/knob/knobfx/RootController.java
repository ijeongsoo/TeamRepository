/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.controller.knob.knobfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author ijeongsu
 */
public class RootController implements Initializable {

	  @FXML
	  private Knob knob;
	  @FXML
	  private Label tempo;
	  @FXML
	  private Knob knob2;

	  /**
	   * Initializes the controller class.
	   */
	  @Override
	  public void initialize(URL url, ResourceBundle rb) {
			knob.setValue(28);
			
			knob.setOnMouseDragged((event) -> {
				  //System.out.println(Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI);
			  if((Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI)>0)
				   knob.setValue((Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI)/3);
			  else if(((Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI))/3>-30)
					knob.setValue(0);
			else if(((Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI))/3<-30)
				  knob.setValue(60);
			});
			
			knob.setOnMouseClicked((event) -> {
				  System.out.println(event.getSceneX()+","+event.getSceneY());
			});
			
			knob.valueProperty().addListener(new ChangeListener<Number>(){
				  @Override
				  public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						
						tempo.setText(newValue.intValue()+"°");
				  };
			});
			
			knob2.setOnMouseDragged((event) -> {
				  //System.out.println(Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI);
			  if((Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI)>0)
				   knob2.setValue((Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI)/3);
			  else if(((Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI))/3>-30)
					knob2.setValue(0);
			else if(((Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI))/3<-30)
				  knob2.setValue(60);
			});
	  }	  
	  
}
