package com.tiagohs.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXTextField;

import io.datafx.controller.FXMLController;
import javafx.fxml.FXML;

@FXMLController(value = "/fxml/new_product.fxml", title = "New Product")
public class ProductNewController {
	
	@FXML
	private JFXTextField productNameTextField;
	
	@PostConstruct
	public void init() {
		
		productNameTextField.focusedProperty().addListener((o, oldValue, newValue) -> {
			if (!newValue) {
				productNameTextField.validate();
			}
		});
	}
}
