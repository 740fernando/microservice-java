package com.devsuperior.hruser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hruser.model.User;
import com.devsuperior.hruser.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	
	@Autowired
	UserService service;
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		return ResponseEntity.ok(service.findById(id));
	}
	@GetMapping(value= "/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email){
		return ResponseEntity.ok(service.findByEmail(email));
	}
}
