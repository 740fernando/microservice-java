package com.devsuperior.hruser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.hruser.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
