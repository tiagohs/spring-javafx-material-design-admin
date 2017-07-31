package com.tiagohs.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.tiagohs.model.Brand;
import com.tiagohs.model.Product;
import com.tiagohs.model.ProductType;
import com.tiagohs.model.Supplier;
import com.tiagohs.service.BrandService;
import com.tiagohs.service.ProductService;
import com.tiagohs.service.ProductTypeService;
import com.tiagohs.service.SupplierService;
import com.tiagohs.util.EntityFactory;
import com.tiagohs.util.ValidatorUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@Controller
public class ProductNewController implements BaseController {
	
	public static final String PRODUCT_KEY = "product_key";
	
	public static final String PATH_FXML = "/fxml/new_product.fxml";
	public static final String TITLE = "New Product - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private StackPane container;
	
	@FXML
	private JFXTextField productNameTextField;
	
	@FXML
	private JFXTextField skuTextField;
	
	@FXML
	private JFXTextArea descriptionTextArea;
	
	@FXML
	private JFXComboBox<Supplier> supplierComboBox;
	
	@FXML
	private JFXComboBox<ProductType> productTypeComboBox;
	
	@FXML
	private JFXComboBox<Brand> brandComboBox;
	
	@FXML
	private JFXTextField tagsTextField;
	
	@FXML
	private JFXTextField initialCostPriceTextField;
	
	@FXML
	private JFXTextField buyPriceTextField;
	
	@FXML
	private JFXTextField wholesalePriceTextField;
	
	@FXML
	private JFXTextField retailPriceTextField;
	
	@FXML
	private JFXTextField weightTextField;
	
	@FXML
	private JFXTextField initialStockTextField;
	
	@FXML
	private JFXButton saveButton;
	
	@FXML
	private JFXButton cancelButton;
	
	@FXML
	private JFXButton helpButton;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ProductTypeService productTypeService;
	
	private Stage productNewStage;
	private Product product;
	
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		this.productNewStage = stage;
		
		checkParameters(parameters);
		validateTextFields();
		watchEvents();
		fillComboBoxes();
		
	}
	
	private <T> void checkParameters( HashMap<String, T> parameters) {
		if (parameters != null) {
			this.product = (Product) parameters.get(PRODUCT_KEY);
			updateTextFields();
		}
	}
	
	private void updateTextFields() {
		
		WindowsUtils.setTextInTextField(skuTextField, product.getSku());
		WindowsUtils.setTextInTextField(productNameTextField, product.getName());
		WindowsUtils.setTextInTextArea(descriptionTextArea, product.getDescription());
		WindowsUtils.setTextInTextField(initialCostPriceTextField, product.getInitialCostPrice());
		WindowsUtils.setTextInTextField(buyPriceTextField, product.getBuyPrice());
		WindowsUtils.setTextInTextField(wholesalePriceTextField, product.getWholesalePrice());
		WindowsUtils.setTextInTextField(retailPriceTextField, product.getRetailPrice());
		WindowsUtils.setTextInTextField(weightTextField, product.getWeight());
		WindowsUtils.setTextInTextField(initialStockTextField, product.getInitialStock());
		
		WindowsUtils.setSelectedComboBoxItem(brandComboBox, product.getBrand());
		WindowsUtils.setSelectedComboBoxItem(productTypeComboBox, product.getProductType());
		WindowsUtils.setSelectedComboBoxItem(supplierComboBox, product.getSupplier());
	}
	
	private void validateTextFields() {
		ValidatorUtils.addRequiredValidator(productNameTextField, "Product Name is Required!");
		ValidatorUtils.addRequiredValidator(skuTextField, "SKU is Required!");
		ValidatorUtils.addRequiredValidator(initialCostPriceTextField, "Initial Cost Price is Required!");
		ValidatorUtils.addRequiredValidator(buyPriceTextField, "Buy Price is Required!");
		ValidatorUtils.addRequiredValidator(initialStockTextField, "Initial Stock On Hand is Required!");
		
		ValidatorUtils.addNumberOnlyValidator(initialCostPriceTextField);
		ValidatorUtils.addNumberOnlyValidator(buyPriceTextField);
		ValidatorUtils.addNumberOnlyValidator(wholesalePriceTextField);
		ValidatorUtils.addNumberOnlyValidator(retailPriceTextField);
		ValidatorUtils.addNumberOnlyValidator(weightTextField);
		ValidatorUtils.addNumberOnlyValidator(initialStockTextField);

		WindowsUtils.validateTextField(productNameTextField);
		WindowsUtils.validateTextField(skuTextField);
		WindowsUtils.validateTextField(initialStockTextField);
		WindowsUtils.validateTextField(initialStockTextField);
	}
	
	private void watchEvents() {
		WindowsUtils.watchEvents(productNameTextField, v -> watch());
		WindowsUtils.watchEvents(initialCostPriceTextField, v -> watch());
		WindowsUtils.watchEvents(initialStockTextField, v -> watch());
	}
	
	private void fillComboBoxes() {
		WindowsUtils.addComboBoxItens(brandComboBox, brandService.findAll());
		WindowsUtils.addComboBoxItens(productTypeComboBox, productTypeService.findAll());
		WindowsUtils.addComboBoxItens(supplierComboBox, supplierService.findAll());
	}
	
	private void watch() {
		if (!(WindowsUtils.isTextFieldEmpty(productNameTextField)) &&
			!(WindowsUtils.isTextFieldEmpty(initialStockTextField)) &&
			!(WindowsUtils.isTextFieldEmpty(initialCostPriceTextField))) {
			saveButton.setDisable(false);
		} else {
			saveButton.setDisable(true);
		}
		
	}
	
	@FXML
	public void onSave() {
		
		try {
			productService.save(EntityFactory.createProduct(product, WindowsUtils.getTextFromTextField(skuTextField), 
															WindowsUtils.getTextFromTextField(productNameTextField), 
															WindowsUtils.getTextFromTextArea(descriptionTextArea), 
															WindowsUtils.getDoubleFromTextField(initialCostPriceTextField), 
															WindowsUtils.getDoubleFromTextField(buyPriceTextField), 
															WindowsUtils.getDoubleFromTextField(wholesalePriceTextField), 
															WindowsUtils.getDoubleFromTextField(retailPriceTextField), 
															WindowsUtils.getDoubleFromTextField(weightTextField), 
															WindowsUtils.getDoubleFromTextField(initialStockTextField), 
															product == null ? new Date() : product.getCreatedAt(), 
															new Date(), 
															(Supplier) WindowsUtils.getSelectedComboBoxItem(supplierComboBox), 
															(Brand) WindowsUtils.getSelectedComboBoxItem(brandComboBox), 
															(ProductType) WindowsUtils.getSelectedComboBoxItem(productTypeComboBox), 
															null, 
															null));
			WindowsUtils.createDefaultDialog(container, "Sucess", "Product save with sucess.", () -> { productNewStage.close(); });
		} catch (Exception e) {
			WindowsUtils.createDefaultDialog(container, "Error", "Error saving product, try again.", () -> {});
		}
	}
	
	@FXML
	public void onCancel() {
		productNewStage.close();
	}
	
	@FXML
	public void onHelp() {
		
	}
	
}
