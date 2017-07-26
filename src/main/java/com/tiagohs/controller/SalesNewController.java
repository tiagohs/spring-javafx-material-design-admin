package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;

import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.stage.Stage;

@Controller
public class SalesNewController implements BaseController {

	public static final String PATH_FXML = "/fxml/new_sale_order.fxml";
	public static final String TITLE = "New Sale - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		
	}
	
	@FXML
	public void onSave() {
		
	}
	
	@FXML
	public void onCancel() {
		
	}
	
	@FXML
	public void onHelp() {
		
	}
}
