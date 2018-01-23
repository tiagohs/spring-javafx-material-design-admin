package com.tiagohs.service;

import java.util.List;

import com.tiagohs.model.Role;

import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public interface RoleService extends IBaseService<Role> {
	
	Service<List<Role>> findByRole(String role, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);
}
