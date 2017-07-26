package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;

import com.tiagohs.util.WindowsUtils;

import javafx.stage.Stage;

@Controller
public class ReportsController implements BaseController{

	public static final String PATH_FXML = "/fxml/reports.fxml";
	public static final String TITLE = "Reports - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		
	}
	
}
