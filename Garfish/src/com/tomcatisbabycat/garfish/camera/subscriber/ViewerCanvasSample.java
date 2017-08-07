package com.tomcatisbabycat.garfish.camera.subscriber;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ViewerCanvasSample extends Application {  
	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		ViewerCanvas viewer = new ViewerCanvas(320, 240);
		root.getChildren().add(viewer);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		primaryStage.setWidth(320);
		primaryStage.setHeight(240);

		primaryStage.setResizable(false);
		primaryStage.setTitle("JavaFX - MjpegViewer");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
