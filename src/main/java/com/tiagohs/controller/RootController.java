package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tiagohs.service.UserService;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


@Controller
public class RootController extends BaseController {

	public static final String PATH_FXML = "/fxml/root.fxml";
	public static final String TITLE = "Inventory Management - stuffs-Admin";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private AnchorPane rootPane;
	
	@Autowired
	private UserService userService;
	
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		try {
			WindowsUtils.replaceFxmlOnWindow(rootPane, DashboardController.PATH_FXML, stage, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onClose() {
		
	}
	
	@FXML
	private void onInvetoryAction() throws Exception {
		WindowsUtils.replaceFxmlOnWindow(rootPane, InventoryController.PATH_FXML, stage, null);
	}
	
	@FXML
	private void onDashboardAction() throws Exception {
		WindowsUtils.replaceFxmlOnWindow(rootPane, DashboardController.PATH_FXML, stage, null);
	}
	
	@FXML
	private void onSalesAction() throws Exception {
		WindowsUtils.replaceFxmlOnWindow(rootPane, SalesController.PATH_FXML, stage, null);
	}
	
	@FXML
	private void onClientsAction() throws Exception {
		WindowsUtils.replaceFxmlOnWindow(rootPane, ClientsController.PATH_FXML, stage, null);
	}
	
	@FXML
	private void onReportsAction() throws Exception {
		WindowsUtils.replaceFxmlOnWindow(rootPane, ReportsController.PATH_FXML, stage, null);
	}
	
	@FXML
	private void onAbout() throws Exception {
		WindowsUtils.openNewWindow(AboutController.PATH_FXML, AboutController.TITLE, AboutController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	private void onSettings() throws Exception {
		WindowsUtils.openNewWindow(SettingsController.PATH_FXML, SettingsController.TITLE, SettingsController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	private void onLogout() throws Exception {
		
		userService.setUserAsSignOut(e -> {
			try {
				WindowsUtils.openNewWindow(LoginController.PATH_FXML, LoginController.TITLE, LoginController.PATH_ICON, null, Modality.WINDOW_MODAL);
				onClose();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}, null);
		
	}
	
}
