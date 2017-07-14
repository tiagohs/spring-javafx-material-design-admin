package com.tiagohs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.jfoenix.controls.JFXDecorator;
import com.tiagohs.controller.ProductNewController;

import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@SpringBootApplication
public class MainApplication extends Application  {
	
	private ConfigurableApplicationContext springContext;
    private Parent root;
    
    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(MainApplication.class);
        
        /*FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        root = fxmlLoader.load();*/
         
    }
	
    @Override
	public void start(Stage primaryStage) throws Exception {
    	
    	Flow flow = new Flow(ProductNewController.class);
    	DefaultFlowContainer container = new DefaultFlowContainer();
    	
    	flowContext = new ViewFlowContext();
    	flowContext.register("Stage", primaryStage);
    	flow.createHandler(flowContext).start(container);
    	
    	Scene scene = new Scene(container.getView());
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(MainApplication.class.getResource("/css/application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        primaryStage.show();
	}
	 
	public static void main(String[] args) {
		launch(args);
	}
	
}
