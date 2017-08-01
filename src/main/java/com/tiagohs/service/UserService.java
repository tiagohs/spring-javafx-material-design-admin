package com.tiagohs.service;

import com.tiagohs.model.User;

public interface UserService extends IBaseService<User> {
	
	Long getTotalUsers();
}
