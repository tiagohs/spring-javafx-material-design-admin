package com.tiagohs.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
public class SalesNewController extends BaseController {
	
	public static final String SALE_KEY = "sale_key";
	public static final String PATH_FXML = "/fxml/new_sale_order.fxml";
	public static final String NEW_SALE_TITLE_KEY = "new_sale.title";
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
	
	@FXML
	private JFXComboBox<String> stateComboBox;
	
    @FXML
    private JFXDatePicker shipmentDateDatePicker;
    
    @FXML
    private JFXDatePicker issueDateDatePicker;
    
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
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		this.itemsControllers = new ArrayList<>();
		this.items = new ArrayList<>();
		this.total = 0;
		
		shipmentDateDatePicker.setDialogParent(container);
		issueDateDatePicker.setDialogParent(container);
		
		saleCode.setText("S" + RandomStringUtils.randomAlphanumeric(5));
		
		fillComboBoxes();
		checkParameters(parameters);
		validateTextFields();
		watchEvents();
	}
	
	@Override
	protected void onClose() {
		clientService.onClose();
		saleService.onClose();
	}
	
	private void fillComboBoxes() {
		WindowsUtils.addComboBoxItens(clientComboBox, clientService);
	}

	private void validateTextFields() {
		
		ValidatorUtils.addRequiredValidator(emailTextField, "E-mail is Required!");
		ValidatorUtils.addNumberOnlyValidator(phoneTextField);
		ValidatorUtils.addEmailValidator(emailTextField, "Email does not match");
		WindowsUtils.validateTextField(emailTextField);
	}
	
	private void watchEvents() {
		WindowsUtils.watchEvents(emailTextField, v -> watch());
		WindowsUtils.watchEvents(clientComboBox, v -> watch());
		
		WindowsUtils.onComboBoxItemSelected(clientComboBox, item -> onSelectClient(item));
	}
	
	private void watch() {
		if (!(WindowsUtils.isTextFieldEmpty(emailTextField)) && 
			!WindowsUtils.isComboBoxSelected(clientComboBox)) {
			saveButton.setDisable(false);
		} else {
			saveButton.setDisable(true);
		}
	}
	
	public void onSelectClient(Client client) {
		
		if (client != null) {
			WindowsUtils.setTextInTextField(emailTextField, client.getUser().getEmail());
			
			if (!client.getPhones().isEmpty()) {
				WindowsUtils.setTextInTextField(phoneTextField, client.getPhones().get(0).getNumber());
			}
		}
	}
	
	private <T> void checkParameters(HashMap<String, T> parameters) {
		if (parameters != null) {
			this.sale = (Sale) parameters.get(SALE_KEY);
			updateTextFields();
		} else {
			this.sale = null;
			addPane(null);
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
			
			addPane(parameters);
		});
	}

	@FXML
	private void onAddAnotherItem() {
		addPane(null);
	}
	
	private void addPane(HashMap<String, Item> parameters) {
		itemsContainer.getChildren().add(createPane(parameters));
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
			phone = EntityFactory.createPhone(WindowsUtils.getLongFromTextField(phoneTextField));
		}
		
		try {
			saleService.save(EntityFactory.createSale(sale, 
					WindowsUtils.getTextFromLabel(saleCode), 
					convertToCalendar(shipmentDateDatePicker.getValue()), 
					convertToCalendar(issueDateDatePicker.getValue()), 
					WindowsUtils.getTextFromTextField(referenceTextField), 
					WindowsUtils.getTextFromTextField(emailTextField), 
					WindowsUtils.getTextFromTextArea(messageTextArea), 
					(String) WindowsUtils.getSelectedComboBoxItem(stateComboBox), 
					Integer.valueOf(numUnits), 
					Double.valueOf(total), 
					phone, 
					(Client) WindowsUtils.getSelectedComboBoxItem(clientComboBox), 
					items, 
					null), e -> {
						WindowsUtils.createDefaultDialog(container, "Sucess", "Client save with sucess.", () -> { stage.close(); });
					}, null);
		} catch (Exception e) {
			e.printStackTrace();
			WindowsUtils.createDefaultDialog(container, "Error", "Error saving client, try again.", () -> {});
		}
		
	}
	
	public static Calendar convertToCalendar(LocalDate localDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		
		return calendar;
	  }
	
	@FXML
	public void onCancel() {
		stage.close();
	}
	
	@FXML
	public void onHelp() {
		
	}
}
