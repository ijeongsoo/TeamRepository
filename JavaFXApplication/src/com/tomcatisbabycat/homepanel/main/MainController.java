/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main;

import com.tomcatisbabycat.homepanel.resources.images.ImageResourceFinder;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author ijeongsu
 */
public class MainController implements Initializable {

	  @FXML
	  private Button mainBtn3;
	  @FXML
	  private Button mainBtn4;
	  @FXML
	  private ImageView mainImage;
	  @FXML
	  private BorderPane mainImagePane;


	  /**
	   * Initializes the controller class.
	   */
	  @Override
	  public void initialize(URL url, ResourceBundle rb) {
			
			Image image = new Image(ImageResourceFinder.class.getResource(ImageResourceFinder.getImageFileName()).toString());
			double originalHight=image.getHeight();
			double originalWidth=image.getWidth();
			if(originalHight>originalWidth){
				  mainImage.setFitWidth(180);
				  mainImage.setFitHeight(0);
				  double resizeHeight=(180*originalHight)/originalWidth;
				  mainImage.setImage(image);
				  Circle clip = new Circle(mainImage.getFitWidth()/2,mainImage.getFitWidth()/2, mainImage.getFitWidth()/2);
				  mainImage.setClip(clip);
			}else{
				  double resizeWidth=(180*originalWidth)/originalHight;
				  mainImage.setImage(image);
				  Circle clip = new Circle(resizeWidth/2,mainImage.getFitHeight()/2, mainImage.getFitHeight()/2);
				  mainImage.setClip(clip);
			}
			
			
			mainBtn4.setOnAction((event) -> {
				  System.out.println("aaa");
			});
			
	  }	 

	  
}
