package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.tiagohs.model.Brand;
import com.tiagohs.service.BrandService;
import com.tiagohs.util.ValidatorUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@Controller
public class BrandNewController implements BaseController {

	public static final String PATH_FXML = "/fxml/new_brand.fxml";
	public static final String TITLE = "New Brand - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;

	@FXML
	private StackPane container;
	
	@FXML
	private JFXTextField nameTextField;
	
	@FXML
	private JFXTextField emailTextField;
	
	@FXML
	private JFXTextArea additionalInfoTextArea;

	@FXML
	private JFXButton saveButton;
	
	@Autowired
	private BrandService brandService;
	
	private Stage brandNewStage;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		this.brandNewStage = stage;
		
		validateTextFields();
		watchEvents();
	}

	private void watchEvents() {
		WindowsUtils.watchEvents(nameTextField, () -> watch());
		WindowsUtils.watchEvents(emailTextField, () -> watch());
	}

	private void validateTextFields() {
		ValidatorUtils.addRequiredValidator(nameTextField, "Brand Name is Required!");
		ValidatorUtils.addRequiredValidator(emailTextField, "Email is Required!");
		
		ValidatorUtils.addEmailValidator(emailTextField, "Email does not match");
		
		WindowsUtils.validateTextField(nameTextField);
		WindowsUtils.validateTextField(emailTextField);
	}
	
	private void watch() {
		if (isRequiredTextFieldsFilled() && (emailTextField.validate())) {
			saveButton.setDisable(false);
		} else {
			saveButton.setDisable(true);
		}
		
	}
	
	private boolean isRequiredTextFieldsFilled() {
		return  !WindowsUtils.isTextFieldEmpty(nameTextField) ||
				!WindowsUtils.isTextFieldEmpty(emailTextField);
	}
	
	@FXML
	public void onSave() {
		Brand brand = new Brand();
		
		brand.setName(WindowsUtils.getTextFromTextField(nameTextField));
		brand.setEmail(WindowsUtils.getTextFromTextField(emailTextField));
		brand.setAdditionalInformation(WindowsUtils.getTextFromTextArea(additionalInfoTextArea));
		
		try {
			brandService.save(brand);
			WindowsUtils.createDefaultDialog(container, "Sucess", "Brand save with sucess.", () -> { brandNewStage.close(); });
		} catch (Exception e) {
			e.printStackTrace();
			WindowsUtils.createDefaultDialog(container, "Error", "Error saving brand, try again.", () -> {});
		}
	}
	
	@FXML
	public void onCancel() {
		
	}
	
	@FXML
	public void onHelp() {
		
	}

}
