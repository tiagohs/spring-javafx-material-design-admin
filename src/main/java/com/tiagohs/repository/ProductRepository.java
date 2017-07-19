package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiagohs.model.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {

}
