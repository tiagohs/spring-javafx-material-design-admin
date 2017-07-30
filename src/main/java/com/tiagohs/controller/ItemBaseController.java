package com.tiagohs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.tiagohs.model.Item;
import com.tiagohs.model.Product;
import com.tiagohs.service.ProductService;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

@Controller
public class ItemBaseController {
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
	private double totalPrice;
	private SalesNewController salesNewController;
	
	@Autowired
	private ProductService productService;
	
	public void init(SalesNewController salesNewController) {
		this.salesNewController = salesNewController;
		
		WindowsUtils.addComboBoxItens(itemsComboBox, productService.findAll());
		WindowsUtils.onComboBoxItemSelected(itemsComboBox, p -> onSelect(p));
	}
	
	private void onSelect(Product product) {
		item = new Item();
		
		item.setProduct(product);
		
		priceTextField.setText(String.format("R$ %.2f", product.getBuyPrice()));
		quantityTextField.setText("1");
	}
	
	
	
	public double getTotalPrice() {
		return totalPrice;
	}

}
