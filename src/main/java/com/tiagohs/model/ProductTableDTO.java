package com.tiagohs.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.StringProperty;

public class ProductTableDTO extends RecursiveTreeObject<ProductTableDTO> {
	
	private StringProperty userName;
	private StringProperty age;
	private StringProperty department;
	
	public ProductTableDTO(StringProperty userName, StringProperty age, StringProperty department) {
		this.userName = userName;
		this.age = age;
		this.department = department;
	}

	public StringProperty getUserName() {
		return userName;
	}

	public void setUserName(StringProperty userName) {
		this.userName = userName;
	}

	public StringProperty getAge() {
		return age;
	}

	public void setAge(StringProperty age) {
		this.age = age;
	}

	public StringProperty getDepartment() {
		return department;
	}

	public void setDepartment(StringProperty department) {
		this.department = department;
	}
	
	
	
}
