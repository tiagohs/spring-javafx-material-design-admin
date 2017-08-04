package com.tiagohs.service;

import com.tiagohs.model.Supplier;

import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public interface SupplierService extends IBaseService<Supplier> {
	
	Service<Long> getTotalSuppliers(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);
}
