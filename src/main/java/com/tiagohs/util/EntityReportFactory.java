package com.tiagohs.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.tiagohs.model.Employee;
import com.tiagohs.model.Product;
import com.tiagohs.model.Sale;
import com.tiagohs.model.Supplier;
import com.tiagohs.model.dto.EmployeeReportDTO;
import com.tiagohs.model.dto.ProductReportDTO;
import com.tiagohs.model.dto.SalesReportDTO;
import com.tiagohs.model.dto.SupplierReportDTO;

public class EntityReportFactory {
	
	public static List<SalesReportDTO> createSales(List<Sale> sales) {
		List<SalesReportDTO> salesReport = new ArrayList<>();
		
		for (Sale sale : sales) {
			salesReport.add(createSale(sale));
		}
		
		return salesReport;
	}
	
	public static SalesReportDTO createSale(Sale sale) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		
		SalesReportDTO salesReportDTO = new SalesReportDTO();
		
		salesReportDTO.setCode(isEmpty(sale.getSaleCode()));
		salesReportDTO.setIssueDate(isEmpty(sale.getIssueDate(), formatter.format(sale.getIssueDateFormatter().getTime())));
		salesReportDTO.setShipmentDate(isEmpty(sale.getShipmentDate(), formatter.format(sale.getShipmentDateFormatter().getTime())));
		salesReportDTO.setTotalUnits(isEmpty(sale.getTotalUnits()));
		salesReportDTO.setTotal(isEmpty(sale.getTotal()));
		salesReportDTO.setClient(isEmpty(sale.getCliente(), sale.getCliente().getUser().getName()));
		
		return salesReportDTO;
	}
	
	public static List<ProductReportDTO> createProducts(List<Product> products) {
		List<ProductReportDTO> productsReport = new ArrayList<>();
		
		for (Product p : products) {
			productsReport.add(createProduct(p));
		}
		
		return productsReport;
	}
	
	public static ProductReportDTO createProduct(Product product) {
		ProductReportDTO productReportDTO = new ProductReportDTO();
		
		productReportDTO.setSku(isEmpty(product.getSku()));
		productReportDTO.setDescription(isEmpty(product.getDescription()));
		productReportDTO.setBuyPrice(isEmpty(product.getBuyPrice()));
		productReportDTO.setSupplier(product.getSupplier() == null ? "---" : isEmpty(product.getSupplier().getCompanyName()));
		productReportDTO.setProductType(product.getProductType() == null ? "---" : isEmpty(product.getProductType().getName()));
		
		return productReportDTO;
	}
	
	public static List<EmployeeReportDTO> createEmployees(List<Employee> employees) {
		List<EmployeeReportDTO> employeeReport = new ArrayList<>();
		
		for (Employee e : employees) {
			employeeReport.add(createEmployee(e));
		}
		
		return employeeReport;
	}
	
	public static EmployeeReportDTO createEmployee(Employee employee) {
		EmployeeReportDTO employeeReportDTO = new EmployeeReportDTO();
		
		employeeReportDTO.setName(employee.getUser() == null ? "---" : isEmpty(employee.getUser().getName()));
		employeeReportDTO.setEmail(employee.getUser() == null ? "---" : isEmpty(employee.getUser().getEmail()));
		employeeReportDTO.setCpf(isEmpty(employee.getCpf()));
		employeeReportDTO.setAdress(employee.getAddres() == null ? "---" : 
									isEmpty(String.format("%s - %d", employee.getAddres().getStreet(), employee.getAddres().getNumber())));
		
		return employeeReportDTO;
	}
	
	public static List<SupplierReportDTO> createSuppliers(List<Supplier> suppliers) {
		List<SupplierReportDTO> supplierReport = new ArrayList<>();
		
		for (Supplier s : suppliers) {
			supplierReport.add(createSupplier(s));
		}
		
		return supplierReport;
	}
	
	public static SupplierReportDTO createSupplier(Supplier supplier) {
		SupplierReportDTO supplierReportDTO = new SupplierReportDTO();
		
		supplierReportDTO.setAdress(supplier.getAddres() == null ? "---" : 
								    isEmpty(String.format("%s - %d", supplier.getAddres().getStreet(), supplier.getAddres().getNumber())));
		supplierReportDTO.setCompanyName(supplier.getCompanyName());
		supplierReportDTO.setEmail(supplier.getEmail());
		
		return supplierReportDTO;
	}
	
	private static String isEmpty(String value) {
		return value == null ? "---" : value;
	}
	
	private static String isEmpty(Object object, String value) {
		return object == null ? "---" : isEmpty(value);
	}
	
	private static String isEmpty(double value) {
		return value == 0.0 ? "---" : String.format("%.2f", value);
	}
	
	private static String isEmpty(int value) {
		return value == 0 ? "---" : String.valueOf(value);
	}
}
