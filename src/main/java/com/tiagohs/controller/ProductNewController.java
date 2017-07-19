package com.tiagohs.controller;

import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.stage.Stage;

@Controller
public class ProductNewController implements BaseController {
	
	@FXML
	private JFXTextField productNameTextField;
	
	public void init(Stage stage) {
		
		productNameTextField.focusedProperty().addListener((o, oldValue, newValue) -> {
			if (!newValue) {
				productNameTextField.validate();
			}
		});
	}
}
