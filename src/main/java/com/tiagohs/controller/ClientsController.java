package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;

import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Controller
public class ClientsController implements BaseController {

	public static final String PATH_FXML = "/fxml/clients.fxml";
	public static final String TITLE = "Clients - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		
	}
	
	@FXML
	private void onNewClient() throws Exception {
		WindowsUtils.openNewWindow(ClientNewController.PATH_FXML, ClientNewController.TITLE, ClientNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
}
