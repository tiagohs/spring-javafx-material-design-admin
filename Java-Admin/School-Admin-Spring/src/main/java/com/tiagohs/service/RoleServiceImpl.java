package com.tiagohs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Role;
import com.tiagohs.repository.RoleRepository;

@Service("roleService")
public class RoleServiceImpl extends BaseService<Role, JpaRepository<Role,Long>> implements RoleService {
	
	private RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		super(roleRepository);
		
		this.roleRepository = roleRepository;
	}
	
	@Override
	public Role findByRole(String role) {
		return roleRepository.findByRole(role);
	}


}
