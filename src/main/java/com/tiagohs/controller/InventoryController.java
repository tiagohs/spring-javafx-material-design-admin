package com.tiagohs.controller;

import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXTreeTableView;
import com.tiagohs.model.dto.ProductTableDTO;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.stage.Modality;
import javafx.stage.Stage;


@Controller
public class InventoryController implements BaseController {
	
	@FXML
	private JFXTreeTableView<ProductTableDTO> productsTable;
	
	
	public void init(Stage stage) {
		
	}
	
	@FXML
	public void onNewProduct() throws Exception {
		WindowsUtils.openNewWindow("/fxml/new_product.fxml", "New Product - Inventory Management", Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewEmployee() throws Exception {
		WindowsUtils.openNewWindow("/fxml/new_employee.fxml", "New Employee - Inventory Management", Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewSupplier() throws Exception {
		WindowsUtils.openNewWindow("/fxml/new_supplier.fxml", "New Supplier - Inventory Management", Modality.APPLICATION_MODAL);
	}
}
