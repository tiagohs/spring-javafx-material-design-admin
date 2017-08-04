package com.tiagohs.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.tiagohs.model.Address;
import com.tiagohs.model.Employee;
import com.tiagohs.model.Fone;
import com.tiagohs.model.Role;
import com.tiagohs.model.User;
import com.tiagohs.service.EmployeeService;
import com.tiagohs.service.RoleService;
import com.tiagohs.service.UserService;
import com.tiagohs.util.EntityFactory;
import com.tiagohs.util.ValidatorUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@Controller
public class EmployeeNewController implements BaseController {

	public static final String EMPLOYEE_KEY = "employee_key";
	
	public static final String PATH_FXML = "/fxml/new_employee.fxml";
	public static final String TITLE = "New Employee - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;

	@FXML
	private StackPane container;
	
	@FXML
	private JFXTextField nameTextField;

	@FXML
	private JFXTextField emailTextField;

	@FXML
	private JFXTextField cpfTextField;

	@FXML
	private JFXTextField residentialPhoneTextField;

	@FXML
	private JFXTextField cellPhoneTextField;

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
	private JFXPasswordField passwordTextField;

	@FXML
	private JFXPasswordField confirmPasswordTextField;

	@FXML
	private JFXComboBox<String> countryComboBox;

	@FXML
	private JFXComboBox<Role> roleComboBox;
	
	@FXML
	private JFXButton saveButton;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	private Stage employeeNewStage;
	private Employee employee;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		this.employeeNewStage = stage;
		
		fillComboBoxes();
		checkParameters(parameters);
		validateTextFields();
		watchEvents();
	}
	
	private <T> void checkParameters(HashMap<String, T> parameters) {
		if (parameters != null) {
			this.employee = (Employee) parameters.get(EMPLOYEE_KEY);
			updateTextFields();
		}
	}

	private void updateTextFields() {
		
		
		WindowsUtils.setTextInTextField(cpfTextField, employee.getCpf());
		
		if (employee.getFones() != null) {
			WindowsUtils.setTextInTextField(residentialPhoneTextField, employee.getFones().get(0).getNumber());
			WindowsUtils.setTextInTextField(cellPhoneTextField, employee.getFones().get(1).getNumber());
		}
		
		if (employee.getAddres() != null) {
			WindowsUtils.setTextInTextField(cepTextField, employee.getAddres().getCep());
			WindowsUtils.setTextInTextField(streetTextField, employee.getAddres().getStreet());
			WindowsUtils.setTextInTextField(numberTextField, employee.getAddres().getNumber());
			WindowsUtils.setTextInTextField(districtTextField, employee.getAddres().getSuburb());
			WindowsUtils.setTextInTextField(complementTextField, employee.getAddres().getComplement());
			WindowsUtils.setTextInTextField(cityTextField, employee.getAddres().getCity());
			WindowsUtils.setTextInTextField(stateTextField, employee.getAddres().getState());
			
			WindowsUtils.setSelectedComboBoxItem(countryComboBox, employee.getAddres().getCountry());
		}
		
		if (employee.getUser() != null) {
			WindowsUtils.setSelectedComboBoxItem(roleComboBox, employee.getUser().getRoles().get(0));
			WindowsUtils.setTextInTextField(nameTextField, employee.getUser().getName());
			WindowsUtils.setTextInTextField(emailTextField, employee.getUser().getEmail());
			WindowsUtils.setTextInTextField(passwordTextField, employee.getUser().getPassword());
		}
		
	}

	private void validateTextFields() {
		ValidatorUtils.addRequiredValidator(nameTextField, "Employee Name is Required!");
		ValidatorUtils.addRequiredValidator(emailTextField, "E-mail is Required!");
		ValidatorUtils.addRequiredValidator(cpfTextField, "CPF is Required!");
		ValidatorUtils.addRequiredValidator(passwordTextField, "Password is Required!");
		ValidatorUtils.addRequiredValidator(confirmPasswordTextField, "Confirm Password is Required!");
		ValidatorUtils.addPasswordAndConfirmPasswordValidator(passwordTextField, confirmPasswordTextField, "Password does not match the confirm password");
		
		ValidatorUtils.addNumberOnlyValidator(numberTextField);
		ValidatorUtils.addNumberOnlyValidator(cpfTextField);
		ValidatorUtils.addNumberOnlyValidator(residentialPhoneTextField);
		ValidatorUtils.addNumberOnlyValidator(cellPhoneTextField);
		
		ValidatorUtils.addMaxLengthValidator(cpfTextField, 11);
		ValidatorUtils.addMaxLengthValidator(cepTextField, 8);
		
		ValidatorUtils.addEmailValidator(emailTextField, "Email does not match");
		
		ValidatorUtils.addDuplicateUserValidator(emailTextField, "An account for the specified email address already exists", userService);
		
		WindowsUtils.validateTextField(numberTextField);
		WindowsUtils.validateTextField(residentialPhoneTextField);
		WindowsUtils.validateTextField(cellPhoneTextField);
		WindowsUtils.validateTextField(nameTextField);
		WindowsUtils.validateTextField(emailTextField);
		WindowsUtils.validateTextField(cpfTextField);
		WindowsUtils.validateTextField(passwordTextField);
		WindowsUtils.validateTextField(confirmPasswordTextField);
	}
	
	private void fillComboBoxes() {
		WindowsUtils.addComboBoxItens(roleComboBox, roleService.findAll());
	}
	
	private void watchEvents() {
		WindowsUtils.watchEvents(nameTextField, v -> watch());
		WindowsUtils.watchEvents(emailTextField, v -> watch());
		WindowsUtils.watchEvents(cpfTextField, v -> watch());
		WindowsUtils.watchEvents(passwordTextField, v -> watch());
		WindowsUtils.watchEvents(confirmPasswordTextField, v -> watch());
	}
	
	private void watch() {
		if (isRequiredTextFieldsFilled() && (passwordTextField.validate() && confirmPasswordTextField.validate())) {
			saveButton.setDisable(false);
		} else {
			saveButton.setDisable(true);
		}
		
	}
	
	@FXML
	public void onSave() {
		
		Role roleSelected = (Role) WindowsUtils.getSelectedComboBoxItem(roleComboBox);
		List<Role> role = roleService.findByRole(roleSelected.getRole());
		
		User user = EntityFactory.createUser(WindowsUtils.getTextFromTextField(emailTextField), 
											 WindowsUtils.getTextFromTextField(nameTextField), 
											 null, 
											 WindowsUtils.getTextFromTextField(passwordTextField), 
											 role);
		
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
		
		List<Fone> phones = Arrays.asList(EntityFactory.createPhone(WindowsUtils.getLongFromTextField(cellPhoneTextField)),
										 EntityFactory.createPhone(WindowsUtils.getLongFromTextField(residentialPhoneTextField)));
		
		try {
			employeeService.save(EntityFactory.createEmployee(employee, WindowsUtils.getTextFromTextField(cpfTextField), 
															  user, address, phones), e -> {
																	WindowsUtils.createDefaultDialog(container, "Sucess", "Employee save with sucess.", () -> { employeeNewStage.close(); });
																}, null);
		} catch (Exception e) {
			e.printStackTrace();
			WindowsUtils.createDefaultDialog(container, "Error", "Error saving employee, try again.", () -> {});
		}
		
	}
	
	private boolean isAddressFilled() {
		return  !WindowsUtils.isTextFieldEmpty(streetTextField) ||
				!WindowsUtils.isTextFieldEmpty(numberTextField) ||
				!WindowsUtils.isTextFieldEmpty(districtTextField) ||
				!WindowsUtils.isTextFieldEmpty(complementTextField) ||
				!WindowsUtils.isTextFieldEmpty(cityTextField) ||
				!WindowsUtils.isTextFieldEmpty(stateTextField) ||
				WindowsUtils.isComboBoxSelected(roleComboBox);
	}
	
	private boolean isRequiredTextFieldsFilled() {
		return  !(WindowsUtils.isTextFieldEmpty(nameTextField)) &&
				!(WindowsUtils.isTextFieldEmpty(emailTextField)) &&
				!(WindowsUtils.isTextFieldEmpty(cpfTextField)) &&
				!(WindowsUtils.isTextFieldEmpty(passwordTextField)) &&
				!(WindowsUtils.isTextFieldEmpty(confirmPasswordTextField));
	}
	
	@FXML
	public void onCancel() {
		employeeNewStage.close();
	}
	
	@FXML
	public void onHelp() {
		
	}

}
