package com.tiagohs.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private long id;
	
	@Column(name = "cpf")
	private String cpf;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Address address;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Fone> phones;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Address getAddres() {
		return address;
	}

	public void setAddres(Address addres) {
		this.address = addres;
	}

	public List<Fone> getFones() {
		return phones;
	}

	public void setFones(List<Fone> fones) {
		this.phones = fones;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		if (getUser() != null) {
			return getUser().getName();
		} else {
			return getCpf();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Employee) {
			if (obj != null) {
				Employee employee = (Employee) obj;
				return employee.getId() == getId();
			}
		}
		
		return false;
	}
}
