package com.tiagohs.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.tiagohs.model.Sale;
import com.tiagohs.model.dto.SalesTableDTO;
import com.tiagohs.service.SaleService;
import com.tiagohs.util.TableService;
import com.tiagohs.util.TableUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;

@Controller
@Scope("prototype")
public class SaleTableController {
	
	enum TypeSaleTable { OPEN, ALL, FINALIZED };
	
	@FXML
	private StackPane container;
	
	@FXML
	private JFXTreeTableView<SalesTableDTO> salesTable;
	
	@FXML
	private JFXTreeTableColumn<SalesTableDTO, String> salesCodeColumn;
	
	@FXML
	private JFXTreeTableColumn<SalesTableDTO, String> salesShipmentDateColumn;
	
	@FXML
	private JFXTreeTableColumn<SalesTableDTO, String> salesIssueDateColumn;
	
	@FXML
	private JFXTreeTableColumn<SalesTableDTO, String> salesClientColumn;
	
	@FXML
	private JFXTreeTableColumn<SalesTableDTO, String> salesTotalUnitsColumn;
	
	@FXML
	private JFXTreeTableColumn<SalesTableDTO, String> salesTotalColumn;
	
	@FXML
	private JFXButton salesEditButton;
	
	@FXML
	private JFXButton salesRemoveButton;
	
	@FXML
	private JFXButton salesReloadButton;
	
	@FXML
	private JFXTextField salesSearchTextField;
	
	@FXML
	private Pagination salesPagination;
	
	@Autowired
	private SaleService saleService;
	
	private TypeSaleTable type;
	private ObservableList<SalesTableDTO> data;
    private TableService tableService;
    private SalesController salesController;
	
	public void init(TypeSaleTable type, SalesController salesController) {
		this.salesController = salesController;
		this.type = type;
		this.tableService = new TableService(() -> configureTable());
		this.tableService.start();
		
		configureSearch();
		
		TableUtils.configureEditAndRemoveState(salesTable, salesEditButton, salesRemoveButton);
	}

	private void configureTable() {
		TableUtils.setupColumn(salesCodeColumn, SalesTableDTO::getCode);
		TableUtils.setupColumn(salesShipmentDateColumn, SalesTableDTO::getShipmentDate);
		TableUtils.setupColumn(salesIssueDateColumn, SalesTableDTO::getIssueDate);
		TableUtils.setupColumn(salesClientColumn, SalesTableDTO::getClient);
		TableUtils.setupColumn(salesTotalUnitsColumn, SalesTableDTO::getTotalUnits);
		TableUtils.setupColumn(salesTotalColumn, SalesTableDTO::getTotal);
		
		data = TableUtils.filledDataOnTable(salesController.getSales(type), e -> createData(e));
		
		TableUtils.configurePagination(salesTable, data, salesPagination);
		salesTable.setShowRoot(false);
		salesTable.setEditable(true);
		
	}

	private SalesTableDTO createData(Sale sale) {
		SalesTableDTO salesTableDTO = new SalesTableDTO();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
		
		salesTableDTO.setCode(sale.getSaleCode());
		
		if (sale.getCliente() != null) {
			salesTableDTO.setClient(sale.getCliente().getUser().getName());
		}
		
		if (sale.getIssueDate() != null) {
			salesTableDTO.setShipmentDate(formatter.format(sale.getIssueDate()));
		}
		
		
		if (sale.getIssueDate() != null) {
			salesTableDTO.setIssueDate(formatter.format(sale.getIssueDate()));
		}
		
		salesTableDTO.setTotalUnits(sale.getTotalUnits());
		salesTableDTO.setTotal(sale.getTotal());
		salesTableDTO.setOriginalObject(sale);
		
		return salesTableDTO;
	}

	private void configureSearch() {
		TableUtils.configureTableSearch(salesSearchTextField, salesTable, (saleProp, newVal) -> configureSearchTest(saleProp, newVal));
	}

	private boolean configureSearchTest(TreeItem<SalesTableDTO> saleProp, String value) {
		final SalesTableDTO sale = saleProp.getValue();
		
        return sale.getCode().get().contains(value)
            || sale.getClient().get().contains(value)
            
            || sale.getTotal().get().contains(value)
            || sale.getTotalUnits().get().contains(value)
            || (sale.getIssueDate() != null || sale.getIssueDate().get().contains(value))
            || (sale.getShipmentDate() != null || sale.getShipmentDate().get().contains(value));
	}


	@FXML
	public void onEdiSalesTable() throws Exception {
		if (salesTable.getSelectionModel().selectedItemProperty().get() != null) {
			SalesTableDTO salesTableDTO = salesTable.getSelectionModel().selectedItemProperty().get().getValue();
			TableUtils.editItemFromTable(salesTable, salesTableDTO.getOriginalObject(), SalesNewController.SALE_KEY, SalesNewController.PATH_FXML, SalesNewController.TITLE, SalesNewController.PATH_ICON);
		} 
	}
	
	@FXML
	public void onRemoveSalesTable() {
		if (salesTable.getSelectionModel().selectedItemProperty().get() != null) {
			SalesTableDTO salesTableDTO = salesTable.getSelectionModel().selectedItemProperty().get().getValue();
			WindowsUtils.createDefaultDialog(container, 
											 "Remove Sale", "Are you sure you want to delete the sale " + salesTableDTO.getOriginalObject().getSaleCode() + " ?", 
											 () -> { TableUtils.removeItemFromTable(saleService, salesTableDTO.getOriginalObject().getId(), salesTable, data, container); });
		} 
	}
	
	@FXML
	public void onChangeStateSalesTable() {
		
	}
	
	@FXML
	public void onReloadSalesTable() {
		TableUtils.reloadTable(() -> configureTable());
		TableUtils.updateEditAndRemoveButtonState(salesTable, salesEditButton, salesRemoveButton);
	}
	
}
