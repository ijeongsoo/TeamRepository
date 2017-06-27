
package com.tomcatisbabycat.homepanel.controller.knob.knobfx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author F-effect
 */
public class Demo extends Application {
    
   

    @Override
    public void start(Stage primaryStage) throws InterruptedException, IOException {

        AnchorPane root=FXMLLoader.load(getClass().getResource("root.fxml"));
         
//        Knob k=new Knob();
//        k.setPrefHeight(450);
//        k.setPrefWidth(450);
//		k.setOnMouseDragged((event) -> {
//			  //System.out.println(event.getS);
//			  System.out.println(Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI);
//			  if((Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI)>0)
//				   k.setValue(Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI);
//			  else if((Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI)>-90)
//					k.setValue(0);
//			else if((Math.atan2(225-event.getSceneY(),225-event.getSceneX())*180/Math.PI)<-90)
//				  k.setValue(180);
//		});
		
		
		

//        root.getChildren().add(k);
//		root.getChildren().add(label);
 
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(this.getClass().getResource("/resources/style.css").toExternalForm());
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	  public static void main(String[] args) {
			launch(args);
	  }

}