package com.tiagohs.util;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.scene.control.TextField;

public class ValidatorUtils {
	
	public static RequiredFieldValidator addRequiredValidator(JFXTextField textField, String message) {
		RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
		
		requiredFieldValidator.setMessage(message);
		textField.getValidators().add(requiredFieldValidator);
		
		return requiredFieldValidator;
	}
	
	public static PasswordAndConfirmPasswordValidator addPasswordAndConfirmPasswordValidator(JFXTextField passwordTextField, JFXTextField confirmPasswordTextField, String message) {
		PasswordAndConfirmPasswordValidator passwordAndConfirmPasswordValidator = new PasswordAndConfirmPasswordValidator(passwordTextField, confirmPasswordTextField);
		
		passwordAndConfirmPasswordValidator.setMessage(message);
		
		passwordTextField.getValidators().add(passwordAndConfirmPasswordValidator);
		confirmPasswordTextField.getValidators().add(passwordAndConfirmPasswordValidator);
		
		return passwordAndConfirmPasswordValidator;
	}
	
	public static void addNumberOnlyValidator(TextField textField) {
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
		    
			if (!newValue.matches("[0-9]*")) {
				textField.setText("");
			}
			
		});
	}
	

}
