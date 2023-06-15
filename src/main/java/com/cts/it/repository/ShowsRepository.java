package com.cts.it.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.it.model.Shows;

public interface ShowsRepository extends JpaRepository<Shows, Integer>{

	
	@Query(value="select * from shows where date=:date and theatre_id=:theatre_id and movie_id=:movie_id" , nativeQuery=true)
	public List<Shows> showsByAll(@Param(value="date")LocalDate date,@Param(value="theatre_id")int theatre_id,@Param(value="movie_id")int movie_id);

//	@Query(value="select theatre_id from shows where movie_id=:movie_id", nativeQuery=true)
//	public Shows theatreByMovie(@Param(value="movie_id")int movie_id);
	
	@Query(value = "SELECT theatre_id FROM shows WHERE movie_id = :movie_id", nativeQuery = true)
	public List<Integer> findTheatreIdsByMovieId(@Param(value="movie_id") int movieId);


	@Query(value="select * from shows where theatre_id=:theatre_id", nativeQuery=true)
	public Shows movByTheatre(@Param(value="theatre_id")int theatre_id);
	
	@Query(value="select movie_id from shows where date=:date", nativeQuery=true)
	public List<Integer> movByDate(@Param(value="date")LocalDate date);




}
 


