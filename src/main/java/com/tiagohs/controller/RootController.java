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

@FXMLController(value = "/fxml/root.fxml", title = "stuffs-Admin", iconPath = "/images/icon.png")
public class RootController {
	
	@FXML
	private AnchorPane rootPane;
	
	private FlowHandler flowHandler;
	
	@PostConstruct
	public void init() throws FlowException {
		Flow innerFlow = new Flow(DashboardController.class);
 
        flowHandler = innerFlow.createHandler();
        rootPane.getChildren().add(flowHandler.start(new AnimatedFlowContainer(Duration.millis(320), ContainerAnimations.ZOOM_IN)));
	}
}
