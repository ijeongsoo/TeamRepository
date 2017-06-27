/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.lock;

import com.tomcatisbabycat.homepanel.css.CSSSelector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ijeongsu
 */
public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("lock.fxml"));

		
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(CSSSelector.class.getResource(CSSSelector.getSeasonCSS()).toString());
		stage.initStyle(StageStyle.UNDECORATED);
		
		//stage.setFullScreen(true);
		//stage.setFullScreenExitHint("");
		
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Thread thread = new statusThread();
		thread.setDaemon(true);
		thread.start();
		launch(args);
	}

}
