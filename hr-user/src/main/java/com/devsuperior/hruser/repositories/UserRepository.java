package com.devsuperior.hruser.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.hruser.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
