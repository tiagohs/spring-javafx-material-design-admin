package com.tiagohs.validators;

import com.jfoenix.validation.base.ValidatorBase;
import com.tiagohs.service.UserService;

import javafx.beans.DefaultProperty;
import javafx.scene.control.TextInputControl;

@DefaultProperty(value = "icon")
public class DuplicateUserValidator extends ValidatorBase {
	
	private UserService userService;
	
	public DuplicateUserValidator(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	protected void eval() {
		TextInputControl textField = (TextInputControl) srcControl.get();
        if (!isEmpty(textField.getText())) {
        	validateEmail(textField.getText());
        } 
	}
	
	private void validateEmail(String email) {
		
		userService.findUserByEmail(email, e -> {
			if (e.getSource().getValue() != null) {
	            hasErrors.set(true);
	        } else {
	            hasErrors.set(false);
	        }
		}, null);
	}
	
	private boolean isEmpty(String text) {
		return text == null || text.isEmpty();
	}

}
