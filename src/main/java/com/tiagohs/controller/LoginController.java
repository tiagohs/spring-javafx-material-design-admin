package com.tiagohs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.tiagohs.service.UserService;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.stage.Stage;

@Controller
public class LoginController implements BaseController {
	
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
		WindowsUtils.openNewWindow("/fxml/root.fxml", "stuffs-Admin: Inventory Management");
		loginStage.close();
	}
}
