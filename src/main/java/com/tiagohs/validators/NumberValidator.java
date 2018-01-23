package com.tiagohs.validators;

import javafx.scene.control.TextField;

public class NumberValidator {
	private static final String REGEX = "[0-9]*";
	
	private TextField textField;
	
	public NumberValidator(TextField textField) {
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
