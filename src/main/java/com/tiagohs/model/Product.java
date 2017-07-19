package com.tiagohs.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private long id;
	
	@Column(name = "sku")
	private String sku;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "initial_cost_price")
	private double initialCostPrice;
	
	@Column(name = "buy_price")
	private double buyPrice;
	
	@Column(name = "wholesale_price")
	private double wholesalePrice;
	
	@Column(name = "retail_price")
	private double retailPrice;
	
	@Column(name = "weight")
	private double weight;
	
	@Column(name = "created_at")
	@Type(type="date")
	private Date createdAt;
	
	@Column(name = "updated_at")
	@Type(type="date")
	private Calendar updatedAt;
	
	@OneToOne(mappedBy = "product", optional = true, fetch = FetchType.LAZY)
	private Supplier supplier; 
	
	@OneToOne(mappedBy = "product", optional = true, fetch = FetchType.LAZY)
	private Brand brand;
	
	@OneToOne(mappedBy = "product", optional = true, fetch = FetchType.LAZY)
	private ProductType productType;
	
	@ManyToMany(mappedBy = "product")
	private List<Image> images;
	
	@ManyToMany(mappedBy = "product")
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

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getInitialCostPrice() {
		return initialCostPrice;
	}

	public void setInitialCostPrice(double initialCostPrice) {
		this.initialCostPrice = initialCostPrice;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Calendar getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
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
