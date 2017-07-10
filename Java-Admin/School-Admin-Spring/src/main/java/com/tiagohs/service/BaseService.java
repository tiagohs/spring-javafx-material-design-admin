package com.tiagohs.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T, R extends JpaRepository<T, Long>> {
	
	private R repository;
	
	public BaseService(R repository) {
		this.repository = repository;
	}
	
	public T save(T obj) throws NullPointerException {
		
		if (obj == null) {
			throw new NullPointerException();
		}
		
		repository.save(obj);
		
		return obj;
	}
	
	public List<T> findAll() {
		return repository.findAll();
	}
	
	
	public void delete(long id) throws Exception {
		repository.delete(id);
	}
	
	
	public T find(long id) throws Exception {
		return repository.findOne(id);
	}
	
}
