package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiagohs.model.ProductType;

@Repository("productTypeRepository")
public interface ProductTypeRepository extends JpaRepository<ProductType, Long>{

}
