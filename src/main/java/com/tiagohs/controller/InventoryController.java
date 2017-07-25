package com.tiagohs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.tiagohs.model.Employee;
import com.tiagohs.model.dto.EmployeeTableDTO;
import com.tiagohs.model.dto.ProductTableDTO;
import com.tiagohs.model.dto.SupplierTableDTO;
import com.tiagohs.service.EmployeeService;
import com.tiagohs.service.ProductService;
import com.tiagohs.service.SupplierService;
import com.tiagohs.util.ITableDataCreator;
import com.tiagohs.util.TableUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private JFXTreeTableColumn<EmployeeTableDTO, String> employeeNameColumn;
	
	@FXML
    private JFXTreeTableColumn<EmployeeTableDTO, String> employeeEmailColumn;
	
	@FXML
    private JFXTreeTableColumn<EmployeeTableDTO, String> employeeCpfColumn;
	
	@FXML
    private JFXTreeTableColumn<EmployeeTableDTO, String> employeeAdressColumn;
	
	@FXML
	private JFXTreeTableView<SupplierTableDTO> supplierTable;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private SupplierService supplierService;
	
	public void init(Stage stage) {
		
		configureEmployeeTable();
	}
	
	private void configureProductTable() {
		
	}
	
	private void configureEmployeeTable() {
		
		TableUtils.setupCellValueFactory(employeeNameColumn, EmployeeTableDTO::getName);
		TableUtils.setupCellValueFactory(employeeEmailColumn, EmployeeTableDTO::getEmail);
		TableUtils.setupCellValueFactory(employeeCpfColumn, EmployeeTableDTO::getCpf);
		TableUtils.setupCellValueFactory(employeeAdressColumn, EmployeeTableDTO::getAdress);
		
		ObservableList<EmployeeTableDTO> dummyData = TableUtils.filledDataOnTable(employeeService.findAll(), e -> createEmplyeeData(e));
		
		employeeTable.setRoot(new RecursiveTreeItem<>(dummyData, RecursiveTreeObject::getChildren));
		employeeTable.setShowRoot(false);
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
	
	private void configureSupplierTable() {
		
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
