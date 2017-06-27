/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.resources.images;

/**
 *
 * @author ijeongsu
 */
public class ImageResourceFinder {
	  private static String ImageFileName="profile.jpeg";

	  public static String getImageFileName() {
			return ImageFileName;
	  }

	  public static void setImageFileName(String ImageFileName) {
			ImageResourceFinder.ImageFileName = ImageFileName;
	  }
	  
	  
}
