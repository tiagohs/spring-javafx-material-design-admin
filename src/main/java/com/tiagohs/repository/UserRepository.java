package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tiagohs.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT COUNT(u) FROM User u")
	Long getTotalUsers();
}
