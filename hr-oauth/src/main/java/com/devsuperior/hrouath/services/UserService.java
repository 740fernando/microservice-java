package com.devsuperior.hrouath.services;

import com.devsuperior.hrouath.model.User;

public interface UserService {
	 User findByEmail(String email);
}
