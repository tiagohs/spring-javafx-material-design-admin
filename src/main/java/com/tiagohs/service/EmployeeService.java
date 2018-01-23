package com.tiagohs.service;

import com.tiagohs.model.Employee;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public interface EmployeeService extends IBaseService<Employee> {
	
	javafx.concurrent.Service<Long> getTotalEmployees(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);
}
