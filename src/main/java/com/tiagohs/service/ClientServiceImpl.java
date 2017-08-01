package com.tiagohs.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Client;
import com.tiagohs.repository.ClientRepository;

@Service("clientService")
public class ClientServiceImpl extends BaseService<Client, JpaRepository<Client,Long>> implements ClientService {
	
	private ClientRepository clientReporitory;
	
	public ClientServiceImpl(ClientRepository repository) {
		super(repository);
		
		this.clientReporitory = repository;
	}

	public ClientRepository getClientReporitory() {
		return clientReporitory;
	}

	public void setClientReporitory(ClientRepository clientReporitory) {
		this.clientReporitory = clientReporitory;
	}

	@Override
	public Long getTotalClients() {
		return clientReporitory.getTotalClients();
	}
	
	
}
