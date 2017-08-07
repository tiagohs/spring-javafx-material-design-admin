package com.tiagohs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Brand;
import com.tiagohs.repository.BrandRepository;

@Service("brandService")
public class BrandServiceImpl extends BaseCrudService<Brand, JpaRepository<Brand,Long>> implements BrandService {
	
	private BrandRepository brandRepository;
	
	@Autowired
	public BrandServiceImpl(BrandRepository brandRepository) {
		super(brandRepository);
		
		this.brandRepository = brandRepository;
	}

	public BrandRepository getBrandRepository() {
		return brandRepository;
	}

	public void setBrandRepository(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}
	
}
