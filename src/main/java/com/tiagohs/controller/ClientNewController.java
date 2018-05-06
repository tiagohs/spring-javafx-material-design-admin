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
import com.tiagohs.model.Client;
import com.tiagohs.model.ClientType;
import com.tiagohs.model.Fone;
import com.tiagohs.model.Role;
import com.tiagohs.model.User;
import com.tiagohs.service.ClientService;
import com.tiagohs.service.RoleService;
import com.tiagohs.service.UserService;
import com.tiagohs.util.EntityFactory;
import com.tiagohs.util.ValidatorUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@Controller
public class ClientNewController extends BaseController {
	
	public static final String PRODUCT_KEY = "client_key";
	public static final String PATH_FXML = "/fxml/new_client.fxml";
	public static final String NEW_CLIENT_TITLE_KEY = "new_client.title";
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
	private JFXComboBox<ClientType> clientTypeComboBox;
	
	@FXML
	private JFXButton saveButton;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UserService userService;
	
	private Client client;
	
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		fillComboBoxes();
		checkParameters(parameters);
		validateTextFields();
		watchEvents();
	}

	@Override
	protected void onClose() {
		roleService.onClose();
		clientService.onClose();
		userService.onClose();
	}
	
	private <T> void checkParameters(HashMap<String, T> parameters) {
		if (parameters != null) {
			this.client = (Client) parameters.get(PRODUCT_KEY);
			updateTextFields();
		}
	}
	
	private void updateTextFields() {
		
		WindowsUtils.setTextInTextField(cpfTextField, client.getCpf());
		WindowsUtils.setSelectedComboBoxItem(clientTypeComboBox, client.getClientType());
		
		if (client.getPhones() != null) {
			WindowsUtils.setTextInTextField(residentialPhoneTextField, client.getPhones().get(0).getNumber());
			WindowsUtils.setTextInTextField(cellPhoneTextField, client.getPhones().get(1).getNumber());
		}
		
		if (client.getAddress() != null) {
			WindowsUtils.setTextInTextField(cepTextField, client.getAddress().getCep());
			WindowsUtils.setTextInTextField(streetTextField, client.getAddress().getStreet());
			WindowsUtils.setTextInTextField(numberTextField, client.getAddress().getNumber());
			WindowsUtils.setTextInTextField(districtTextField, client.getAddress().getSuburb());
			WindowsUtils.setTextInTextField(complementTextField, client.getAddress().getComplement());
			WindowsUtils.setTextInTextField(cityTextField, client.getAddress().getCity());
			WindowsUtils.setTextInTextField(stateTextField, client.getAddress().getState());
			
			WindowsUtils.setSelectedComboBoxItem(countryComboBox, client.getAddress().getCountry());
		}
		
		if (client.getUser() != null) {
			WindowsUtils.setSelectedComboBoxItem(roleComboBox, client.getUser().getRoles().get(0));
			WindowsUtils.setTextInTextField(nameTextField, client.getUser().getName());
			WindowsUtils.setTextInTextField(emailTextField, client.getUser().getEmail());
			WindowsUtils.setTextInTextField(passwordTextField, client.getUser().getPassword());
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
		WindowsUtils.addComboBoxItens(roleComboBox, roleService);
		WindowsUtils.addComboBoxItens(clientTypeComboBox, 
				Arrays.asList(
						new ClientType(1L, "Pessoa"),
						new ClientType(2L, "Empresa"),
						new ClientType(3L, "Outros")));
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
	
	@SuppressWarnings("unchecked")
	@FXML
	public void onSave() {
		ClientType clientType = WindowsUtils.getSelectedComboBoxItem(clientTypeComboBox);
		Role roleSelected = WindowsUtils.getSelectedComboBoxItem(roleComboBox);
		List<Fone> phones = Arrays.asList(EntityFactory.createPhone(WindowsUtils.getLongFromTextField(cellPhoneTextField)),
										 EntityFactory.createPhone(WindowsUtils.getLongFromTextField(residentialPhoneTextField)));
		
		roleService.findByRole(roleSelected.getRole(), e -> {
			List<Role> role = (List<Role>) e.getSource().getValue();
			
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
													  WindowsUtils.getSelectedComboBoxItem(countryComboBox), 
													  WindowsUtils.getTextFromTextField(cepTextField));
			}
			
			try {
				clientService.save(EntityFactory.createClient(client, WindowsUtils.getTextFromTextField(cpfTextField), 
													  clientType, address, phones, user), en -> {
															WindowsUtils.createDefaultDialog(container, "Sucess", "Client save with sucess.", () -> { stage.close(); });
														}, null);
			
			} catch (Exception error) {
				error.printStackTrace();
				WindowsUtils.createDefaultDialog(container, "Error", "Error saving client, try again.", () -> {});
			}
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
