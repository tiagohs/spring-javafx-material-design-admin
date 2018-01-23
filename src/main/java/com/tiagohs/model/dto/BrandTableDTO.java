package com.tiagohs.model.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tiagohs.model.Brand;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BrandTableDTO extends RecursiveTreeObject<BrandTableDTO> {
	
	private StringProperty name;
	private StringProperty email;
	private StringProperty additionalInformation;
	private Brand originalBrand;
	
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
	public StringProperty getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(StringProperty additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = new SimpleStringProperty(additionalInformation);
	}
	public Brand getOriginalBrand() {
		return originalBrand;
	}
	public void setOriginalBrand(Brand originalBrand) {
		this.originalBrand = originalBrand;
	}
	
	
}
