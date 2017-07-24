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
	
	public static final String PATH_FXML = "/fxml/inventory.fxml";
	public static final String TITLE = "Inventory - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private JFXTreeTableView<ProductTableDTO> productsTable;
	
	
	public void init(Stage stage) {
		
	}
	
	@FXML
	public void onNewProduct() throws Exception {
		WindowsUtils.openNewWindow(ProductNewController.PATH_FXML, ProductNewController.TITLE, ProductNewController.PATH_ICON, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewEmployee() throws Exception {
		WindowsUtils.openNewWindow(EmployeeNewController.PATH_FXML, EmployeeNewController.TITLE, EmployeeNewController.PATH_ICON, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewSupplier() throws Exception {
		WindowsUtils.openNewWindow(SupplierNewController.PATH_FXML, SupplierNewController.TITLE, SupplierNewController.PATH_ICON, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewBrand() throws Exception {
		WindowsUtils.openNewWindow(BrandNewController.PATH_FXML, BrandNewController.TITLE, BrandNewController.PATH_ICON, Modality.APPLICATION_MODAL);
	}
}
