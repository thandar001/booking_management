package com.bk.system.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bk.system.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	
	Optional<User> findUserByEmail(String email);
}
