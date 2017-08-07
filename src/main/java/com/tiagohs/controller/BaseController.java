package com.tiagohs.controller;

import java.util.HashMap;

import javafx.stage.Stage;

public abstract class BaseController {
	
	protected Stage stage;
	
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		this.stage = stage;
		
		this.stage.setOnHiding(e -> { onClose(); });
		this.stage.setOnHidden(e -> { onClose(); });
	}
	
	public Stage getStage() {
		return stage;
	}
	
	protected abstract void onClose();
}
