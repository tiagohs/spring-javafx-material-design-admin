package com.tiagohs.controller;

import org.springframework.stereotype.Controller;

import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


@Controller
public class RootController implements BaseController {
	
	@FXML
	private AnchorPane rootPane;
	
	public void init(Stage stage) {
		
		try {
			WindowsUtils.replaceFxmlOnWindow(rootPane, "/fxml/dashboard.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onInvetoryAction() throws Exception {
		WindowsUtils.replaceFxmlOnWindow(rootPane, "/fxml/inventory.fxml");
	}
	
	@FXML
	private void onDashboardAction() throws Exception {
		WindowsUtils.replaceFxmlOnWindow(rootPane, "/fxml/dashboard.fxml");
	}
	
	@FXML
	private void onSalesAction() throws Exception {
		WindowsUtils.replaceFxmlOnWindow(rootPane, "/fxml/sales.fxml");
	}
	
}
