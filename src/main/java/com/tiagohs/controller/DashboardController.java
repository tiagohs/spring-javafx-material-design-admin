package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;

import com.tiagohs.util.WindowsUtils;

import javafx.stage.Stage;

@Controller
public class DashboardController implements BaseController {
	
	public static final String PATH_FXML = "/fxml/dashboard.fxml";
	public static final String TITLE = "Dashboard - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		
	}
	
	
	
	
}
