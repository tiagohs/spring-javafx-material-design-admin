package com.tiagohs.controller;

import javax.annotation.PostConstruct;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.AnimatedFlowContainer;
import io.datafx.controller.flow.container.ContainerAnimations;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

@FXMLController(value = "/fxml/root.fxml", title = "stuffs-Admin: Inventory Management", iconPath = "/images/icon.png")
public class RootController {
	
	@FXML
	private AnchorPane rootPane;
	
	private FlowHandler flowHandler;
	
	@PostConstruct
	public void init() throws FlowException {
		openWindow(DashboardController.class);
	}
	
	@FXML
	private void onInvetoryAction() throws FlowException {
		openWindow(ProductNewController.class);
	}
	
	@FXML
	private void onDashboardAction() throws FlowException {
		openWindow(DashboardController.class);
	}
	
	private void openWindow(Class<?> controller) throws FlowException {
		Flow innerFlow = new Flow(controller);
 		 
        flowHandler = innerFlow.createHandler();
        
        rootPane.getChildren().clear();
		rootPane.getChildren().add(flowHandler.start(new AnimatedFlowContainer(Duration.seconds(2), ContainerAnimations.FADE)));
	}
	
	
}
