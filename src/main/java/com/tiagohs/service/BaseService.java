package com.tiagohs.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public abstract class BaseService<T, R extends JpaRepository<T, Long>> {
	
	private R repository;
	
	public BaseService(R repository) {
		this.repository = repository;
	}
	
	public Service<T> save(T obj, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) throws Exception {
		
		if (obj == null) {
			throw new Exception();
		}
		
		return createService(new Task<T>() {
			protected T call() throws Exception {
				return repository.save(obj);
			};
		}, onSucess, beforeStart);
		
	}
	
	public Service<List<T>> findAll(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<List<T>>() {
			protected List<T> call() throws Exception {
				return repository.findAll();
			};
		}, onSucess, beforeStart);
	}
	
	
	public Service<Void> delete(long id, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) throws Exception {
		return createService(new Task<Void>() {
			protected Void call() throws Exception {
				repository.delete(id);
				return null;
			};
		}, onSucess, beforeStart);
		
	}
	
	
	public Service<T> find(long id, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) throws Exception {
		return createService(new Task<T>() {
			protected T call() throws Exception {
				return repository.findOne(id);
			};
		}, onSucess, beforeStart);
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
		
		return service;
	}
	
}
