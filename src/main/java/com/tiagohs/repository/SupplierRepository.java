package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiagohs.model.Supplier;

@Repository("supplierRepository")
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
