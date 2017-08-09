package com.tiagohs.controller;

import java.util.HashMap;

import com.tiagohs.MainApplication;
import com.tiagohs.util.I18N;

import javafx.stage.Stage;

public abstract class BaseController {
	
	protected Stage stage;
	protected I18N i18N;
	
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		this.stage = stage;
		this.i18N = MainApplication.i18n;
		
		this.stage.setOnHiding(e -> { onClose(); });
		this.stage.setOnHidden(e -> { onClose(); });
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public I18N getI18N() {
		return i18N;
	}
	
	protected abstract void onClose();
}
