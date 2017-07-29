package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
	private VBox itemsContainer;
	
	@FXML
	private Pane paneBottomItems;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		
		shipmentDateDatePicker.setDialogParent(container);
		issueDateDatePicker.setDialogParent(container);
	}
	
	@FXML
	private void onAddAnotherItem() {
		itemsContainer.getChildren().add(createPane());
		paneBottomItems.setLayoutY(paneBottomItems.getLayoutY() + 63.0);
	}
	
	private Pane createPane() {
		VBox pane = new VBox();
		
		pane.setPrefWidth(709.0);
		pane.setPrefHeight(61.0);
		pane.setScaleX(1.0);
		pane.setScaleY(1.0);
		pane.setScaleZ(1.0);
		pane.setStyle("-fx-background-color: #336699;");
		
		JFXComboBox<String> item = new JFXComboBox<>();
		
		item.setPrefWidth(202.0);
		item.setPrefHeight(25.0);
		
		item.setLayoutX(14);
		item.setLayoutY(20);
		
		pane.getChildren().add(item);
		
		return pane;
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
