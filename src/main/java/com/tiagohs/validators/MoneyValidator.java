package com.tiagohs.validators;

import javafx.scene.control.TextField;

public class MoneyValidator {
	private static final String REGEX = "^[+-]?[0-9]{1,3}(?:[0-9]*(?:[.,][0-9]{2})?|(?:,[0-9]{3})*(?:\\.[0-9]{2})?|(?:\\.[0-9]{3})*(?:,[0-9]{2})?)$";
	
	private TextField textField;
	
	public MoneyValidator(TextField textField) {
		this.textField = textField;
	}
	
	public void validate() {
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
		    
			if (newValue.trim().isEmpty()) {
				return;
			}
			
			if (!newValue.matches(REGEX)) {
				textField.setText(oldValue);
			}
			
		});
	}
	
}
