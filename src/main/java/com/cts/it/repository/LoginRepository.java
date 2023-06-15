package com.cts.it.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.it.model.Booking;
import com.cts.it.model.Login;

public interface LoginRepository extends JpaRepository<Login, String>{

}
