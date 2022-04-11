package com.devsuperior.hrouath.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devsuperior.hrouath.feignclients.UserFeignClient;
import com.devsuperior.hrouath.model.User;
import com.devsuperior.hrouath.services.UserService;

/**
 * UserDetailsService - Interface utilizada pelo Spring Security. A assinatura
 * dessa interface permite buscar um username no banco e valida se autenticacao
 * esta correta .
 * 
 * @author fsouviei
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final String EMAIL_FOUND = "Email found ";

	private static final String EMAIL_NOT_FOUND = "Email not found : ";

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserFeignClient userFeignClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userFeignClient.findByEmail(username).getBody();
		if (user.equals(null)) {
			logger.error(EMAIL_NOT_FOUND.concat(username));
			throw new UsernameNotFoundException(EMAIL_NOT_FOUND);
		}
		logger.info(EMAIL_FOUND.concat(username));
		return user;
	}
}
