package com.tiagohs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXDatePicker;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
	
	private List<ItemBaseController> itemsControllers;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		this.itemsControllers = new ArrayList<>();
		
		shipmentDateDatePicker.setDialogParent(container);
		issueDateDatePicker.setDialogParent(container);
		
		itemsContainer.getChildren().add(createPane());
	}
	
	@FXML
	private void onAddAnotherItem() {
		itemsContainer.getChildren().add(createPane());
		paneBottomItems.setLayoutY(paneBottomItems.getLayoutY() + 63.0);
	}
	
	private Pane createPane() {
		Scene scene = null;
		
		try {
			FXMLLoader loader = WindowsUtils.loadFxml("/fxml/item_base.fxml");
			scene = new Scene(loader.load());
			ItemBaseController controller = (ItemBaseController) loader.getController();
			controller.init(this);
			
			itemsControllers.add(controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return (Pane) scene.lookup("#itemContainer");
	}
	
	public void onUpdateTotal(double total) {
		
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
