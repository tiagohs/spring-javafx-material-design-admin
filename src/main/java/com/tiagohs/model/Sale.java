package com.tiagohs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "sale")
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sale_id")
	private long id;
	
	@Column(name = "sale_code")
	private String saleCode;
	
	@Type(type = "date")
	@Column(name = "issue_date")
	private Date issueDate;

	@Column(name = "shipment_date")
	private Date shipmentDate;

	@Column(name = "reference")
	private String reference;

	@Column(name = "email")
	private String email;

	@Column(name = "message")
	private String message;
	
	@Column(name = "state")
	private String state;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY)
	private Fone fone;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY)
	private Client cliente;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY)
	private User assignTo;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Item> items;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "sale_tag", 
	   joinColumns = @JoinColumn(name = "sale_id"), 
	   inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Fone getFone() {
		return fone;
	}

	public void setFone(Fone fone) {
		this.fone = fone;
	}

	public Client getCliente() {
		return cliente;
	}

	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(User assignTo) {
		this.assignTo = assignTo;
	}
	
	
}
