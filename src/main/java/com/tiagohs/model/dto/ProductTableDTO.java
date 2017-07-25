package com.tiagohs.model.dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductTableDTO extends RecursiveTreeObject<ProductTableDTO> {
	
	private StringProperty sku;
	private StringProperty supplier;
	private StringProperty buyPrice;
	private StringProperty productType;
	private StringProperty description;
	
	public ProductTableDTO(String sku, String supplier, double buyPrice,
			String productType, String description) {
		this.sku = new SimpleStringProperty(sku);
		this.supplier = new SimpleStringProperty(supplier);
		this.buyPrice = new SimpleStringProperty(Double.toString(buyPrice));
		this.productType = new SimpleStringProperty(productType);
		this.description = new SimpleStringProperty(description);
	}

	public StringProperty getSku() {
		return sku;
	}

	public void setSku(StringProperty sku) {
		this.sku = sku;
	}

	public StringProperty getSupplier() {
		return supplier;
	}

	public void setSupplier(StringProperty supplier) {
		this.supplier = supplier;
	}

	public StringProperty getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(StringProperty buyPrice) {
		this.buyPrice = buyPrice;
	}

	public StringProperty getProductType() {
		return productType;
	}

	public void setProductType(StringProperty productType) {
		this.productType = productType;
	}

	public StringProperty getDescription() {
		return description;
	}

	public void setDescription(StringProperty description) {
		this.description = description;
	}
	
	
	
}
