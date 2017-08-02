package com.tiagohs.service;

import com.tiagohs.model.Supplier;

public interface SupplierService extends IBaseService<Supplier> {
	
	Long getTotalSuppliers();
}
