package com.tiagohs.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

@Entity
@Table(name = "sale")
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sale_id")
	private long id;
	
	@Column(name = "sale_code")
	private String saleCode;
	
	@Column(name = "issue_date")
	private String issueDate;

	@Column(name = "shipment_date")
	private String shipmentDate;

	@Column(name = "reference")
	private String reference;

	@Column(name = "email")
	private String email;

	@Column(name = "message")
	private String message;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "total_units")
	private double totalUnits;
	
	@Column(name = "total")
	private double total;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Fone fone;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Client cliente;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Item> items;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "sale_tag", 
	   joinColumns = @JoinColumn(name = "sale_id"), 
	   inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags;
	
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
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

	public double getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(double totalUnits) {
		this.totalUnits = totalUnits;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Calendar getIssueDateFormatter() {
		Calendar c = Calendar.getInstance();
		
		try {
			c.setTime(formatter.parse(issueDate));
		} catch (ParseException e) {
			
		}
		
		return c;
	}
	
	public void setIssueDate(Calendar issueDate) {
		this.issueDate = formatter.format(issueDate.getTime());
	}

	public Calendar getShipmentDateFormatter() {
		Calendar c = Calendar.getInstance();
		
		try {
			c.setTime(formatter.parse(shipmentDate));
		} catch (ParseException e) {
			
		}
		
		return c;
	}
	
	public void setShipmentDate(Calendar shipmentDate) {
		this.shipmentDate = formatter.format(shipmentDate.getTime());
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public void setShipmentDate(String shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public String getShipmentDate() {
		return shipmentDate;
	}
	
}
