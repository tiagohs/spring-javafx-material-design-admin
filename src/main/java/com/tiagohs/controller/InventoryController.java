package com.tiagohs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tiagohs.model.Employee;
import com.tiagohs.model.Product;
import com.tiagohs.model.Supplier;
import com.tiagohs.model.dto.EmployeeTableDTO;
import com.tiagohs.model.dto.ProductTableDTO;
import com.tiagohs.model.dto.SupplierTableDTO;
import com.tiagohs.service.EmployeeService;
import com.tiagohs.service.ProductService;
import com.tiagohs.service.SupplierService;
import com.tiagohs.util.TableService;
import com.tiagohs.util.TableUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.stage.Modality;
import javafx.stage.Stage;


@Controller
public class InventoryController implements BaseController {
	
	public static final String PATH_FXML = "/fxml/inventory.fxml";
	public static final String TITLE = "Inventory - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private JFXTreeTableView<ProductTableDTO> productsTable;
	
	@FXML
	private JFXTreeTableView<EmployeeTableDTO> employeeTable;
	
	@FXML
	private JFXTreeTableView<SupplierTableDTO> supplierTable;
	
	@FXML
    private JFXTreeTableColumn<EmployeeTableDTO, String> employeeNameColumn;
	
	@FXML
    private JFXTreeTableColumn<EmployeeTableDTO, String> employeeEmailColumn;
	
	@FXML
    private JFXTreeTableColumn<EmployeeTableDTO, String> employeeCpfColumn;
	
	@FXML
    private JFXTreeTableColumn<EmployeeTableDTO, String> employeeAdressColumn;
	
	@FXML
    private JFXTreeTableColumn<ProductTableDTO, String> productSkuColumn;
	
	@FXML
    private JFXTreeTableColumn<ProductTableDTO, String> productSupplierColumn;
	
	@FXML
    private JFXTreeTableColumn<ProductTableDTO, String> productBuyPriceColumn;
	
	@FXML
    private JFXTreeTableColumn<ProductTableDTO, String> productProductTypeColumn;
	
	@FXML
    private JFXTreeTableColumn<ProductTableDTO, String> productDescriptionColumn;
	
	@FXML
    private JFXTreeTableColumn<SupplierTableDTO, String> supplierNameColumn;
	
	@FXML
    private JFXTreeTableColumn<SupplierTableDTO, String> supplierEmailColumn;
	
	@FXML
    private JFXTreeTableColumn<SupplierTableDTO, String> supplierAddresColumn;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private SupplierService supplierService;
	
	private List<TableService> tableService;
	
	public void init(Stage stage) {
		
		configureTables();
	}
	
	private void configureTables() {
		tableService = new ArrayList<TableService>();
		
		tableService.add(new TableService(() -> configureProductTable()));
		tableService.add(new TableService(() -> configureEmployeeTable()));
		tableService.add(new TableService(() -> configureSupplierTable()));
		
		tableService.forEach(s -> { s.start(); });
	}
	
	private void configureProductTable() {
		TableUtils.setupColumn(productSkuColumn, ProductTableDTO::getSku);
		TableUtils.setupColumn(productSupplierColumn, ProductTableDTO::getSupplier);
		TableUtils.setupColumn(productBuyPriceColumn, ProductTableDTO::getBuyPrice);
		TableUtils.setupColumn(productProductTypeColumn, ProductTableDTO::getProductType);
		TableUtils.setupColumn(productDescriptionColumn, ProductTableDTO::getDescription);
		
		productsTable.setRoot(new RecursiveTreeItem<>(TableUtils.filledDataOnTable(productService.findAll(), e -> createProductData(e)), 
							  						  RecursiveTreeObject::getChildren));
		productsTable.setShowRoot(false);
	}
	
	private void configureEmployeeTable() {
		
		TableUtils.setupColumn(employeeNameColumn, EmployeeTableDTO::getName);
		TableUtils.setupColumn(employeeEmailColumn, EmployeeTableDTO::getEmail);
		TableUtils.setupColumn(employeeCpfColumn, EmployeeTableDTO::getCpf);
		TableUtils.setupColumn(employeeAdressColumn, EmployeeTableDTO::getAdress);
		
		employeeTable.setRoot(new RecursiveTreeItem<>(TableUtils.filledDataOnTable(employeeService.findAll(), e -> createEmplyeeData(e)), 
							  						  RecursiveTreeObject::getChildren));
		employeeTable.setShowRoot(false);
	}
	
	private void configureSupplierTable() {
		TableUtils.setupColumn(supplierNameColumn, SupplierTableDTO::getCompanyName);
		TableUtils.setupColumn(supplierEmailColumn, SupplierTableDTO::getEmail);
		TableUtils.setupColumn(supplierAddresColumn, SupplierTableDTO::getAdress);
		
		supplierTable.setRoot(new RecursiveTreeItem<>(TableUtils.filledDataOnTable(supplierService.findAll(), e -> createSupplierData(e)), 
							  						  RecursiveTreeObject::getChildren));
		supplierTable.setShowRoot(false);
	}
	
	private EmployeeTableDTO createEmplyeeData(Employee e) {
		EmployeeTableDTO em = new EmployeeTableDTO();
		
		em.setName(e.getUser().getName());
		em.setEmail(e.getUser().getEmail());
		em.setCpf(e.getCpf());
		
		if (e.getAddres() != null) {
			em.setAdress(e.getAddres());
		} else {
			em.setAdress("--");
		}
		
		return em;
	}
	
	private ProductTableDTO createProductData(Product product) {
		return new ProductTableDTO(product.getSku(), product.getSupplier().getCompanyName(), product.getBuyPrice(), product.getProductType().getName(), product.getDescription());
	}
	
	private SupplierTableDTO createSupplierData(Supplier supplier) {
		SupplierTableDTO supplierTableDTO = new SupplierTableDTO();
		
		supplierTableDTO.setCompanyName(supplier.getCompanyName());
		supplierTableDTO.setEmail(supplier.getEmail());
		
		if (supplier.getAddres() != null) {
			supplierTableDTO.setAdress(supplier.getAddres());
		} else {
			supplierTableDTO.setAdress("--");
		}
		
		return supplierTableDTO;
		
	}
	
	@FXML
	public void onNewProduct() throws Exception {
		WindowsUtils.openNewWindow(ProductNewController.PATH_FXML, ProductNewController.TITLE, ProductNewController.PATH_ICON, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewEmployee() throws Exception {
		WindowsUtils.openNewWindow(EmployeeNewController.PATH_FXML, EmployeeNewController.TITLE, EmployeeNewController.PATH_ICON, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewSupplier() throws Exception {
		WindowsUtils.openNewWindow(SupplierNewController.PATH_FXML, SupplierNewController.TITLE, SupplierNewController.PATH_ICON, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewBrand() throws Exception {
		WindowsUtils.openNewWindow(BrandNewController.PATH_FXML, BrandNewController.TITLE, BrandNewController.PATH_ICON, Modality.APPLICATION_MODAL);
	}
}
