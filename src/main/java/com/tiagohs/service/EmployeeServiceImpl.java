package com.tiagohs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Employee;
import com.tiagohs.repository.EmployeeRepository;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

@Service("employeeService")
public class EmployeeServiceImpl extends BaseCrudService<Employee, JpaRepository<Employee,Long>> implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository repository) {
		super(repository);
		
		this.employeeRepository = repository;
	}

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public javafx.concurrent.Service<Long> getTotalEmployees(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<Long>() {
			protected Long call() throws Exception {
				return employeeRepository.getTotalEmployees();
			};
		}, onSucess, beforeStart);
	}
	
}
