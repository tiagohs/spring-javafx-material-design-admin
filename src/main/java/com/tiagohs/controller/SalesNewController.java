package com.tiagohs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.tiagohs.model.Client;
import com.tiagohs.model.Fone;
import com.tiagohs.model.Item;
import com.tiagohs.model.Sale;
import com.tiagohs.service.ClientService;
import com.tiagohs.service.SaleService;
import com.tiagohs.util.EntityFactory;
import com.tiagohs.util.ValidatorUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@Controller
public class SalesNewController implements BaseController {
	
	public static final String SALE_KEY = "sale_key";
	public static final String PATH_FXML = "/fxml/new_sale_order.fxml";
	public static final String TITLE = "New Sale - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
    private StackPane container;
	
	@FXML
	private JFXTextField emailTextField;
	
	@FXML
	private JFXTextField phoneTextField;
	
	@FXML
	private JFXTextField referenceTextField;
	
	@FXML
	private JFXTextArea messageTextArea;
	
	@FXML
	private Label saleCode;
	
	@FXML
	private Label totalUnitsLabel;
	
	@FXML
	private Label totalLabel;
	
	@FXML
	private JFXComboBox<Client> clientComboBox;
	
    /*@FXML
    private JFXDatePicker shipmentDateDatePicker;
    
    @FXML
    private JFXDatePicker issueDateDatePicker;*/
    
	@FXML
	private Button saveButton;
	
	@FXML
	private VBox itemsContainer;
	
	@FXML
	private Pane paneBottomItems;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private SaleService saleService;
	
	private int numUnits;
	private double total;
	
	private List<ItemBaseController> itemsControllers;
	private List<Item> items;
	private Sale sale;
	private Stage saleStage;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		this.saleStage = stage;
		this.itemsControllers = new ArrayList<>();
		this.items = new ArrayList<>();
		this.total = 0;
		
		/*shipmentDateDatePicker.setDialogParent(container);
		issueDateDatePicker.setDialogParent(container);*/
		
		saleCode.setText("S" + RandomStringUtils.randomAlphanumeric(5));
		itemsContainer.getChildren().add(createPane(null));
		
		checkParameters(parameters);
		validateTextFields();
		watchEvents();
		fillComboBoxes();
	}
	
	private void fillComboBoxes() {
		WindowsUtils.addComboBoxItens(clientComboBox, clientService.findAll());
	}

	private void validateTextFields() {
		
		ValidatorUtils.addRequiredValidator(emailTextField, "E-mail is Required!");
		
		ValidatorUtils.addEmailValidator(emailTextField, "Email does not match");
		
		WindowsUtils.validateTextField(emailTextField);
		
	}
	
	private void watchEvents() {
		WindowsUtils.watchEvents(emailTextField, v -> watch());
		WindowsUtils.watchEvents(clientComboBox, v -> watch());
	}
	
	private void watch() {
		if (!(WindowsUtils.isTextFieldEmpty(emailTextField)) && 
			!WindowsUtils.isComboBoxSelected(clientComboBox)) {
			saveButton.setDisable(false);
		} else {
			saveButton.setDisable(true);
		}
		
	}
	
	private <T> void checkParameters(HashMap<String, T> parameters) {
		if (parameters != null) {
			this.sale = (Sale) parameters.get(SALE_KEY);
			updateTextFields();
		}
	}
	
	private void updateTextFields() {
		
		WindowsUtils.setTextInLabel(saleCode, sale.getSaleCode());
		WindowsUtils.setTextInLabel(totalUnitsLabel, sale.getTotalUnits());
		WindowsUtils.setTextInLabel(totalLabel, sale.getTotal());
		
		WindowsUtils.setTextInTextField(emailTextField, sale.getEmail());
		WindowsUtils.setTextInTextField(referenceTextField, sale.getReference());
		WindowsUtils.setTextInTextArea(messageTextArea, sale.getMessage());
		
		if (sale.getFone() != null) {
			WindowsUtils.setTextInTextField(phoneTextField, sale.getFone().getNumber());
		}
		
		if (sale.getCliente() != null) {
			WindowsUtils.setSelectedComboBoxItem(clientComboBox, sale.getCliente());
		}
		
		if (sale.getItems() != null) {
			updateItems();
		}
		
		
	}
	
	private void updateItems() {
		
		sale.getItems().forEach(item -> {
			HashMap<String, Item> parameters = new HashMap<String, Item>();
			parameters.put(ItemBaseController.ITEM_KEY, item);
			
			itemsContainer.getChildren().add(createPane(parameters));
		});
	}

	@FXML
	private void onAddAnotherItem() {
		itemsContainer.getChildren().add(createPane(null));
		paneBottomItems.setLayoutY(paneBottomItems.getLayoutY() + 63.0);
	}
	
	private Pane createPane(HashMap<String, Item> parameters) {
		Scene scene = null;
		
		try {
			FXMLLoader loader = WindowsUtils.loadFxml("/fxml/item_base.fxml");
			scene = new Scene(loader.load());
			ItemBaseController controller = (ItemBaseController) loader.getController();
			controller.init(this, parameters);
			
			itemsControllers.add(controller);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return (Pane) scene.lookup("#itemContainer");
	}
	
	public void updateValues() {
		this.total = 0;
		this.numUnits = 0;
		
		itemsControllers.forEach(controller -> {
			this.total += controller.getTotal();
			this.numUnits += controller.getQuantity();
		});
		
		totalLabel.setText(String.format("%.2f", this.total));
		totalUnitsLabel.setText(Integer.toString(this.numUnits));
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}
	
	
	@FXML
	public void onSave() {
		
		if (sale == null)
			sale = new Sale();
		
		Fone phone = null;
		if (!WindowsUtils.isTextFieldEmpty(phoneTextField)) {
			phone = EntityFactory.createPhone(WindowsUtils.getIntegerFromTextField(phoneTextField));
		}
		
		try {
			saleService.save(EntityFactory.createSale(sale, 
					WindowsUtils.getTextFromLabel(saleCode), 
					null, 
					null, 
					WindowsUtils.getTextFromTextField(referenceTextField), 
					WindowsUtils.getTextFromTextField(emailTextField), 
					WindowsUtils.getTextFromTextArea(messageTextArea), 
					"Em Processo", 
					Double.valueOf(total), 
					Double.valueOf(numUnits), 
					phone, 
					(Client) WindowsUtils.getSelectedComboBoxItem(clientComboBox), 
					items, 
					null));
			WindowsUtils.createDefaultDialog(container, "Sucess", "Client save with sucess.", () -> { saleStage.close(); });
		} catch (Exception e) {
			e.printStackTrace();
			WindowsUtils.createDefaultDialog(container, "Error", "Error saving client, try again.", () -> {});
		}
		
	}
	
	@FXML
	public void onCancel() {
		saleStage.close();
	}
	
	@FXML
	public void onHelp() {
		
	}
}
