package com.tiagohs.controller;

import javax.annotation.PostConstruct;

import com.tiagohs.util.WindowsUtils;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

@FXMLController(value = "/fxml/root.fxml", title = "stuffs-Admin: Inventory Management")
public class RootController {
	
	@FXML
	private AnchorPane rootPane;
	
	@PostConstruct
	public void init() throws FlowException {
		WindowsUtils.replaceFxmlOnWindow(rootPane, DashboardController.class);
	}
	
	@FXML
	private void onInvetoryAction() throws FlowException {
		WindowsUtils.replaceFxmlOnWindow(rootPane, InventoryController.class);
	}
	
	@FXML
	private void onDashboardAction() throws FlowException {
		WindowsUtils.replaceFxmlOnWindow(rootPane, DashboardController.class);
	}
	
}
