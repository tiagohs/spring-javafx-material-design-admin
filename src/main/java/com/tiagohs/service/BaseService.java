package com.tiagohs.service;

import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public abstract class BaseService {
	
	private List<Service<?>> services;

	public BaseService() {
		this.services = new ArrayList<Service<?>>();
	}
	
	protected <D> Service<D> createService(Task<D> task, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		Service<D> service = new Service<D>() {
				protected Task<D> createTask() {
					return task;
				}
		};
		
		if (onSucess != null)
			service.setOnSucceeded(onSucess);
		
		if (beforeStart != null)
			service.setOnScheduled(beforeStart);
		
		service.setOnFailed(e -> {
			System.out.println("Failed: " + e.getSource().getException());
		});
		
		service.start();
		
		services.add(service);
		
		return service;
	}
	
	public void onClose() {
		
		for (Service<?> service : services) {
			service.cancel();
		}
		
	}
	
}
