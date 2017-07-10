package com.tiagohs.service;

import com.tiagohs.model.Role;

public interface RoleService extends IBaseService<Role> {
	
	Role findByRole(String role);
}
