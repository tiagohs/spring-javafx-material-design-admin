package com.tiagohs.service;

import com.tiagohs.model.Client;

public interface ClientService extends IBaseService<Client> {
	
	Long getTotalClients();
}
