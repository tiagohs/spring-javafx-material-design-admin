package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;

import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.stage.Stage;

@Controller
public class AboutController implements BaseController {
	
	public static final String PATH_FXML = "/fxml/about.fxml";
	public static final String TITLE = "About Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
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
