package com.tiagohs.controller;

import java.util.HashMap;

import javafx.stage.Stage;

public interface BaseController {
	
	public <T> void init(Stage stage, HashMap<String, T> parameters);
}
