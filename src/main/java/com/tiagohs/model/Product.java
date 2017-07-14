package com.tiagohs.model;

import java.util.Calendar;
import java.util.List;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

public class Product {
	
	private long id;
	private String sku;
	private String supplier; 
	private String productType;
	private String brand;
	private String description;
	private String initialCostPrice;
	private String buyPrice;
	private String wholesalePrice;
	private String retailPrice;
	private String weight;

	private Calendar createdAt;
	private Calendar updatedAt;
	
	private List<Image> images;
	private List<Tag> tags;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInitialCostPrice() {
		return initialCostPrice;
	}
	public void setInitialCostPrice(String initialCostPrice) {
		this.initialCostPrice = initialCostPrice;
	}
	public String getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getWholesalePrice() {
		return wholesalePrice;
	}
	public void setWholesalePrice(String wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}
	public String getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public Calendar getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}
	public Calendar getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
}
