package com.tiagohs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Supplier;
import com.tiagohs.repository.SupplierRepository;

@Service("supplierService")
public class SupplierServiceImpl extends BaseService<Supplier, JpaRepository<Supplier,Long>> implements SupplierService {
	
	private SupplierRepository supplierRepository;
	
	@Autowired
	public SupplierServiceImpl(SupplierRepository supplierRepository) {
		super(supplierRepository);
		
		this.supplierRepository = supplierRepository;
	}

	public SupplierRepository getSupplierRepository() {
		return supplierRepository;
	}

	public void setSupplierRepository(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}

	
}
