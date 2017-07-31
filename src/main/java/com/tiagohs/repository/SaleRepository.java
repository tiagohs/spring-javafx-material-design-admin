package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagohs.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
