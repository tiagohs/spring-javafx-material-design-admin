package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXDatePicker;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@Controller
public class SalesNewController implements BaseController {

	public static final String PATH_FXML = "/fxml/new_sale_order.fxml";
	public static final String TITLE = "New Sale - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
    private StackPane container;
	
    @FXML
    private JFXDatePicker shipmentDateDatePicker;
    
    @FXML
    private JFXDatePicker issueDateDatePicker;
    
	@FXML
	private Pane itemsContainer;
	
	@FXML
	private Pane itemContainer;
	
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		
		shipmentDateDatePicker.setDialogParent(container);
		issueDateDatePicker.setDialogParent(container);
	}
	
	@FXML
	private void onAddAnotherItem() {
		itemsContainer.getChildren().add(1, itemContainer);
	}
	
	@FXML
	public void onSave() {
		
	}
	
	@FXML
	public void onCancel() {
		
	}
	
	@FXML
	public void onHelp() {
		
	}
}
