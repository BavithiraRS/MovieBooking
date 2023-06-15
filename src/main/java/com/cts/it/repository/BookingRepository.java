package com.cts.it.repository;

import com.cts.it.model.Booking;
import com.cts.it.model.Shows;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	//List<Booking> findAllById(int user_id);
//	
	@Query(value="select * from booking where user_user_id=:user_user_id", nativeQuery = true)
	public List<Booking> bookByUser(@Param(value="user_user_id")int user_user_id);
	
	
	
	//List<Booking> findByUser(int user_id);
//	   @Query(value = "SELECT * FROM booking b WHERE b.user_user_id = :user_user_id", nativeQuery = true)
//	    List<Booking> findByUserId(@Param("user_user_id") Integer user_id);
}
 