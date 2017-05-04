/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.main;

import com.tomcatisbabycat.homepanel.resources.images.ImageResourceFinder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 *
 * @author ijeongsu
 */
public class MainImageSet {
	private ImageView mainImage;

	public MainImageSet(ImageView mainImage) {
		this.mainImage = mainImage;
	}
	
	
	
	public void mainImageSet(){
		Image image = new Image(ImageResourceFinder.class.getResource(ImageResourceFinder.getImageFileName()).toString());
		double originalHight = image.getHeight();
		double originalWidth = image.getWidth();
		if (originalHight > originalWidth) {
			mainImage.setFitWidth(180);
			mainImage.setFitHeight(0);
			double resizeHeight = (180 * originalHight) / originalWidth;
			mainImage.setImage(image);
			Circle clip = new Circle(mainImage.getFitWidth() / 2, mainImage.getFitWidth() / 2, mainImage.getFitWidth() / 2);
			mainImage.setClip(clip);
		} else {
			double resizeWidth = (180 * originalWidth) / originalHight;
			mainImage.setImage(image);
			Circle clip = new Circle(resizeWidth / 2, mainImage.getFitHeight() / 2, mainImage.getFitHeight() / 2);
			mainImage.setClip(clip);
		}
	}

	
	
	
	
}
