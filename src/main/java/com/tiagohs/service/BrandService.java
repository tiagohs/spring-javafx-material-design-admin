package com.tiagohs.service;

import org.springframework.stereotype.Repository;

import com.tiagohs.model.Brand;

@Repository("brandService")
public interface BrandService extends IBaseService<Brand> {

}
