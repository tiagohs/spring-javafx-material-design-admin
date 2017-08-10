package com.tiagohs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tiagohs.controller.SaleTableController.TypeSaleTable;
import com.tiagohs.model.Sale;
import com.tiagohs.service.SaleService;
import com.tiagohs.util.WindowsUtils;

import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Controller
public class SalesController extends BaseController {

	public static final String PATH_FXML = "/fxml/sales.fxml";
	public static final String SALES_TITLE_KEY = "sales.title";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private AnchorPane allContainer;
	
	@FXML
	private AnchorPane openContainer;
	
	@FXML
	private AnchorPane finalizedContainer;
	
	@Autowired
	private SaleService saleService;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		allContainer.getChildren().add(createAllTable(TypeSaleTable.ALL));
		openContainer.getChildren().add(createAllTable(TypeSaleTable.OPEN));
		finalizedContainer.getChildren().add(createAllTable(TypeSaleTable.FINALIZED));
	}
	
	@Override
	protected void onClose() {
		saleService.onClose();
	}
	
	private StackPane createAllTable(TypeSaleTable type) {
		Scene scene = null;
		
		try {
			FXMLLoader loader = WindowsUtils.loadFxml("/fxml/sale_table.fxml");
			scene = new Scene(loader.load());
			
			SaleTableController controller = loader.getController();
			controller.init(type, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return (StackPane) scene.lookup("#container");
	}
	
	public Service<List<Sale>> getSales(TypeSaleTable type, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		
		switch(type) {
			case ALL:
				return saleService.findAll(onSucess, beforeStart);
			case FINALIZED:
				return saleService.findAllFinalizedSales(onSucess, beforeStart);
			case OPEN:
				return saleService.findAllOpenSales(onSucess, beforeStart);
		}
		
		return null;
	}
	
	@FXML
	public void onNewSale() throws Exception {
		WindowsUtils.openNewWindow(SalesNewController.PATH_FXML, getWindowTitle(SalesNewController.NEW_SALE_TITLE_KEY), SalesNewController.PATH_ICON, null, Modality.APPLICATION_MODAL);
	}
	
	
}
