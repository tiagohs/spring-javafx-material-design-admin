package com.tiagohs.controller;

import org.springframework.stereotype.Controller;

import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


@Controller
public class RootController implements BaseController {

	public static final String PATH_FXML = "/fxml/root.fxml";
	public static final String TITLE = "Inventory Management - stuffs-Admin";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private AnchorPane rootPane;
	
	private Stage rootStage;
	
	public void init(Stage stage) {
		this.rootStage = stage;
		
		try {
			WindowsUtils.replaceFxmlOnWindow(rootPane, DashboardController.PATH_FXML, rootStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void onInvetoryAction() throws Exception {
		WindowsUtils.replaceFxmlOnWindow(rootPane, InventoryController.PATH_FXML, rootStage);
	}
	
	@FXML
	private void onDashboardAction() throws Exception {
		WindowsUtils.replaceFxmlOnWindow(rootPane, DashboardController.PATH_FXML, rootStage);
	}
	
	@FXML
	private void onSalesAction() throws Exception {
		WindowsUtils.replaceFxmlOnWindow(rootPane, SalesController.PATH_FXML, rootStage);
	}
	
	@FXML
	private void onClientsAction() throws Exception {
		WindowsUtils.replaceFxmlOnWindow(rootPane, ClientsController.PATH_FXML, rootStage);
	}
	
	@FXML
	private void onReportsAction() throws Exception {
		WindowsUtils.replaceFxmlOnWindow(rootPane, ReportsController.PATH_FXML, rootStage);
	}
	
	@FXML
	private void onAbout() throws Exception {
		WindowsUtils.openNewWindow(AboutController.PATH_FXML, AboutController.TITLE, AboutController.PATH_ICON, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	private void onSettings() throws Exception {
		WindowsUtils.openNewWindow(SettingsController.PATH_FXML, SettingsController.TITLE, SettingsController.PATH_ICON, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	private void onLogout() throws Exception {
		
		//Check
		
		WindowsUtils.openNewWindow(LoginController.PATH_FXML, LoginController.TITLE, LoginController.PATH_ICON, Modality.WINDOW_MODAL);
		
	}
	
}
