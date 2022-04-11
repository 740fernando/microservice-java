package com.devsuperior.hrouath.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hrouath.model.User;
import com.devsuperior.hrouath.services.UserService;

import feign.FeignException;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email){
		try {
			User user = service.findByEmail(email);
			return ResponseEntity.ok(user);
		}catch ( FeignException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
