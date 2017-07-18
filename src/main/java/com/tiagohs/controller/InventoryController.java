package com.tiagohs.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXTreeTableView;
import com.tiagohs.model.ProductTableDTO;

import io.datafx.controller.FXMLController;
import javafx.fxml.FXML;

@FXMLController(value = "/fxml/inventory.fxml", title = "Inventory - Inventory Management")
public class InventoryController {
	
	@FXML
	private JFXTreeTableView<ProductTableDTO> productsTable;
	
	
	
	@PostConstruct
	private void init() {
		
	}
}
