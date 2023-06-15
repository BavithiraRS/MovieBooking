package com.cts.it.repository;

import com.cts.it.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User getByUserName(String username);

}
