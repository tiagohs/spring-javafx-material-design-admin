package com.tiagohs.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.tiagohs.model.Employee;
import com.tiagohs.model.Product;
import com.tiagohs.model.Sale;
import com.tiagohs.model.Supplier;
import com.tiagohs.model.dto.EmployeeTableDTO;
import com.tiagohs.model.dto.ProductTableDTO;
import com.tiagohs.model.dto.SalesTableDTO;
import com.tiagohs.model.dto.SupplierTableDTO;
import com.tiagohs.service.EmployeeService;
import com.tiagohs.service.ProductService;
import com.tiagohs.service.ReportsService;
import com.tiagohs.service.SaleService;
import com.tiagohs.service.SupplierService;
import com.tiagohs.service.TableService;
import com.tiagohs.service.UserService;
import com.tiagohs.util.EntityReportFactory;
import com.tiagohs.util.TableUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ReportsController implements BaseController{

	public static final String PATH_FXML = "/fxml/reports.fxml";
	public static final String TITLE = "Reports - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private JFXTreeTableView<SalesTableDTO> salesTable;
	
	@FXML
	private JFXTreeTableView<ProductTableDTO> productsTable;
	
	@FXML
	private JFXTreeTableView<EmployeeTableDTO> employeesTable;
	
	@FXML
	private JFXTreeTableView<SupplierTableDTO> suppliersTable;
	
	@FXML
	private JFXTreeTableColumn<SalesTableDTO, String> saleCodeColumn;
	
	@FXML
	private JFXTreeTableColumn<SalesTableDTO, String> saleIssueDateColumn;
	
	@FXML
	private JFXTreeTableColumn<SalesTableDTO, String> saleShipmentDateColumn;
	
	@FXML
	private JFXTreeTableColumn<SalesTableDTO, String> saleTotalColumn;
	
	@FXML
	private JFXTreeTableColumn<SalesTableDTO, String> saleNumItemsColumn;
	
	@FXML
	private JFXTreeTableColumn<SalesTableDTO, String> saleClientNameColumn;
	
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
    private JFXTreeTableColumn<EmployeeTableDTO, String> employeeNameColumn;
	
	@FXML
    private JFXTreeTableColumn<EmployeeTableDTO, String> employeeEmailColumn;
	
	@FXML
    private JFXTreeTableColumn<EmployeeTableDTO, String> employeeCpfColumn;
	
	@FXML
    private JFXTreeTableColumn<EmployeeTableDTO, String> productTypeColumn;
	
	@FXML
    private JFXTreeTableColumn<SupplierTableDTO, String> supplierNameColumn;
	
	@FXML
    private JFXTreeTableColumn<SupplierTableDTO, String> supplierEmailColumn;
	
	@FXML
    private JFXTreeTableColumn<SupplierTableDTO, String> supplierAddresColumn;
	
	@FXML
	private Pagination salesPagination;
	
	@FXML
	private Pagination productsPagination;
	
	@FXML
	private Pagination employeesPagination;
	
	@FXML
	private Pagination suppliersPagination;
	
	@FXML
	private Label totalUserLabel;
	
	@FXML
	private Label totalSaleLabel;
	
	@FXML
	private Label totalEmployeeLabel;
	
	@FXML
	private Label totalSuppliersLabel;
	
	@FXML
	private JFXSpinner salesSpinner;
	
	@FXML
	private JFXSpinner productsSpinner;
	
	@FXML
	private JFXSpinner employeesSpinner;
	
	@FXML
	private JFXSpinner suppliersSpinner;
	
	@FXML
	private JFXButton salesReportGenerate;
	
	@FXML
	private JFXButton productsReportGenerate;
	
	@FXML
	private JFXButton employeesReportGenerate;
	
	@FXML
	private JFXButton supplierReportGenerate;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private ReportsService reportsService;
	
	private ObservableList<ProductTableDTO> productsData;
	private ObservableList<EmployeeTableDTO> employeesData;
	private ObservableList<SupplierTableDTO> suppliersData;
	private ObservableList<SalesTableDTO> salesData;
	
	private List<TableService> tableService;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		
		configureLabels();
		configureTables();
	}
	
	private void configureLabels() {
		WindowsUtils.setTextInLabel(totalUserLabel, String.valueOf(userService.getTotalUsers()));
		WindowsUtils.setTextInLabel(totalSaleLabel, String.valueOf(saleService.getTotalSales()));
		WindowsUtils.setTextInLabel(totalEmployeeLabel, String.valueOf(employeeService.getTotalEmployees()));
		WindowsUtils.setTextInLabel(totalSuppliersLabel, String.valueOf(supplierService.getTotalSuppliers()));
	}
	
	private void configureTables() {
		
		tableService = new ArrayList<TableService>();
		
		tableService.add(new TableService(() -> configureProductTable()));
		tableService.add(new TableService(() -> configureEmployeeTable()));
		tableService.add(new TableService(() -> configureSupplierTable()));
		tableService.add(new TableService(() -> configureSaleTable()));
		
		tableService.forEach(s -> { s.start(); });
	}
	
	private void configureProductTable() {
		TableUtils.setupColumn(productSkuColumn, ProductTableDTO::getSku);
		TableUtils.setupColumn(productSupplierColumn, ProductTableDTO::getSupplier);
		TableUtils.setupColumn(productBuyPriceColumn, ProductTableDTO::getBuyPrice);
		TableUtils.setupColumn(productProductTypeColumn, ProductTableDTO::getProductType);
		TableUtils.setupColumn(productDescriptionColumn, ProductTableDTO::getDescription);
		
		productsData = TableUtils.filledDataOnTable(productService.findAll(), e -> createProductData(e));
		
		TableUtils.configurePagination(productsTable, productsData, productsPagination);
		productsTable.setShowRoot(false);
		productsTable.setEditable(true);
	}
	
	private void configureEmployeeTable() {
		
		TableUtils.setupColumn(employeeNameColumn, EmployeeTableDTO::getName);
		TableUtils.setupColumn(employeeEmailColumn, EmployeeTableDTO::getEmail);
		TableUtils.setupColumn(employeeCpfColumn, EmployeeTableDTO::getCpf);
		
		employeesData = TableUtils.filledDataOnTable(employeeService.findAll(), e -> createEmployeeData(e));
		
		TableUtils.configurePagination(employeesTable, employeesData, employeesPagination);
		employeesTable.setShowRoot(false);
	}
	
	private void configureSupplierTable() {
		TableUtils.setupColumn(supplierNameColumn, SupplierTableDTO::getCompanyName);
		TableUtils.setupColumn(supplierEmailColumn, SupplierTableDTO::getEmail);
		TableUtils.setupColumn(supplierAddresColumn, SupplierTableDTO::getAdress);
		
		suppliersData = TableUtils.filledDataOnTable(supplierService.findAll(), e -> createSupplierData(e));
		
		TableUtils.configurePagination(suppliersTable, suppliersData, suppliersPagination);
		suppliersTable.setShowRoot(false);
	}
	
	private void configureSaleTable() {
		TableUtils.setupColumn(saleCodeColumn, SalesTableDTO::getCode);
		TableUtils.setupColumn(saleShipmentDateColumn, SalesTableDTO::getShipmentDate);
		TableUtils.setupColumn(saleIssueDateColumn, SalesTableDTO::getIssueDate);
		TableUtils.setupColumn(saleClientNameColumn, SalesTableDTO::getClient);
		TableUtils.setupColumn(saleNumItemsColumn, SalesTableDTO::getTotalUnits);
		TableUtils.setupColumn(saleTotalColumn, SalesTableDTO::getTotal);
		
		salesData = TableUtils.filledDataOnTable(saleService.findAll(), e -> createSaleData(e));
		
		TableUtils.configurePagination(salesTable, salesData, salesPagination);
		salesTable.setShowRoot(false);
		salesTable.setEditable(true);
	}
	
	private EmployeeTableDTO createEmployeeData(Employee e) {
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
	
	private SalesTableDTO createSaleData(Sale sale) {
		SalesTableDTO salesTableDTO = new SalesTableDTO();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
		
		salesTableDTO.setCode(sale.getSaleCode());
		
		if (sale.getCliente() != null) {
			salesTableDTO.setClient(sale.getCliente().getUser().getName());
		}
		
		if (sale.getShipmentDate() != null) {
			salesTableDTO.setShipmentDate(formatter.format(sale.getShipmentDateFormatter().getTime()));
		}
		
		
		if (sale.getIssueDate() != null) {
			salesTableDTO.setIssueDate(formatter.format(sale.getIssueDateFormatter().getTime()));
		}
		
		salesTableDTO.setTotalUnits(sale.getTotalUnits());
		salesTableDTO.setTotal(sale.getTotal());
		salesTableDTO.setOriginalObject(sale);
		
		return salesTableDTO;
	}
	
	@FXML
	public void salesReport() throws Exception {
		createReport(EntityReportFactory.createSales(saleService.findAll()), "/reports/sales_template.jrxml", salesReportGenerate, salesSpinner);
	}
	
	@FXML
	public void productsReport() throws Exception {
		createReport(EntityReportFactory.createProducts(productService.findAll()), "/reports/products_template.jrxml", productsReportGenerate, productsSpinner);
	}
	
	@FXML
	public void employeesReport() throws Exception {
		createReport(EntityReportFactory.createEmployees(employeeService.findAll()), "/reports/employees_template.jrxml", employeesReportGenerate, employeesSpinner);
	}
	
	@FXML
	public void suppliersReport() throws Exception {
		createReport(EntityReportFactory.createSuppliers(supplierService.findAll()), "/reports/suppliers_template.jrxml", supplierReportGenerate, suppliersSpinner);
	}
	
	private <T> void createReport(List<T> data, String reportTemplatePath, JFXButton reportGenerate, JFXSpinner spinner) {
		Service<JasperPrint> createTaskService = reportsService.createJasperPrint(reportTemplatePath, data);
		
		createTaskService.setOnScheduled(e -> {
			reportGenerate.setDisable(true);
			spinner.setVisible(true);
		});
		
		createTaskService.setOnSucceeded(e -> {
			reportGenerate.setDisable(false);
			spinner.setVisible(false);
			
			JasperPrint jasperPrint = (JasperPrint) e.getSource().getValue();
			
			try {
				HashMap<String, JasperPrint> parameters = new HashMap<String, JasperPrint>();
				parameters.put(ReportViewerController.JASPER_PRINT, jasperPrint);
				
				WindowsUtils.openNewWindow(ReportViewerController.PATH_FXML, ReportViewerController.TITLE, ReportViewerController.PATH_ICON, parameters, Modality.APPLICATION_MODAL);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		createTaskService.start();
	}
}
