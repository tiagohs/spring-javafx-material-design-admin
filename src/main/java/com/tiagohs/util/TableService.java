package com.tiagohs.util;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class TableService extends Service<Void> {
	
	private ITableServiceCreator creator;
	
	public TableService(ITableServiceCreator creator) {
		this.creator = creator;
	}
	
	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {           
            @Override
            protected Void call() throws Exception {
            	Platform.runLater(() -> creator.onCreate());
            	
                return null;
            }
        };
	}

}
