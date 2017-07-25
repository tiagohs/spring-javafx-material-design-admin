package com.tiagohs.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.tiagohs.model.Address;
import com.tiagohs.model.Employee;
import com.tiagohs.model.Fone;
import com.tiagohs.model.Role;
import com.tiagohs.model.User;
import com.tiagohs.service.EmployeeService;
import com.tiagohs.service.RoleService;
import com.tiagohs.util.ValidatorUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@Controller
public class EmployeeNewController implements BaseController {

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
	private JFXTextField passwordTextField;

	@FXML
	private JFXTextField confirmPasswordTextField;

	@FXML
	private JFXComboBox<String> countryComboBox;

	@FXML
	private JFXComboBox<Role> roleComboBox;
	
	private Stage employeeNewStage;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public void init(Stage stage) {
		this.employeeNewStage = stage;
		
		validateTextFields();
		fillComboBoxes();
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
		
		WindowsUtils.validateTextField(nameTextField);
		WindowsUtils.validateTextField(emailTextField);
		WindowsUtils.validateTextField(cpfTextField);
		WindowsUtils.validateTextField(passwordTextField);
		WindowsUtils.validateTextField(confirmPasswordTextField);
	}
	
	private void fillComboBoxes() {
		WindowsUtils.addComboBoxItens(roleComboBox, roleService.findAll());
	}
	
	@FXML
	public void onSave() {
		Employee employee = new Employee();
		
		User user = new User();
		user.setName(WindowsUtils.getTextFromTextField(nameTextField));
		user.setEmail(WindowsUtils.getTextFromTextField(emailTextField));
		user.setPassword(WindowsUtils.getTextFromTextField(passwordTextField));
		user.setRoles(Arrays.asList((Role) WindowsUtils.getSelectedComboBoxItem(roleComboBox)));
		
		Address adress = null;
		
		if (isAddressFilled()) {
			adress = new Address();
			adress.setStreet(WindowsUtils.getTextFromTextField(streetTextField));
			adress.setNumber(WindowsUtils.getIntegerFromTextField(numberTextField));
			adress.setSuburb(WindowsUtils.getTextFromTextField(districtTextField));
			adress.setComplement(WindowsUtils.getTextFromTextField(complementTextField));
			adress.setCity(WindowsUtils.getTextFromTextField(cityTextField));
			adress.setState(WindowsUtils.getTextFromTextField(stateTextField));
			adress.setCountry((String) WindowsUtils.getSelectedComboBoxItem(roleComboBox));
		}
		
		Fone fone = new Fone();
		fone.setNumber(WindowsUtils.getIntegerFromTextField(cellPhoneTextField));
		fone.setNumber(WindowsUtils.getIntegerFromTextField(residentialPhoneTextField));
		
		employee.setUser(user);
		employee.setAddres(adress);
		employee.setFones(Arrays.asList(fone));
		employee.setCpf(WindowsUtils.getTextFromTextField(cpfTextField));
		
		try {
			employeeService.save(employee);
			WindowsUtils.createDefaultDialog(container, "Sucess", "Employee save with sucess.", () -> { employeeNewStage.close(); });
		} catch (Exception e) {
			WindowsUtils.createDefaultDialog(container, "Error", "Error saving employee, try again.", () -> {});
		}
		
	}
	
	private boolean isAddressFilled() {
		return  WindowsUtils.isTextFieldEmpty(streetTextField) ||
				WindowsUtils.isTextFieldEmpty(numberTextField) ||
				WindowsUtils.isTextFieldEmpty(districtTextField) ||
				WindowsUtils.isTextFieldEmpty(complementTextField) ||
				WindowsUtils.isTextFieldEmpty(cityTextField) ||
				WindowsUtils.isTextFieldEmpty(stateTextField) ||
				WindowsUtils.isComboBoxSelected(roleComboBox);
	}
	
	@FXML
	public void onCancel() {
		
	}
	
	@FXML
	public void onHelp() {
		
	}

}
