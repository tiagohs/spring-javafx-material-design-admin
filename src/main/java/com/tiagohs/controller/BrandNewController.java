package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.tiagohs.model.Brand;
import com.tiagohs.service.BrandService;
import com.tiagohs.util.EntityFactory;
import com.tiagohs.util.ValidatorUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@Controller
public class BrandNewController extends BaseController {
	
	public static final String BRAND_KEY = "brand_key";
	
	public static final String PATH_FXML = "/fxml/new_brand.fxml";
	public static final String NEW_BRAND_TITLE_KEY = "new_brand.title";
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
	
	private Brand brand;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		checkParameters(parameters);
		validateTextFields();
		watchEvents();
	}

	@Override
	protected void onClose() {
		brandService.onClose();
	}
	
	private <T> void checkParameters( HashMap<String, T> parameters) {
		if (parameters != null) {
			this.brand = (Brand) parameters.get(BRAND_KEY);
			updateTextFields();
		}
	}

	private void updateTextFields() {
		
		WindowsUtils.setTextInTextField(nameTextField, brand.getName());
		WindowsUtils.setTextInTextField(emailTextField, brand.getEmail());
		WindowsUtils.setTextInTextArea(additionalInfoTextArea, brand.getAdditionalInformation());
	}

	private void watchEvents() {
		WindowsUtils.watchEvents(nameTextField, v -> watch());
		WindowsUtils.watchEvents(emailTextField, v -> watch());
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
		
		try {
			brandService.save(EntityFactory.createBrand(brand, WindowsUtils.getTextFromTextField(nameTextField), 
														WindowsUtils.getTextFromTextField(emailTextField), 
														WindowsUtils.getTextFromTextArea(additionalInfoTextArea)), e -> {
															WindowsUtils.createDefaultDialog(container, "Sucess", "Brand save with sucess.", () -> { stage.close(); });
														}, null);
		} catch (Exception e) {
			e.printStackTrace();
			WindowsUtils.createDefaultDialog(container, "Error", "Error saving brand, try again.", () -> {});
		}
	}
	
	@FXML
	public void onCancel() {
		stage.close();
	}
	
	@FXML
	public void onHelp() {
		
	}

}
