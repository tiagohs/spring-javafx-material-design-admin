package com.tiagohs.service;

import com.tiagohs.model.Employee;

public interface EmployeeService extends IBaseService<Employee> {
	
	Long getTotalEmployees();
}
