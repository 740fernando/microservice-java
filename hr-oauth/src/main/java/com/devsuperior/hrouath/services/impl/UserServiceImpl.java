package com.devsuperior.hrouath.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hrouath.feignclients.UserFeignClient;
import com.devsuperior.hrouath.model.User;
import com.devsuperior.hrouath.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final String EMAIL_FOUND = "Email found";

	private static final String EMAIL_NOT_FOUND = "Email not found : ";

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public User findByEmail(String email) {
		User user = userFeignClient.findByEmail(email).getBody();
		if(user.equals(null)){
			logger.error(EMAIL_NOT_FOUND.concat(email));
			throw new IllegalArgumentException(EMAIL_NOT_FOUND);
		}
		logger.info(EMAIL_FOUND);
		return user;
	}
}
