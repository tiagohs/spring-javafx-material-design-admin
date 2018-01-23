package com.tiagohs.validators;

import com.jfoenix.validation.base.ValidatorBase;

import javafx.beans.DefaultProperty;
import javafx.scene.control.TextInputControl;

@DefaultProperty(value = "icon")
public class EmailValidator extends ValidatorBase {
	private static final String REGEX = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+";
	
	@Override
	protected void eval() {
		TextInputControl textField = (TextInputControl) srcControl.get();
		
		if (textField.getText().matches(REGEX)) {
			hasErrors.set(false);
		} else {
			hasErrors.set(true);
		}
		
	}

}
