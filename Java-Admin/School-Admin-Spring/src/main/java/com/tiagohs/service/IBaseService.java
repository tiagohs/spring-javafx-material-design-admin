package com.tiagohs.service;

import java.util.List;

public interface IBaseService<T> {
	public T save(T obj) throws NullPointerException;
	public List<T> findAll();
	public void delete(long id) throws Exception;
	public T find(long id) throws Exception;
}
