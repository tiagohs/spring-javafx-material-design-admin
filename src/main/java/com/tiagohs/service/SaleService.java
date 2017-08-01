package com.tiagohs.service;

import java.util.Calendar;
import java.util.List;

import com.tiagohs.model.Sale;

public interface SaleService extends IBaseService<Sale> {
	
	List<Sale> findAllOpenSales();
	List<Sale> findAllFinalizedSales();
	Long getTotalSales();
	List<Sale> findSaleByMonth(Calendar date);
	Long getTotalSalesByMonth(Calendar date);
}
