package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiagohs.model.Client;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Long> {

}
