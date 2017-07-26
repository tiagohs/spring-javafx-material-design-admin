package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;

import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@Controller
public class ClientNewController implements BaseController {

	public static final String PATH_FXML = "/fxml/new_client.fxml";
	public static final String TITLE = "New Client - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private StackPane container;
	
	private Stage clientNewStage;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		this.clientNewStage = stage;
		
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
