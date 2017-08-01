package com.tiagohs.model.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tiagohs.model.Sale;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SalesTableDTO extends RecursiveTreeObject<SalesTableDTO> {
	
	private StringProperty code;
	private StringProperty shipmentDate;
	private StringProperty issueDate;
	private StringProperty client;
	private StringProperty totalUnits;
	private StringProperty total;
	private Sale originalObject;
	
	public StringProperty getCode() {
		return code;
	}
	public void setCode(StringProperty code) {
		this.code = code;
	}
	public void setCode(String code) {
		this.code = new SimpleStringProperty(code);
	}
	public StringProperty getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(StringProperty shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	public void setShipmentDate(String shipmentDate) {
		this.shipmentDate = new SimpleStringProperty(shipmentDate);
	}
	public StringProperty getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(StringProperty issueDate) {
		this.issueDate = issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = new SimpleStringProperty(issueDate);
	}
	public StringProperty getClient() {
		return client;
	}
	public void setClient(StringProperty client) {
		this.client = client;
	}
	public void setClient(String client) {
		this.client = new SimpleStringProperty(client);
	}
	public StringProperty getTotalUnits() {
		return totalUnits;
	}
	public void setTotalUnits(StringProperty totalUnits) {
		this.totalUnits = totalUnits;
	}
	public void setTotalUnits(double totalUnits) {
		this.totalUnits = new SimpleStringProperty(String.valueOf(totalUnits));;
	}
	public void setTotalUnits(String totalUnits) {
		this.totalUnits = new SimpleStringProperty(totalUnits);
	}
	public StringProperty getTotal() {
		return total;
	}
	public void setTotal(StringProperty total) {
		this.total = total;
	}
	public void setTotal(String total) {
		this.total = new SimpleStringProperty(total);
	}
	public void setTotal(double total) {
		this.total = new SimpleStringProperty(String.valueOf(total));
	}
	public void setFormattedTotal(double total) {
		this.total = new SimpleStringProperty(String.format("R$ %.2f", total));
	}
	public Sale getOriginalObject() {
		return originalObject;
	}
	public void setOriginalObject(Sale originalObject) {
		this.originalObject = originalObject;
	}
	
	
}
