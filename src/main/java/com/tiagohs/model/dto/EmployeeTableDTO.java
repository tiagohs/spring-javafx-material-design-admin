package com.tiagohs.model.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tiagohs.model.Address;
import com.tiagohs.util.WindowsUtils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployeeTableDTO extends RecursiveTreeObject<EmployeeTableDTO> {
	
	private StringProperty name;
	private StringProperty email;
	private StringProperty cpf;
	private StringProperty adress;
	
	public StringProperty getName() {
		return name;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public StringProperty getEmail() {
		return email;
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}
	
	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}

	public StringProperty getCpf() {
		return cpf;
	}

	public void setCpf(StringProperty cpf) {
		this.cpf = cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = new SimpleStringProperty(WindowsUtils.formatCPF(cpf));
	}

	public StringProperty getAdress() {
		return adress;
	}

	public void setAdress(StringProperty adress) {
		this.adress = adress;
	}
	
	public void setAdress(String adress) {
		this.adress = new SimpleStringProperty(adress);
	}
	
	public void setAdress(Address adress) {
		this.adress = new SimpleStringProperty(adress.getStreet() + " " + adress.getNumber());
	}
	
}
