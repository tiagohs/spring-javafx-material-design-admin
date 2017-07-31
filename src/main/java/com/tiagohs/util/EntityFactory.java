package com.tiagohs.util;

import java.util.Date;
import java.util.List;

import com.tiagohs.model.Address;
import com.tiagohs.model.Brand;
import com.tiagohs.model.Client;
import com.tiagohs.model.ClientType;
import com.tiagohs.model.Employee;
import com.tiagohs.model.Fone;
import com.tiagohs.model.Image;
import com.tiagohs.model.Item;
import com.tiagohs.model.Product;
import com.tiagohs.model.ProductType;
import com.tiagohs.model.Role;
import com.tiagohs.model.Sale;
import com.tiagohs.model.Supplier;
import com.tiagohs.model.Tag;
import com.tiagohs.model.User;

public class EntityFactory {
	
	public static Supplier createSupplier(String companyName, String email, Address address) {
		return createSupplier(new Supplier(), companyName, email, address);
	}
	
	public static Supplier createSupplier(Supplier supplier, String companyName, String email, Address address) {
		
		if (supplier == null) {
			supplier = new Supplier();
		}
		
		supplier.setCompanyName(companyName);
		supplier.setEmail(email);
		supplier.setAddres(address);
		
		return supplier;
	}
	
	public static Employee createEmployee(String cpf, User user, Address address, List<Fone> phones) {
		return createEmployee(new Employee(), cpf, user, address, phones);
	}
	
	public static Employee createEmployee(Employee employee, String cpf, User user, Address address, List<Fone> phones) {
		
		if (employee == null) {
			employee = new Employee();
		}
		
		employee.setCpf(cpf);
		employee.setUser(user);
		employee.setAddres(address);
		employee.setFones(phones);
		
		return employee;
	}
	
	public static Product createProduct(String sku, String name, String description, double initialCostPrice, double buyPrice,
										double wholesalePrice, double retailPrice, double weight, double initialStock, Date createdAt,
										Date updatedAt, Supplier supplier, Brand brand, ProductType productType, List<Image> images,
										List<Tag> tags) {
		return createProduct(new Product(), sku, name, description, initialCostPrice, buyPrice, wholesalePrice, retailPrice, weight, initialStock, createdAt, updatedAt, supplier, brand, productType, images, tags);
	}
	
	public static Product createProduct(Product product, String sku, String name, String description, double initialCostPrice, double buyPrice,
			double wholesalePrice, double retailPrice, double weight, double initialStock, Date createdAt,
			Date updatedAt, Supplier supplier, Brand brand, ProductType productType, List<Image> images,
			List<Tag> tags) {
		
		if (product == null) {
			product = new Product();
		}
		
		product.setSku(sku);
		product.setName(name);
		product.setDescription(description);
		product.setInitialCostPrice(initialCostPrice);
		product.setBuyPrice(buyPrice);
		product.setWholesalePrice(wholesalePrice);
		product.setRetailPrice(retailPrice);
		product.setWeight(weight);
		product.setInitialStock(initialStock);
		product.setCreatedAt(createdAt);
		product.setUpdatedAt(updatedAt);
		
		product.setBrand(brand);
		product.setProductType(productType);
		product.setSupplier(supplier);
		
		product.setTags(tags);
		product.setImages(images);
		
		return product;
	}
	
	public static Brand createBrand(String name, String email, String additionalInformation) {
		return createBrand(new Brand(), name, email, additionalInformation);
	}
	
	public static Brand createBrand(Brand brand, String name, String email, String additionalInformation) {
		
		brand.setName(name);
		brand.setEmail(email);
		brand.setAdditionalInformation(additionalInformation);
		
		return brand;
	}
	
	public static Sale createSale(String saleCode, Date issueDate, Date shipmentDate, String reference, String email,
			String message, String state, double totalUnits, double total, Fone fone, Client cliente, List<Item> items,
			List<Tag> tags) {
		return createSale(new Sale(), saleCode, issueDate, shipmentDate, reference, email, message, state, totalUnits, total, fone, cliente, items, tags);
	}
	
	public static Sale createSale(Sale sale, String saleCode, Date issueDate, Date shipmentDate, String reference, String email,
			String message, String state, double totalUnits, double total, Fone fone, Client cliente, List<Item> items,
			List<Tag> tags) {
		
		sale.setSaleCode(saleCode);
		sale.setIssueDate(issueDate);
		sale.setShipmentDate(shipmentDate);
		sale.setReference(reference);
		sale.setEmail(email);
		sale.setMessage(message);
		sale.setState(state);
		sale.setTotalUnits(totalUnits);
		sale.setTotal(total);
		sale.setFone(fone);
		sale.setCliente(cliente);
		sale.setItems(items);
		
		return sale;
	}
	
	
	
	public static Client createClient(String cpf, ClientType clientType, Address address,
											List<Fone> phones, User user) {
		return createClient(new Client(), cpf, clientType, address, phones, user);
	}
	
	public static Client createClient(Client client, String cpf, ClientType clientType, Address address,
									List<Fone> phones, User user) {
		
		if (client == null) {
			client = new Client();
		}
		
		client.setAddress(address);
		client.setCpf(cpf);
		client.setClientType(clientType);
		client.setPhones(phones);
		client.setUser(user);
		
		return client;
	}
	
	public static ClientType createClientType(String name) {
		ClientType clientType = new ClientType();
		
		clientType.setName(name);
		
		return clientType;
	}
	
	public static ProductType createProductType(String name) {
		ProductType productType = new ProductType();
		
		productType.setName(name);
		
		return productType;
	}
	
	public static Image createImage(String path) {
		Image image = new Image();
		
		image.setPath(path);
		
		return image;
	}
	
	public static Tag createTag(String name) {
		Tag tag = new Tag();
		
		tag.setName(name);
		
		return tag;
	}
	
	public static Address createAddress(String street, int number, String complement, String suburb, String city, String state,
			String country, String cep) {
		Address address = new Address();
		
		address.setStreet(street);
		address.setNumber(number);
		address.setComplement(complement);
		address.setSuburb(suburb);
		address.setSuburb(suburb);
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		address.setCep(cep);
		
		return address;
	}
	
	public static User createUser(String email, String name, String photoPath, String password, List<Role> role) {
		User user = new User();
		
		user.setName(name);
		user.setEmail(email);
		user.setPhotoPath(photoPath);
		user.setPassword(password);
		user.setRoles(role);
		
		return user;
	}
	
	public static Role createRole(String role, String roleName) {
		Role r = new Role();
		
		r.setName(roleName);
		r.setRole(role);
		
		return r;
	}
	
	public static Fone createPhone(int number) {
		Fone phone = new Fone();
		
		phone.setNumber(number);
		
		return phone;
	}
	
}
