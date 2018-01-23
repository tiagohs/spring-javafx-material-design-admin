package com.tiagohs.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tag_id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToMany(mappedBy = "tags")
	private List<Product> products;
	
	@ManyToMany(mappedBy = "tags")
	private List<Sale> sales;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Tag) {
			if (obj != null) {
				Tag tag = (Tag) obj;
				return tag.getId() == getId();
			}
		}
		
		return false;
	}
}
