package com.tiagohs.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXButton;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.container.AnimatedFlowContainer;
import io.datafx.controller.flow.container.ContainerAnimations;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

@FXMLController(value = "/fxml/root.fxml", title = "stuffs-Admin", iconPath = "/images/icon.png")
public class RootController {
	
	@FXML
	private AnchorPane rootPane;
	
	@FXML
	@ActionTrigger("inventoryButton")
	private JFXButton inventoryButton;
	
	private FlowHandler flowHandler;
	
	@PostConstruct
	public void init() throws FlowException {
		Flow innerFlow = new Flow(DashboardController.class);
 
        flowHandler = innerFlow.createHandler();
        rootPane.getChildren().add(flowHandler.start(new AnimatedFlowContainer(Duration.millis(320), ContainerAnimations.ZOOM_IN)));
        
        inventoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Flow innerFlow = new Flow(ProductNewController.class);
       		 
                flowHandler = innerFlow.createHandler();
                try {
                	rootPane.getChildren().clear();
					rootPane.getChildren().add(flowHandler.start());
				} catch (FlowException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
	}
	
	@ActionMethod("inventoryButton")
	private void onInvetoryAction() throws FlowException {
		
		
		
	}
}
