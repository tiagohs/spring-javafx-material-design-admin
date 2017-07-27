package com.tiagohs.model.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tiagohs.model.Address;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClientTableDTO extends RecursiveTreeObject<ClientTableDTO>{
	
	private StringProperty name;
	private StringProperty email;
	private StringProperty address;
	private StringProperty fone;
	private StringProperty numOrders;
	private StringProperty type;
	
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
	public StringProperty getAddress() {
		return address;
	}
	public void setAddress(StringProperty address) {
		this.address = address;
	}
	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}
	public void setAddress(Address address) {
		this.address = new SimpleStringProperty(address.getStreet() + " - " + address.getNumber());
	}
	public StringProperty getFone() {
		return fone;
	}
	public void setFone(StringProperty fone) {
		this.fone = fone;
	}
	public void setFone(String fone) {
		this.fone = new SimpleStringProperty(fone);
	}
	public StringProperty getNumOrders() {
		return numOrders;
	}
	public void setNumOrders(StringProperty numOrders) {
		this.numOrders = numOrders;
	}
	public void setNumOrders(String numOrders) {
		this.numOrders = new SimpleStringProperty(numOrders);
	}
	public StringProperty getType() {
		return type;
	}
	public void setType(StringProperty type) {
		this.type = type;
	}
	public void setType(String type) {
		this.type = new SimpleStringProperty(type);
	}
	
}
