package com.tiagohs.controller;

import org.springframework.stereotype.Controller;

import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Controller
public class SalesController implements BaseController {

	@Override
	public void init(Stage stage) {
		
	}
	
	@FXML
	public void onNewSale() throws Exception {
		WindowsUtils.openNewWindow("/fxml/new_sale_order.fxml", "New Sales Order - Inventory Management", Modality.APPLICATION_MODAL);
	}
}
