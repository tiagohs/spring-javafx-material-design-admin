package com.tiagohs.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Sale;
import com.tiagohs.repository.SaleRepository;

@Service("saleService")
public class SaleServiceImpl extends BaseService<Sale, JpaRepository<Sale,Long>> implements SaleService {
	
	private SaleRepository saleRepository;
	
	public SaleServiceImpl(SaleRepository repository) {
		super(repository);
		
		this.saleRepository = repository;
	}
	
	public SaleRepository getSaleRepository() {
		return saleRepository;
	}

	@Override
	public List<Sale> findAllOpenSales() {
		return saleRepository.findAllOpenSales();
	}

	@Override
	public List<Sale> findAllFinalizedSales() {
		return saleRepository.findAllFinalizedSales();
	}

	@Override
	public Long getTotalSales() {
		return saleRepository.getTotalSales();
	}

	@Override
	public List<Sale> findSaleByMonth(Calendar date) {
		
		if (date != null) {
			Integer month = date.get(Calendar.MONTH);
			Integer year = date.get(Calendar.YEAR);
			
			return saleRepository.findSalesByMonth(month);
		}
		
		return null;
	}

	@Override
	public Long getTotalSalesByMonth(Calendar date) {
		
		if (date != null) {
			Integer month = date.get(Calendar.MONTH);
			Integer year = date.get(Calendar.YEAR);
			
			return saleRepository.getTotalSalesByMonth(month);
		}
		
		return 0L;
	}
	
	
	
	
}
