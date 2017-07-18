package com.tiagohs.controller;

import javax.annotation.PostConstruct;

import com.jfoenix.controls.JFXTreeTableView;
import com.tiagohs.model.ProductTableDTO;
import com.tiagohs.util.WindowsUtils;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import javafx.fxml.FXML;

@FXMLController(value = "/fxml/inventory.fxml", title = "Inventory - Inventory Management")
public class InventoryController {
	
	@FXML
	private JFXTreeTableView<ProductTableDTO> productsTable;
	
	
	
	@PostConstruct
	private void init() {
		
	}
	
	@FXML
	public void onNewProduct() throws FlowException {
		WindowsUtils.openNewWindow(ProductNewController.class);
	}
}
