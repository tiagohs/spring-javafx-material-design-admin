package com.tiagohs.controller;

import java.util.HashMap;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.tiagohs.model.Language;
import com.tiagohs.service.LanguageService;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

@Controller
public class SettingsController extends BaseController {

	public static final String PATH_FXML = "/fxml/settings.fxml";
	public static final String SETTINGS_TITLE_KEY = "settings.title";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private JFXComboBox<Language> languageComboBox;
	
	@FXML
	private Button saveButton;
	
	@Autowired
	private LanguageService languageService;
	
	private Language defaultLanguage;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		fillComboBoxes();
		addDefaultLanguage();
		watchEvents();
	}
	
	private void addDefaultLanguage() {
		this.defaultLanguage = languageService.findDefaultLanguage();
		
		WindowsUtils.setSelectedComboBoxItem(languageComboBox, defaultLanguage);
	}

	private void fillComboBoxes() {
		WindowsUtils.addComboBoxItens(languageComboBox, languageService);
	}
	
	private void watchEvents() {
		WindowsUtils.onComboBoxItemSelected(languageComboBox, v -> watch(v));
	}
	
	private void watch(Language languageSelected) {
		if (languageSelected.equals(defaultLanguage)) {
			saveButton.setDisable(true);
		} else {
			saveButton.setDisable(false);
		}
		
	}
	
	@Override
	protected void onClose() {
		languageService.onClose();
	}
	
	@FXML
	public void onSave() {
		
		Language newLanguage = WindowsUtils.getSelectedComboBoxItem(languageComboBox);
		
		languageService.changeDefaultLanguage(newLanguage, e -> {
			
			try {
				getI18N().updateDefaultLocale(new Locale(newLanguage.getLanguageCode(), newLanguage.getCountryCode()));
				WindowsUtils.openNewWindow(RootController.PATH_FXML, getWindowTitle(RootController.ROOT_TITLE_KEY), RootController.PATH_ICON, null, null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			stage.close();
		}, null);
	}
	
	@FXML
	public void onCancel() {
		stage.close();
	}
	
	@FXML
	public void onHelp() {
		
	}


}
