package com.tiagohs;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SplashScreen extends Preloader {
	
	private Stage stage;
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		this.stage.setScene(createScene());
		this.stage.show();
	}
	
	private Scene createScene() {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 300, 200);
        return scene;
    }

	@Override
	public void handleApplicationNotification(PreloaderNotification notification) {
		if (notification instanceof StateChangeNotification) {
			stage.hide();
        }
	}

}
