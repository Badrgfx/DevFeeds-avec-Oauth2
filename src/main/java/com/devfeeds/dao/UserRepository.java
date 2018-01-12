package com.devfeeds.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devfeeds.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {
	
	User findOneByUsername(String username);
}
