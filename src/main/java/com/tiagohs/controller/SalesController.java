package com.tiagohs.controller;

import org.springframework.stereotype.Controller;

import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Controller
public class SalesController implements BaseController {

	public static final String PATH_FXML = "/fxml/sales.fxml";
	public static final String TITLE = "Sales - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@Override
	public void init(Stage stage) {
		
	}
	
	@FXML
	public void onNewSale() throws Exception {
		WindowsUtils.openNewWindow(SalesNewController.PATH_FXML, SalesNewController.TITLE, SalesNewController.PATH_ICON, Modality.APPLICATION_MODAL);
	}
}
