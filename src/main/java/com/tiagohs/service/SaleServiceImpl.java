package com.tiagohs.service;

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
}
