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
import com.tiagohs.model.Brand;
import com.tiagohs.model.Employee;
import com.tiagohs.model.Product;
import com.tiagohs.model.Supplier;
import com.tiagohs.model.dto.BrandTableDTO;
import com.tiagohs.model.dto.EmployeeTableDTO;
import com.tiagohs.model.dto.ProductTableDTO;
import com.tiagohs.model.dto.SupplierTableDTO;
import com.tiagohs.service.BrandService;
import com.tiagohs.service.EmployeeService;
import com.tiagohs.service.ProductService;
import com.tiagohs.service.SupplierService;
import com.tiagohs.service.TableService;
import com.tiagohs.util.TableUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

@SuppressWarnings("unchecked")
@Controller
public class InventoryController extends BaseController {
	
	public static final String PATH_FXML = "/fxml/inventory.fxml";
	public static final String INVENTORY_TITLE_KEY = "inventory.title";
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
	private JFXTreeTableView<BrandTableDTO> brandTable;
	
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
    private JFXTreeTableColumn<BrandTableDTO, String> brandNameColumn;
	
	@FXML
    private JFXTreeTableColumn<BrandTableDTO, String> brandEmailColumn;
	
	@FXML
    private JFXTreeTableColumn<BrandTableDTO, String> brandAdditionalInformationColumn;
	
	@FXML
	private Pagination productPagination;
	
	@FXML
	private Pagination employeePagination;
	
	@FXML
	private Pagination supplierPagination;
	
	@FXML
	private Pagination brandPagination;
	
	@FXML
    private JFXTextField productSearchTextField;
	
	@FXML
    private JFXTextField employeeSearchTextField;
	
	@FXML
    private JFXTextField supplierSearchTextField;
	
	@FXML
    private JFXTextField brandSearchTextField;
	
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
    private JFXButton brandEditButton;
	
    @FXML
    private JFXButton brandRemoveButton;
    
	@Autowired
	private ProductService productService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private BrandService brandService;
	
	private ObservableList<ProductTableDTO> productsData;
	
	private ObservableList<EmployeeTableDTO> employeesData;
	
	private ObservableList<SupplierTableDTO> suppliersData;
	
	private ObservableList<BrandTableDTO> brandsData;
	
	private List<TableService> tableService;
	
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		configureTables();
		configureSearchs();
	}

	@Override
	protected void onClose() {
		productService.onClose();
		supplierService.onClose();
		employeeService.onClose();
		brandService.onClose();
	}
	
	private void configureTables() {
		tableService = new ArrayList<TableService>();
		
		tableService.add(new TableService(() -> configureProductTable()));
		tableService.add(new TableService(() -> configureEmployeeTable()));
		tableService.add(new TableService(() -> configureSupplierTable()));
		tableService.add(new TableService(() -> configureBrandTable()));
		
		tableService.forEach(s -> { s.start(); });
		
		TableUtils.configureEditAndRemoveState(productsTable, productEditButton, productRemoveButton);
		TableUtils.configureEditAndRemoveState(employeeTable, employeeEditButton, employeeRemoveButton);
		TableUtils.configureEditAndRemoveState(supplierTable, supplierEditButton, supplierRemoveButton);
		TableUtils.configureEditAndRemoveState(brandTable, brandEditButton, brandRemoveButton);
	}
	
	private void configureSearchs() {
		TableUtils.configureTableSearch(productSearchTextField, productsTable, (productProp, newVal) -> configureProductSearchTest(productProp, newVal));
		TableUtils.configureTableSearch(employeeSearchTextField, employeeTable, (employeeProp, newVal) -> configureEmployeeSearchTest(employeeProp, newVal));
		TableUtils.configureTableSearch(supplierSearchTextField, supplierTable, (supplierProp, newVal) -> configureSupplierSearchTest(supplierProp, newVal));
		TableUtils.configureTableSearch(brandSearchTextField, brandTable, (brandProp, newVal) -> configureBrandSearchTest(brandProp, newVal));
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
	
	private boolean configureBrandSearchTest(TreeItem<BrandTableDTO> brandProp, String value) {
		final BrandTableDTO brand = brandProp.getValue();
        return brand.getName().get().contains(value)
            || brand.getEmail().get().contains(value)
            || brand.getAdditionalInformation().get().contains(value);
	}
	
	private void configureProductTable() {
		TableUtils.setupColumn(productSkuColumn, ProductTableDTO::getSku);
		TableUtils.setupColumn(productSupplierColumn, ProductTableDTO::getSupplier);
		TableUtils.setupColumn(productBuyPriceColumn, ProductTableDTO::getBuyPrice);
		TableUtils.setupColumn(productProductTypeColumn, ProductTableDTO::getProductType);
		TableUtils.setupColumn(productDescriptionColumn, ProductTableDTO::getDescription);
		
		productService.findAll(e -> {
			TableUtils.configureTable((List<Product>) e.getSource().getValue(), productsData, productsTable, productPagination, en -> createProductData(en));
		}, null);
	}
	
	private void configureEmployeeTable() {
		
		TableUtils.setupColumn(employeeNameColumn, EmployeeTableDTO::getName);
		TableUtils.setupColumn(employeeEmailColumn, EmployeeTableDTO::getEmail);
		TableUtils.setupColumn(employeeCpfColumn, EmployeeTableDTO::getCpf);
		TableUtils.setupColumn(employeeAdressColumn, EmployeeTableDTO::getAdress);
		
		employeeService.findAll(e -> {
			TableUtils.configureTable((List<Employee>) e.getSource().getValue(), employeesData, employeeTable, employeePagination, en -> createEmplyeeData(en));
		}, null);
	}
	
	private void configureSupplierTable() {
		TableUtils.setupColumn(supplierNameColumn, SupplierTableDTO::getCompanyName);
		TableUtils.setupColumn(supplierEmailColumn, SupplierTableDTO::getEmail);
		TableUtils.setupColumn(supplierAddresColumn, SupplierTableDTO::getAdress);
		
		supplierService.findAll(e -> {
			TableUtils.configureTable((List<Supplier>) e.getSource().getValue(), suppliersData, supplierTable, supplierPagination, en -> createSupplierData(en));
		}, null);
	}
	

	private void configureBrandTable() {
		TableUtils.setupColumn(brandNameColumn, BrandTableDTO::getName);
		TableUtils.setupColumn(brandEmailColumn, BrandTableDTO::getEmail);
		TableUtils.setupColumn(brandAdditionalInformationColumn, BrandTableDTO::getAdditionalInformation);
		
		brandService.findAll(e -> {
			TableUtils.configureTable((List<Brand>) e.getSource().getValue(), brandsData, brandTable, brandPagination, en -> createBrandData(en));
		}, null);
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
	
	private BrandTableDTO createBrandData(Brand brand) {
		BrandTableDTO brandTableDTO = new BrandTableDTO();
		
		brandTableDTO.setName(brand.getName());
		brandTableDTO.setEmail(brand.getEmail());
		brandTableDTO.setAdditionalInformation(brand.getAdditionalInformation());
		brandTableDTO.setOriginalBrand(brand);
		
		return brandTableDTO;
	}
	
	@FXML
	private void onReloadProductTable() {
		TableUtils.reloadTable(() -> configureProductTable());
		TableUtils.updateEditAndRemoveButtonState(productsTable, productEditButton, productRemoveButton);
	}
	
	@FXML
	private void onReloadEmployeeTable() {
		TableUtils.reloadTable(() -> configureEmployeeTable());
		TableUtils.updateEditAndRemoveButtonState(employeeTable, employeeEditButton, employeeRemoveButton);
	}
	
	@FXML
	private void onReloadSupplierTable() {
		TableUtils.reloadTable(() -> configureSupplierTable());
		TableUtils.updateEditAndRemoveButtonState(supplierTable, supplierEditButton, supplierRemoveButton);
	}
	
	
	@FXML
	private void onReloadBrandTable() {
		TableUtils.reloadTable(() -> configureBrandTable());
		TableUtils.updateEditAndRemoveButtonState(brandTable, brandEditButton, brandRemoveButton);
	}
	
	@FXML
	private void onEditProductTable() throws Exception {
		ProductTableDTO productTableValue = productsTable.getSelectionModel().selectedItemProperty().get().getValue();
		TableUtils.editItemFromTable(productsTable, productTableValue.getOriginalProduct(), ProductNewController.PRODUCT_KEY, ProductNewController.PATH_FXML, getWindowTitle(ProductNewController.NEW_PRODUCT_TITLE_KEY), ProductNewController.PATH_ICON);
	}
	
	@FXML
	private void onEditEmployeeTable() throws Exception {
		EmployeeTableDTO employeeTableValue = employeeTable.getSelectionModel().selectedItemProperty().get().getValue();
		TableUtils.editItemFromTable(employeeTable, employeeTableValue.getOriginalEmployee(), EmployeeNewController.EMPLOYEE_KEY, EmployeeNewController.PATH_FXML, getWindowTitle(EmployeeNewController.NEW_EMPLOYEE_TITLE_KEY), EmployeeNewController.PATH_ICON);
	}
	
	@FXML
	private void onEditSupplierTable() throws Exception {
		SupplierTableDTO supplierTableValue = supplierTable.getSelectionModel().selectedItemProperty().get().getValue();
		TableUtils.editItemFromTable(supplierTable, supplierTableValue.getOriginalSupplier(), SupplierNewController.SUPPLIER_KEY, SupplierNewController.PATH_FXML, getWindowTitle(SupplierNewController.NEW_SUPPLIER_TITLE_KEY), SupplierNewController.PATH_ICON);
	}
	
	@FXML
	private void onEditBrandTable() throws Exception {
		BrandTableDTO brandTableValue = brandTable.getSelectionModel().selectedItemProperty().get().getValue();
		TableUtils.editItemFromTable(brandTable, brandTableValue.getOriginalBrand(), BrandNewController.BRAND_KEY, BrandNewController.PATH_FXML, getWindowTitle(BrandNewController.NEW_BRAND_TITLE_KEY), BrandNewController.PATH_ICON);
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
	private void onRemoveBrandTable() {
		BrandTableDTO brandTableValue = brandTable.getSelectionModel().selectedItemProperty().get().getValue();
		WindowsUtils.createDefaultDialog(container, 
				 						 "Remove Supplier", "Are you sure you want to delete the " + brandTableValue.getOriginalBrand().getName() + " ?", 
				 						 () -> { TableUtils.removeItemFromTable(brandService, brandTableValue.getOriginalBrand().getId(), brandTable, brandsData, container); });
	}
	
	@FXML
	public void onNewProduct() throws Exception {
		WindowsUtils.openNewWindow(ProductNewController.PATH_FXML, getWindowTitle(ProductNewController.NEW_PRODUCT_TITLE_KEY), ProductNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewEmployee() throws Exception {
		WindowsUtils.openNewWindow(EmployeeNewController.PATH_FXML, getWindowTitle(EmployeeNewController.NEW_EMPLOYEE_TITLE_KEY), EmployeeNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewSupplier() throws Exception {
		WindowsUtils.openNewWindow(SupplierNewController.PATH_FXML, getWindowTitle(SupplierNewController.NEW_SUPPLIER_TITLE_KEY), SupplierNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
	
	@FXML
	public void onNewBrand() throws Exception {
		WindowsUtils.openNewWindow(BrandNewController.PATH_FXML, getWindowTitle(BrandNewController.NEW_BRAND_TITLE_KEY), BrandNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
}
