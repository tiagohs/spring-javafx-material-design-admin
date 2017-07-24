package com.tiagohs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.tiagohs.controller.LoginController;
import com.tiagohs.util.WindowsUtils;

import javafx.application.Application;
import javafx.stage.Stage;

@SpringBootApplication
public class MainApplication extends Application  {
	
	public static ConfigurableApplicationContext springContext;
    
    @Override
    public void init() throws Exception {
    	springContext = SpringApplication.run(MainApplication.class);
    }
	
    @Override
	public void start(Stage primaryStage) throws Exception {
    	WindowsUtils.openNewWindow(primaryStage, LoginController.PATH_FXML, LoginController.TITLE, LoginController.PATH_ICON);
	}
    
	public static void main(String[] args) {
		launch(args);
	}
	
}
