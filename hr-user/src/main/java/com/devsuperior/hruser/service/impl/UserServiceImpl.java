package com.devsuperior.hruser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hruser.exception.UserException;
import com.devsuperior.hruser.model.User;
import com.devsuperior.hruser.repositories.UserRepository;
import com.devsuperior.hruser.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repo;
	
	@Override
	public User findByEmail(String email) {
		return repo.findByEmail(email).orElseThrow(()-> new UserException(email));
	}
	@Override
	public User findById(Long id) {
		return repo.findById(id).orElseThrow(()-> new UserException(id));
	}

}
