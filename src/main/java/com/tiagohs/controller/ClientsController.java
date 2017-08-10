package com.tiagohs.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.tiagohs.model.Client;
import com.tiagohs.model.dto.ClientTableDTO;
import com.tiagohs.service.ClientService;
import com.tiagohs.service.TableService;
import com.tiagohs.util.TableUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

@SuppressWarnings("unchecked")
@Controller
public class ClientsController extends BaseController {

	public static final String PATH_FXML = "/fxml/clients.fxml";
	public static final String CLIENTS_TITLE_KEY = "clients.title";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private StackPane container;
	
	@FXML
	private JFXTreeTableView<ClientTableDTO> clientsTable;
	
	@FXML
    private JFXTreeTableColumn<ClientTableDTO, String> nameColumn;
	
	@FXML
    private JFXTreeTableColumn<ClientTableDTO, String> emailColumn;
	
	@FXML
    private JFXTreeTableColumn<ClientTableDTO, String> addressColumn;
	
	@FXML
    private JFXTreeTableColumn<ClientTableDTO, String> foneColumn;
	
	@FXML
    private JFXTreeTableColumn<ClientTableDTO, String> numOrdersColumn;
	
	@FXML
    private JFXTreeTableColumn<ClientTableDTO, String> typeColumn;
	
	@FXML
	private Pagination clientPagination;
	
	@FXML
    private JFXTextField clientSearchTextField;
    
    @FXML
    private JFXButton clientEditButton;
	
    @FXML
    private JFXButton clientRemoveButton;
    
    @Autowired
    private ClientService clientService;
    
    private ObservableList<ClientTableDTO> data;
    private TableService tableService;
    
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		this.tableService = new TableService(() -> configureTable());
		this.tableService.start();
		
		configureSearch();
		
		TableUtils.configureEditAndRemoveState(clientsTable, clientEditButton, clientRemoveButton);
	}

	@Override
	protected void onClose() {
		clientService.onClose();
	}
	
	private void configureSearch() {
		TableUtils.configureTableSearch(clientSearchTextField, clientsTable, (clientProp, newVal) -> configureSearchTest(clientProp, newVal));
	}

	private boolean configureSearchTest(TreeItem<ClientTableDTO> clientProp, String value) {
		final ClientTableDTO client = clientProp.getValue();
		
        return client.getName().get().contains(value)
            || client.getEmail().get().contains(value)
            || client.getFone().get().contains(value)
            || client.getAddress().get().contains(value)
            || client.getAddress().get().contains(value)
            || client.getNumOrders().get().contains(value);
	}

	private void configureTable() {
		TableUtils.setupColumn(nameColumn, ClientTableDTO::getName);
		TableUtils.setupColumn(emailColumn, ClientTableDTO::getEmail);
		TableUtils.setupColumn(addressColumn, ClientTableDTO::getAddress);
		TableUtils.setupColumn(foneColumn, ClientTableDTO::getFone);
		TableUtils.setupColumn(numOrdersColumn, ClientTableDTO::getNumOrders);
		TableUtils.setupColumn(typeColumn, ClientTableDTO::getType);
		
		clientService.findAll(e -> {
			TableUtils.configureTable((List<Client>) e.getSource().getValue(), data, clientsTable, clientPagination, en -> createData(en));
		}, null);
	}
	
	private ClientTableDTO createData(Client client) {
		ClientTableDTO clientTableDTO = new ClientTableDTO();
		
		if (client.getUser() != null) {
			clientTableDTO.setName(client.getUser().getName());
			clientTableDTO.setEmail(client.getUser().getEmail());
		}
		
		if (client.getAddress() != null) {
			clientTableDTO.setAddress(client.getAddress().getStreet() + " - " + client.getAddress().getNumber());
		}
		
		clientTableDTO.setType(clientTableDTO.getType());
		clientTableDTO.setNumOrders("--");
		clientTableDTO.setOriginalClient(client);
		
		return clientTableDTO;
	}
	
	@FXML
	private void onReloadTable() {
		TableUtils.reloadTable(() -> configureTable());
		TableUtils.updateEditAndRemoveButtonState(clientsTable, clientEditButton, clientRemoveButton);
	}
	
	@FXML
	private void onEditTable() throws Exception {
		ClientTableDTO clientTableDTO = clientsTable.getSelectionModel().selectedItemProperty().get().getValue();
		TableUtils.editItemFromTable(clientsTable, clientTableDTO.getOriginalClient(), ClientNewController.PRODUCT_KEY, ClientNewController.PATH_FXML, getWindowTitle(ClientNewController.NEW_CLIENT_TITLE_KEY), ClientNewController.PATH_ICON);
	}
	
	@FXML
	private void onRemoveTable() {
		ClientTableDTO clientTableDTO = clientsTable.getSelectionModel().selectedItemProperty().get().getValue();
		WindowsUtils.createDefaultDialog(container, 
										 "Remove Product", "Are you sure you want to delete the " + clientTableDTO.getOriginalClient().getUser().getName() + " ?", 
										 () -> { TableUtils.removeItemFromTable(clientService, clientTableDTO.getOriginalClient().getId(), clientsTable, data, container); });
	}
	
	@FXML
	private void onNewClient() throws Exception {
		WindowsUtils.openNewWindow(ClientNewController.PATH_FXML, getWindowTitle(ClientNewController.NEW_CLIENT_TITLE_KEY), ClientNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
}
