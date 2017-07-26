package com.tiagohs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
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

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


@Controller
public class InventoryController implements BaseController {
	
	public static final String PATH_FXML = "/fxml/inventory.fxml";
	public static final String TITLE = "Inventory - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;

	@FXML
	private StackPane container;
	
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
	
	@FXML
	private Pagination productPagination;
	
	@FXML
	private Pagination employeePagination;
	
	@FXML
	private Pagination supplierPagination;
	
	@FXML
    private JFXTextField productSearchTextField;
	
	@FXML
    private JFXTextField employeeSearchTextField;
	
	@FXML
    private JFXTextField supplierSearchTextField;
	
	@FXML
    private JFXButton productEditButton;
	
    @FXML
    private JFXButton productRemoveButton;
    
    @FXML
    private JFXButton employeeEditButton;
	
    @FXML
    private JFXButton employeeRemoveButton;
    
    @FXML
    private JFXButton supplierEditButton;
	
    @FXML
    private JFXButton supplierRemoveButton;
    
    @FXML
    private JFXButton productReloadButton;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private SupplierService supplierService;
	
	private ObservableList<ProductTableDTO> productsData;
	
	private ObservableList<EmployeeTableDTO> employeesData;
	
	private ObservableList<SupplierTableDTO> suppliersData;
	
	private List<TableService> tableService;
	
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		
		configureTables();
		configureSearchs();
		configureEditAndRemoveOptions();
	}
	
	private void configureTables() {
		tableService = new ArrayList<TableService>();
		
		tableService.add(new TableService(() -> configureProductTable()));
		tableService.add(new TableService(() -> configureEmployeeTable()));
		tableService.add(new TableService(() -> configureSupplierTable()));
		
		tableService.forEach(s -> { s.start(); });
	}
	
	private void configureSearchs() {
		TableUtils.configureTableSearch(productSearchTextField, productsTable, (productProp, newVal) -> configureProductSearchTest(productProp, newVal));
		TableUtils.configureTableSearch(employeeSearchTextField, employeeTable, (productProp, newVal) -> configureEmployeeSearchTest(productProp, newVal));
		TableUtils.configureTableSearch(supplierSearchTextField, supplierTable, (productProp, newVal) -> configureSupplierSearchTest(productProp, newVal));
	}
	
	private void configureEditAndRemoveOptions() {
		productsTable.setOnMouseClicked((e) -> { 
			productEditButton.setDisable(false);
			productRemoveButton.setDisable(false);
		});
	}
	
	private boolean configureProductSearchTest(TreeItem<ProductTableDTO> productProp, String value) {
		final ProductTableDTO product = productProp.getValue();
        return product.getSku().get().contains(value)
            || product.getSupplier().get().contains(value)
            || product.getBuyPrice().get().contains(value)
            || product.getDescription().get().contains(value)
            || product.getProductType().get().contains(value);
	}
	
	private boolean configureEmployeeSearchTest(TreeItem<EmployeeTableDTO> employeeProp, String value) {
		final EmployeeTableDTO employee = employeeProp.getValue();
        return employee.getName().get().contains(value)
            || employee.getEmail().get().contains(value)
            || employee.getCpf().get().contains(value)
            || employee.getAdress().get().contains(value);
	}
	
	private boolean configureSupplierSearchTest(TreeItem<SupplierTableDTO> supplierProp, String value) {
		final SupplierTableDTO supplier = supplierProp.getValue();
        return supplier.getCompanyName().get().contains(value)
            || supplier.getEmail().get().contains(value)
            || supplier.getAdress().get().contains(value);
	}
	
	private void configureProductTable() {
		TableUtils.setupColumn(productSkuColumn, ProductTableDTO::getSku);
		TableUtils.setupColumn(productSupplierColumn, ProductTableDTO::getSupplier);
		TableUtils.setupColumn(productBuyPriceColumn, ProductTableDTO::getBuyPrice);
		TableUtils.setupColumn(productProductTypeColumn, ProductTableDTO::getProductType);
		TableUtils.setupColumn(productDescriptionColumn, ProductTableDTO::getDescription);
		
		productsData = TableUtils.filledDataOnTable(productService.findAll(), e -> createProductData(e));
		
		TableUtils.configurePagination(productsTable, productsData, productPagination);
		productsTable.setShowRoot(false);
		productsTable.setEditable(true);
	}
	
	private void configureEmployeeTable() {
		
		TableUtils.setupColumn(employeeNameColumn, EmployeeTableDTO::getName);
		TableUtils.setupColumn(employeeEmailColumn, EmployeeTableDTO::getEmail);
		TableUtils.setupColumn(employeeCpfColumn, EmployeeTableDTO::getCpf);
		TableUtils.setupColumn(employeeAdressColumn, EmployeeTableDTO::getAdress);
		
		employeesData = TableUtils.filledDataOnTable(employeeService.findAll(), e -> createEmplyeeData(e));
		
		TableUtils.configurePagination(employeeTable, employeesData, employeePagination);
		employeeTable.setShowRoot(false);
	}
	
	private void configureSupplierTable() {
		TableUtils.setupColumn(supplierNameColumn, SupplierTableDTO::getCompanyName);
		TableUtils.setupColumn(supplierEmailColumn, SupplierTableDTO::getEmail);
		TableUtils.setupColumn(supplierAddresColumn, SupplierTableDTO::getAdress);
		
		suppliersData = TableUtils.filledDataOnTable(supplierService.findAll(), e -> createSupplierData(e));
		
		TableUtils.configurePagination(supplierTable, suppliersData, supplierPagination);
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
		
		em.setOriginalEmployee(e);
		
		return em;
	}
	
	private ProductTableDTO createProductData(Product product) {
		ProductTableDTO productTableDTO = new ProductTableDTO();
		
		productTableDTO.setSku(product.getSku());
		productTableDTO.setDescription(product.getDescription());
		productTableDTO.setBuyPrice(product.getBuyPrice());
		
		if (product.getSupplier() != null) {
			productTableDTO.setSupplier(product.getSupplier().getCompanyName());
		} else {
			productTableDTO.setSupplier("--");
		}
		
		if (product.getSupplier() != null) {
			productTableDTO.setProductType(product.getProductType().getName());
		} else {
			productTableDTO.setProductType("--");
		}
		
		productTableDTO.setOriginalProduct(product);
		
		return productTableDTO;
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
		
		supplierTableDTO.setOriginalSupplier(supplier);
		
		return supplierTableDTO;
		
	}
	
	@FXML
	private void onReloadProductTable() {
		TableUtils.reloadTable(() -> configureProductTable());
	}
	
	@FXML
	private void onReloadEmployeeTable() {
		TableUtils.reloadTable(() -> configureEmployeeTable());
	}
	
	@FXML
	private void onReloadSupplierTable() {
		TableUtils.reloadTable(() -> configureSupplierTable());
	}
	
	@FXML
	private void onEditProductTable() throws Exception {
		ProductTableDTO productTableValue = productsTable.getSelectionModel().selectedItemProperty().get().getValue();
		TableUtils.editItemFromTable(productsTable, productTableValue.getOriginalProduct(), ProductNewController.PRODUCT_KEY, ProductNewController.PATH_FXML, ProductNewController.TITLE, ProductNewController.PATH_ICON);
	}
	
	@FXML
	private void onEditEmployeeTable() throws Exception {
		EmployeeTableDTO employeeTableValue = employeeTable.getSelectionModel().selectedItemProperty().get().getValue();
		TableUtils.editItemFromTable(employeeTable, employeeTableValue.getOriginalEmployee(), EmployeeNewController.EMPLOYEE_KEY, EmployeeNewController.PATH_FXML, EmployeeNewController.TITLE, EmployeeNewController.PATH_ICON);
	}
	
	@FXML
	private void onEditSupplierTable() throws Exception {
		SupplierTableDTO supplierTableValue = supplierTable.getSelectionModel().selectedItemProperty().get().getValue();
		TableUtils.editItemFromTable(supplierTable, supplierTableValue.getOriginalSupplier(), SupplierNewController.SUPPLIER_KEY, SupplierNewController.PATH_FXML, SupplierNewController.TITLE, SupplierNewController.PATH_ICON);
	}
	
	@FXML
	private void onRemoveProductTable() {
		ProductTableDTO productTableValue = productsTable.getSelectionModel().selectedItemProperty().get().getValue();
		WindowsUtils.createDefaultDialog(container, 
										 "Remove Product", "Are you sure you want to delete the " + productTableValue.getOriginalProduct().getName() + " ?", 
										 () -> { TableUtils.removeItemFromTable(productService, productTableValue.getOriginalProduct().getId(), productsTable, productsData, container); });
	}
	
	@FXML
	private void onRemoveEmployeeTable() {
		EmployeeTableDTO employeeTableValue = employeeTable.getSelectionModel().selectedItemProperty().get().getValue();
		WindowsUtils.createDefaultDialog(container, 
				 						 "Remove Employee", "Are you sure you want to delete the " + employeeTableValue.getOriginalEmployee().getUser().getName() + " ?", 
				 						 () -> { TableUtils.removeItemFromTable(employeeService, employeeTableValue.getOriginalEmployee().getId(), employeeTable, employeesData, container); });
	}
	
	@FXML
	private void onRemoveSupplierTable() {
		SupplierTableDTO supplierTableValue = supplierTable.getSelectionModel().selectedItemProperty().get().getValue();
		WindowsUtils.createDefaultDialog(container, 
				 						 "Remove Supplier", "Are you sure you want to delete the " + supplierTableValue.getOriginalSupplier().getCompanyName() + " ?", 
				 						 () -> { TableUtils.removeItemFromTable(supplierService, supplierTableValue.getOriginalSupplier().getId(), supplierTable, suppliersData, container); });
	}
	
	@FXML
	public void onNewProduct() throws Exception {
		WindowsUtils.openNewWindow(ProductNewController.PATH_FXML, ProductNewController.TITLE, ProductNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewEmployee() throws Exception {
		WindowsUtils.openNewWindow(EmployeeNewController.PATH_FXML, EmployeeNewController.TITLE, EmployeeNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewSupplier() throws Exception {
		WindowsUtils.openNewWindow(SupplierNewController.PATH_FXML, SupplierNewController.TITLE, SupplierNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewBrand() throws Exception {
		WindowsUtils.openNewWindow(BrandNewController.PATH_FXML, BrandNewController.TITLE, BrandNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
}
