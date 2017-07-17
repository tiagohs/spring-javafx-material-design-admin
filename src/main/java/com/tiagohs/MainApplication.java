package com.tiagohs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tiagohs.controller.RootController;

import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.scene.Scene;
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
    	
    	Scene scene = new Scene(new Flow(RootController.class).start());
        scene.getStylesheets().add(MainApplication.class.getResource("/css/application.css").toExternalForm());
        scene.getStylesheets().add(MainApplication.class.getResource("/css/jfoenix.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	 
	public static void main(String[] args) {
		launch(args);
	}
	
}
