package com.tiagohs.model.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tiagohs.model.Address;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SupplierTableDTO extends RecursiveTreeObject<SupplierTableDTO> {
	
	private StringProperty companyName;
	private StringProperty email;
	private StringProperty adress;
	
	public SupplierTableDTO(String companyName, String email, Address adress) {
		this.companyName = new SimpleStringProperty(companyName);
		this.email = new SimpleStringProperty(email);
		this.adress = new SimpleStringProperty(adress.getStreet() + " " + adress.getNumber());
	}
	
	public StringProperty getCompanyName() {
		return companyName;
	}
	public void setCompanyName(StringProperty companyName) {
		this.companyName = companyName;
	}
	public StringProperty getEmail() {
		return email;
	}
	public void setEmail(StringProperty email) {
		this.email = email;
	}
	public StringProperty getAdress() {
		return adress;
	}
	public void setAdress(StringProperty adress) {
		this.adress = adress;
	}
	
	
}
