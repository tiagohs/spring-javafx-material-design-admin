package com.tiagohs.validators;

import javafx.scene.control.TextField;

public class MaxLengthValidator {
	
	private TextField textField;
	
	public MaxLengthValidator(TextField textField) {
		this.textField = textField;
	}
	
	public void validate(int maxLength) {
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
		    
			if (newValue.length() > maxLength) {
				textField.setText(oldValue);
			}
			
		});
	}
}
