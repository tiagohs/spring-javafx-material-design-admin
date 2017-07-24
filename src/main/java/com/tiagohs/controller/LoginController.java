package com.tiagohs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.tiagohs.service.UserService;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Controller
public class LoginController implements BaseController {

	public static final String PATH_FXML = "/fxml/login.fxml";
	public static final String TITLE = "Login - stuffs-Admin: Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private JFXButton loginButton;
	
	@Autowired
	private UserService userService;
	
	private Stage loginStage;
	
	public void init(Stage stage) {
		this.loginStage = stage;
	}
	
	@FXML
	public void onLogin() throws Exception {
		WindowsUtils.openNewWindow(RootController.PATH_FXML, RootController.TITLE, RootController.PATH_ICON, Modality.WINDOW_MODAL);
		loginStage.close();
	}
}
