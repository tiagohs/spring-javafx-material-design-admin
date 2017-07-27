package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.tiagohs.model.dto.ClientTableDTO;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Controller
public class ClientsController implements BaseController {

	public static final String PATH_FXML = "/fxml/clients.fxml";
	public static final String TITLE = "Clients - Inventory Management";
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
    
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		
	}
	
	@FXML
	private void onNewClient() throws Exception {
		WindowsUtils.openNewWindow(ClientNewController.PATH_FXML, ClientNewController.TITLE, ClientNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
}
