package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.tiagohs.model.Language;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

@Controller
public class SettingsController extends BaseController {

	public static final String PATH_FXML = "/fxml/settings.fxml";
	public static final String TITLE = "Settings - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private JFXComboBox<Language> languageComboBox;
	
	@FXML
	private Button saveButton;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		
	}
	
	@Override
	protected void onClose() {
		
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
