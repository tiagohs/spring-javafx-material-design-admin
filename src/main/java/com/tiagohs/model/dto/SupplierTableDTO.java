package com.tiagohs.model.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tiagohs.model.Address;
import com.tiagohs.model.Supplier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SupplierTableDTO extends RecursiveTreeObject<SupplierTableDTO> {
	
	private StringProperty companyName;
	private StringProperty email;
	private StringProperty adress;
	private Supplier originalSupplier;
	
	public SupplierTableDTO(String companyName, String email, Address adress, Supplier originalSupplier) {
		this.companyName = new SimpleStringProperty(companyName);
		this.email = new SimpleStringProperty(email);
		this.adress = new SimpleStringProperty(adress.getStreet() + " " + adress.getNumber());
		this.originalSupplier = originalSupplier;
	}
	
	public SupplierTableDTO() {
	}
	
	public StringProperty getCompanyName() {
		return companyName;
	}
	public void setCompanyName(StringProperty companyName) {
		this.companyName = companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = new SimpleStringProperty(companyName);
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

	public Supplier getOriginalSupplier() {
		return originalSupplier;
	}

	public void setOriginalSupplier(Supplier originalSupplier) {
		this.originalSupplier = originalSupplier;
	}
	
}
