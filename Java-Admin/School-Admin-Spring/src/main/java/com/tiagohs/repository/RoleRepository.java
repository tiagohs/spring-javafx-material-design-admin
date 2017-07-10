package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiagohs.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByRole(String role);
}
