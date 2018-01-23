package com.tiagohs.service;

import com.tiagohs.model.Client;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public interface ClientService extends IBaseService<Client> {
	
	javafx.concurrent.Service<Long> getTotalClients(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);
}
