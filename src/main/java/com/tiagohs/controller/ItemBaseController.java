package com.tiagohs.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.tiagohs.model.Item;
import com.tiagohs.model.Product;
import com.tiagohs.service.ProductService;
import com.tiagohs.util.ValidatorUtils;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

@Controller
@Scope("prototype")
public class ItemBaseController implements IItemBaseController {
	
	public static final String ITEM_KEY = "item_key";
	public static final String PATH_FXML = "/fxml/item_base.fxml";
	
	@FXML
	private JFXComboBox<Product> itemsComboBox;
	
	@FXML
	private JFXTextField quantityTextField;
	
	@FXML
	private JFXTextField priceTextField;
	
	@FXML
	private JFXTextField discountTextField;
	
	@FXML
	private JFXTextField taxTextField;
	
	@FXML
	private Label totalLabel;
	
	private Item item;
	private SalesNewController salesNewController;
	
	@Autowired
	private ProductService productService;
	
	public <T> void init(SalesNewController salesNewController, HashMap<String, T> parameters) {
		this.salesNewController = salesNewController;
		
		fillComboBoxs();
		checkParameters(parameters);
		validateTextFields();
		watchEvents();
	}
	
	private <T> void checkParameters(HashMap<String, T> parameters) {
		if (parameters != null) {
			this.item = (Item) parameters.get(ITEM_KEY);
			updateTextFields();
		}
	}

	private void updateTextFields() {
		
		WindowsUtils.setTextInTextField(quantityTextField, item.getQuantity());
		WindowsUtils.setTextInTextField(priceTextField, item.getProduct().getBuyPrice());
		WindowsUtils.setTextInTextField(discountTextField, item.getDiscount());
		WindowsUtils.setTextInTextField(taxTextField, item.getTax());
		
		WindowsUtils.setTextInLabel(totalLabel, item.getTotalPrice());
		
		WindowsUtils.setSelectedComboBoxItem(itemsComboBox, item.getProduct());
		
	}

	private void validateTextFields() {
		
		ValidatorUtils.addDecimalValidator(discountTextField);
		ValidatorUtils.addDecimalValidator(taxTextField);
		ValidatorUtils.addNumberOnlyValidator(quantityTextField);
	}
	
	private void fillComboBoxs() {
		WindowsUtils.addComboBoxItens(itemsComboBox, productService);
	}
	
	private void watchEvents() {
		
		WindowsUtils.onTextFieldTextChange(quantityTextField, (observable, oldValue, newValue) -> onQuantityChange(newValue));
		WindowsUtils.onTextFieldTextChange(discountTextField, (observable, oldValue, newValue) -> onDiscountChange(newValue));
		WindowsUtils.onTextFieldTextChange(taxTextField, (observable, oldValue, newValue) -> onTaxChange(newValue));
		
		WindowsUtils.onComboBoxItemSelected(itemsComboBox, item -> onSelect(item));
	}
	
	private void onQuantityChange(String value) {
		if (!value.trim().isEmpty()) {
			item.setQuantity(Integer.valueOf(value));
			onUpdate();
		}
		
		
	}
	
	private void onDiscountChange(String value) {
		if (!value.trim().isEmpty()) {
			item.setDiscount(Double.valueOf(value));
			onUpdate();
		}
		
		
	}
	
	private void onTaxChange(String value) {
		if (!value.trim().isEmpty()) {
			item.setTax(Double.valueOf(value));
			onUpdate();
		}
		
		
	}
	
	private void onSelect(Product product) {
		if (item == null)
			item = new Item();
		
		item.setProduct(product);
		
		if (WindowsUtils.isTextFieldEmpty(quantityTextField)) {
			item.setQuantity(1);
			quantityTextField.setText("1");
		}
		
		priceTextField.setText(String.format("R$ %.2f", item.getProduct().getBuyPrice()));
		
		salesNewController.addItem(item);
		onUpdate();
	}
	
	private void onUpdate() {
		item.setTotalPrice( (item.getProduct().getBuyPrice() * item.getQuantity()) );
		
		if (!WindowsUtils.isTextFieldEmpty(taxTextField)) {
			double tax = item.getTax() / 100;
			double priceTax = ( item.getTotalPrice() * tax );
			
			item.setTotalPrice( ( item.getTotalPrice() + priceTax ) );
		}
			
		if (!WindowsUtils.isTextFieldEmpty(discountTextField)) {
			double discount = item.getDiscount() / 100;
			double priceDiscount = ( item.getTotalPrice() * discount );
			
			item.setTotalPrice( ( item.getTotalPrice() - priceDiscount ) );
		}
		
		totalLabel.setText(String.format("R$ %.2f", item.getTotalPrice()));
		
		salesNewController.updateValues();
	}
	
	@Override
	public double getTotal() {
		return item.getTotalPrice();
	}

	@Override
	public double getQuantity() {
		return item.getQuantity();
	}

}
