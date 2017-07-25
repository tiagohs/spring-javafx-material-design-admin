package com.tiagohs.util;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import com.tiagohs.validators.EmailValidator;
import com.tiagohs.validators.MaxLengthValidator;
import com.tiagohs.validators.NumberValidator;
import com.tiagohs.validators.PasswordAndConfirmPasswordValidator;

import javafx.scene.control.TextField;

public class ValidatorUtils {
	
	public static RequiredFieldValidator addRequiredValidator(JFXTextField textField, String message) {
		return (RequiredFieldValidator) addValidator(new RequiredFieldValidator(), textField, message);
	}
	
	public static RequiredFieldValidator addRequiredValidator(JFXPasswordField textField, String message) {
		return (RequiredFieldValidator) addValidator(new RequiredFieldValidator(), textField, message);
	}
	
	public static EmailValidator addEmailValidator(JFXTextField textField, String message) {
		return (EmailValidator) addValidator(new EmailValidator(), textField, message);
	}
	
	public static PasswordAndConfirmPasswordValidator addPasswordAndConfirmPasswordValidator(JFXPasswordField passwordTextField, JFXPasswordField confirmPasswordTextField, String message) {
		PasswordAndConfirmPasswordValidator passwordAndConfirmPasswordValidator = new PasswordAndConfirmPasswordValidator(passwordTextField, confirmPasswordTextField);
		
		passwordAndConfirmPasswordValidator.setMessage(message);
		
		passwordTextField.getValidators().add(passwordAndConfirmPasswordValidator);
		confirmPasswordTextField.getValidators().add(passwordAndConfirmPasswordValidator);
		
		return passwordAndConfirmPasswordValidator;
	}
	
	public static NumberValidator addNumberOnlyValidator(TextField textField) {
		NumberValidator numberValidator = new NumberValidator(textField);
		numberValidator.validate();
		
		return numberValidator;
	}
	
	public static MaxLengthValidator addMaxLengthValidator(TextField textField, int maxLength) {
		MaxLengthValidator maxLengthValidator = new MaxLengthValidator(textField);
		maxLengthValidator.validate(maxLength);
		
		return maxLengthValidator;
	}
	
	private static ValidatorBase addValidator(ValidatorBase validatorBase, JFXTextField textField, String message) {
		
		validatorBase.setMessage(message);
		textField.getValidators().add(validatorBase);
		
		return validatorBase;
	}
	
	private static ValidatorBase addValidator(ValidatorBase validatorBase, JFXPasswordField textField, String message) {
		
		if (message != null) {
			validatorBase.setMessage(message);
		}
		
		textField.getValidators().add(validatorBase);
		
		return validatorBase;
	}
	
	

}
