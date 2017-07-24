package com.tiagohs.controller;

import org.springframework.stereotype.Controller;

import com.tiagohs.util.WindowsUtils;

import javafx.stage.Stage;

@Controller
public class BrandNewController implements BaseController {

	public static final String PATH_FXML = "/fxml/new_brand.fxml";
	public static final String TITLE = "New Brand - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@Override
	public void init(Stage stage) {
		
	}

}
