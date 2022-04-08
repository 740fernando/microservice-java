package com.devsuperior.hruser.service;

import com.devsuperior.hruser.model.User;

public interface UserService {

	User findByEmail(String email);

	User findById(Long id);
}
