package com.tiagohs.service;

import java.util.List;

import com.tiagohs.model.Role;

public interface RoleService extends IBaseService<Role> {
	
	List<Role> findByRole(String role);
}
