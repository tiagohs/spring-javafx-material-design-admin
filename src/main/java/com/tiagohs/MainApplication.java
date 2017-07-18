package com.tiagohs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tiagohs.controller.LoginController;
import com.tiagohs.util.WindowsUtils;

import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

@SpringBootApplication
public class MainApplication extends Application  {
	
    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    @Override
    public void init() throws Exception {
        SpringApplication.run(MainApplication.class);
    }
	
    @Override
	public void start(Stage primaryStage) throws Exception {
    	Scene scene = new Scene(new Flow(LoginController.class).start());
        scene.getStylesheets().add(WindowsUtils.BASE_APPLICATION_CSS_PATH);
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(WindowsUtils.ICON_APP_PATH));
        primaryStage.show();
	}
	 
	public static void main(String[] args) {
		launch(args);
	}
	
}
