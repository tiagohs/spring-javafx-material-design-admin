package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.tiagohs.model.Address;
import com.tiagohs.model.Supplier;
import com.tiagohs.service.SupplierService;
import com.tiagohs.service.UserService;
import com.tiagohs.util.EntityFactory;
import com.tiagohs.util.ValidatorUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@Controller
public class SupplierNewController extends BaseController {

	public static final String SUPPLIER_KEY = "supplier_key";
	
	public static final String PATH_FXML = "/fxml/new_supplier.fxml";
	public static final String NEW_SUPPLIER_TITLE_KEY = "new_supplier.title";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private StackPane container;
	
	@FXML
	private JFXTextField nameTextField;
	
	@FXML
	private JFXTextField emailTextField;
	
	@FXML
	private JFXTextField cepTextField;

	@FXML
	private JFXTextField streetTextField;

	@FXML
	private JFXTextField numberTextField;
	
	@FXML
	private JFXTextField districtTextField;

	@FXML
	private JFXTextField complementTextField;

	@FXML
	private JFXTextField cityTextField;

	@FXML
	private JFXTextField stateTextField;
	
	@FXML
	private JFXComboBox<String> countryComboBox;
	
	@FXML
	private JFXButton saveButton;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private UserService userService;
	
	private Supplier supplier;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		checkParameters(parameters);
		validateTextFields();
		watchEvents();
	}

	@Override
	protected void onClose() {
		userService.onClose();
		supplierService.onClose();
	}
	
	private <T> void checkParameters( HashMap<String, T> parameters) {
		if (parameters != null) {
			this.supplier = (Supplier) parameters.get(SUPPLIER_KEY);
			updateTextFields();
		}
	}

	private void updateTextFields() {
		
		WindowsUtils.setTextInTextField(nameTextField, supplier.getCompanyName());
		WindowsUtils.setTextInTextField(emailTextField, supplier.getEmail());
		WindowsUtils.setTextInTextField(cepTextField, supplier.getAddres().getCep());
		WindowsUtils.setTextInTextField(streetTextField, supplier.getAddres().getStreet());
		WindowsUtils.setTextInTextField(numberTextField, supplier.getAddres().getNumber());
		WindowsUtils.setTextInTextField(districtTextField, supplier.getAddres().getSuburb());
		WindowsUtils.setTextInTextField(cityTextField, supplier.getAddres().getCity());
		WindowsUtils.setTextInTextField(stateTextField, supplier.getAddres().getState());
		WindowsUtils.setTextInTextField(complementTextField, supplier.getAddres().getComplement());
		
		WindowsUtils.setSelectedComboBoxItem(countryComboBox, supplier.getAddres().getCountry());
	}

	private void validateTextFields() {
		ValidatorUtils.addRequiredValidator(nameTextField, "Supplier Name is Required!");
		ValidatorUtils.addRequiredValidator(emailTextField, "Supplier E-mail is Required!");
		ValidatorUtils.addRequiredValidator(cepTextField, "Zip code is Required!");
		ValidatorUtils.addRequiredValidator(streetTextField, "Street is Required!");
		ValidatorUtils.addRequiredValidator(numberTextField, "Number is Required!");
		ValidatorUtils.addRequiredValidator(districtTextField, "District is Required!");
		ValidatorUtils.addRequiredValidator(cityTextField, "City is Required!");
		ValidatorUtils.addRequiredValidator(stateTextField, "State is Required!");
		
		ValidatorUtils.addNumberOnlyValidator(cepTextField);
		ValidatorUtils.addNumberOnlyValidator(numberTextField);
		
		ValidatorUtils.addMaxLengthValidator(cepTextField, 8);
		
		ValidatorUtils.addEmailValidator(emailTextField, "Email does not match");
		
		ValidatorUtils.addDuplicateUserValidator(emailTextField, "An account for the specified email address already exists", userService);
		
		WindowsUtils.validateTextField(nameTextField);
		WindowsUtils.validateTextField(emailTextField);
		WindowsUtils.validateTextField(cepTextField);
		WindowsUtils.validateTextField(streetTextField);
		WindowsUtils.validateTextField(numberTextField);
		WindowsUtils.validateTextField(districtTextField);
		WindowsUtils.validateTextField(cityTextField);
		WindowsUtils.validateTextField(stateTextField);
	}
	
	private void watchEvents() {
		WindowsUtils.watchEvents(nameTextField, v -> watch());
		WindowsUtils.watchEvents(emailTextField, v -> watch());
	}
	
	private void watch() {
		if (isRequiredTextFieldsFilled() && isAddressFilled() && (emailTextField.validate())) {
			saveButton.setDisable(false);
		} else {
			saveButton.setDisable(true);
		}
		
	}

	private boolean isRequiredTextFieldsFilled() {
		return  !WindowsUtils.isTextFieldEmpty(nameTextField) ||
				!WindowsUtils.isTextFieldEmpty(emailTextField) ||
				!WindowsUtils.isTextFieldEmpty(cepTextField) ||
				!WindowsUtils.isTextFieldEmpty(streetTextField) ||
				!WindowsUtils.isTextFieldEmpty(numberTextField) ||
				!WindowsUtils.isTextFieldEmpty(districtTextField) ||
				!WindowsUtils.isTextFieldEmpty(cityTextField) ||
				!WindowsUtils.isTextFieldEmpty(stateTextField);
	}
	
	private boolean isAddressFilled() {
		return  !WindowsUtils.isTextFieldEmpty(streetTextField) ||
				!WindowsUtils.isTextFieldEmpty(numberTextField) ||
				!WindowsUtils.isTextFieldEmpty(districtTextField) ||
				!WindowsUtils.isTextFieldEmpty(complementTextField) ||
				!WindowsUtils.isTextFieldEmpty(cityTextField) ||
				!WindowsUtils.isTextFieldEmpty(stateTextField);
	}

	@FXML
	public void onSave() {
		
		Address address = null;
		
		if (isAddressFilled()) {
			address = EntityFactory.createAddress(WindowsUtils.getTextFromTextField(streetTextField), 
					WindowsUtils.getIntegerFromTextField(numberTextField), 
					WindowsUtils.getTextFromTextField(complementTextField), 
					WindowsUtils.getTextFromTextField(districtTextField), 
					WindowsUtils.getTextFromTextField(cityTextField), 
					WindowsUtils.getTextFromTextField(stateTextField), 
					(String) WindowsUtils.getSelectedComboBoxItem(countryComboBox), 
					WindowsUtils.getTextFromTextField(cepTextField));
		}
		
		try {
			supplierService.save(EntityFactory.createSupplier(supplier, WindowsUtils.getTextFromTextField(nameTextField), 
															  WindowsUtils.getTextFromTextField(emailTextField), 
															  address), e -> {
																	WindowsUtils.createDefaultDialog(container, "Sucess", "Supplier save with sucess.", () -> { stage.close(); });
																}, null);
		} catch (Exception e) {
			e.printStackTrace();
			WindowsUtils.createDefaultDialog(container, "Error", "Error saving supplier, try again.", () -> {});
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
