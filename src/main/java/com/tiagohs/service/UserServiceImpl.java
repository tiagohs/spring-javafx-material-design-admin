package com.tiagohs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.User;
import com.tiagohs.repository.UserRepository;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

@Service("userService")
public class UserServiceImpl extends BaseCrudService<User, JpaRepository<User,Long>> implements UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super(userRepository);
		
		this.userRepository = userRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public javafx.concurrent.Service<Long> getTotalUsers(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<Long>() {
			protected Long call() throws Exception {
				return userRepository.getTotalUsers();
			};
		}, onSucess, beforeStart);
	}

	@Override
	public javafx.concurrent.Service<User> findUserByEmail(String email, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<User>() {
			protected User call() throws Exception {
				return userRepository.findByEmail(email);
			};
		}, onSucess, beforeStart);
		
		
	}

	@Override
	public javafx.concurrent.Service<User> findByEmailAndPassword(String email, String password, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<User>() {
			protected User call() throws Exception {
				return userRepository.findByEmailAndPassword(email, password);
			};
		}, onSucess, beforeStart);
	}

	@Override
	public javafx.concurrent.Service<User> finUserSignIn(EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<User>() {
			protected User call() throws Exception {
				return userRepository.finUserSignIn();
			};
		}, onSucess, beforeStart);
	}
	
	@Override
	public javafx.concurrent.Service<Void> setUserAsSignin(String email, String password,
			EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<Void>() {
			protected Void call() throws Exception {
				userRepository.setUserAsSignIn(email, password);
				
				return null;
			};
		}, onSucess, beforeStart);
	}

	@Override
	public javafx.concurrent.Service<Void> setUserAsSignOut(EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<Void>() {
			protected Void call() throws Exception {
				userRepository.setUserAsSignOut();
				
				return null;
			};
		}, onSucess, beforeStart);
	}

}
