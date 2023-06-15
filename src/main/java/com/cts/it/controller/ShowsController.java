//package com.cts.it.controller;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cts.it.exception.*;
//import com.cts.it.model.Booking;
//import com.cts.it.model.ErrorDetail;
//import com.cts.it.model.Movie;
//import com.cts.it.model.Shows;
//import com.cts.it.model.Theatre;
//import com.cts.it.repository.MovieRepository;
//import com.cts.it.repository.ShowsRepository;
//import com.cts.it.repository.TheatreRepository;
//import com.cts.it.service.ShowsService;
//
//@RestController
//@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001" })
//public class ShowsController {
//
//	@Autowired
//	ShowsRepository sRepo;
//
//	@Autowired
//	TheatreRepository tRepo;
//
//	@Autowired
//	MovieRepository mRepo;
//
//	@Autowired
//	ShowsService sService;
//
//	@GetMapping(value = "/registerShow/{date}/{name}/{seat}/{movie_id}/{theatre_id}/{price}")
//	public ResponseEntity show(@PathVariable LocalDate date, @PathVariable String name, @PathVariable int seat,
//			@PathVariable Movie movie_id, @PathVariable Theatre theatre_id, @PathVariable int price) {
//
//		Shows s = new Shows();
//		s.setDate(date);
//		s.setName(name);
//		s.setSeat(seat);
//		s.setMovie(movie_id);
//		s.setTheatre(theatre_id);
//		s.setPrice(price);
//		sRepo.save(s);
//		return new ResponseEntity<Shows>(s, HttpStatus.OK);
//	}
//
//	@GetMapping("/shows")
//	public List<Shows> getAllShows() {
//
//		return sService.getAllShows();
//	}
//
//	@GetMapping("/specficShow/{date}/{theatre_id}/{movie_id}")
//	public ResponseEntity<List<Shows>> getByDate(@PathVariable LocalDate date, @PathVariable int theatre_id,
//			@PathVariable int movie_id) {
//		List<Shows> s = sRepo.showsByAll(date, theatre_id, movie_id);
//
//		return new ResponseEntity<List<Shows>>(s, HttpStatus.OK);
//	}
//
////	@GetMapping("/theatreByMovie/{movie_id}")
////    public ResponseEntity<Shows> getTheatreByMovie(@PathVariable int movie_id ) {
////		Shows s=new Shows();
////		 s=sRepo.movByTheatre(movie_id);
////		System.out.println(">>>>>>>>>>>>>>"+movie_id);
////		return new ResponseEntity<Shows>(s, HttpStatus.OK);	
////	}
////	
//
//	@GetMapping("/theatresByMovie/{movie_id}")
//	public ResponseEntity<List<Theatre>> getTheatreIdsByMovie(@PathVariable int movie_id) {
//		List<Integer> theatreIds = sRepo.findTheatreIdsByMovieId(movie_id);
//		List<Theatre> t = tRepo.findAllById(theatreIds);
//		return new ResponseEntity<List<Theatre>>(t, HttpStatus.OK);
//	}
//
//	@GetMapping("/moviesByDate/{date}")
//	public ResponseEntity<List<Movie>> getMoviesByDate(@PathVariable LocalDate date) {
//		List<Integer> movieIds = sRepo.movByDate(date);
//		List<Movie> m = mRepo.findAllById(movieIds);
//		return new ResponseEntity<List<Movie>>(m, HttpStatus.OK);
//	}
//
//	@GetMapping("/show/{id}")
//	public ResponseEntity<Shows> getById(@PathVariable int id) {
//		try {
//			Shows show = new Shows();
//			show = sRepo.findById(id).get();
//			return new ResponseEntity<Shows>(show, HttpStatus.OK);
//		} catch (Exception e) {
//			throw new IdNotFoundException("There is no such movie existing with the given id");
//		}
//	}
//
//	@ExceptionHandler(HousefullException.class)
//	public ResponseEntity existUser(Exception e) {
//		ErrorDetail er = new ErrorDetail();
//		er.setErCode(HttpStatus.NOT_FOUND.toString());
//		er.setErMsg(e.getMessage());
//		return new ResponseEntity(er, HttpStatus.NOT_FOUND);
//	}
//
//	@ExceptionHandler(IdNotFoundException.class)
//	public ResponseEntity idNotFoundException(Exception e) {
//		ErrorDetail er = new ErrorDetail();
//		er.setErCode(HttpStatus.NOT_FOUND.toString());
//		er.setErMsg(e.getMessage());
//		return new ResponseEntity(er, HttpStatus.NOT_FOUND);
//	}
//
//}
package com.cts.it.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.it.exception.*;
import com.cts.it.model.Booking;
import com.cts.it.model.ErrorDetail;
import com.cts.it.model.Movie;
import com.cts.it.model.Shows;
import com.cts.it.model.Theatre;
import com.cts.it.repository.MovieRepository;
import com.cts.it.repository.ShowsRepository;
import com.cts.it.repository.TheatreRepository;
import com.cts.it.service.ShowsService;

@RestController
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001" })
public class ShowsController {
    private static final Logger logger = LoggerFactory.getLogger(ShowsController.class);

    @Autowired
    private ShowsRepository sRepo;

    @Autowired
    private TheatreRepository tRepo;

    @Autowired
    private MovieRepository mRepo;

    @Autowired
    private ShowsService sService;

    @GetMapping(value = "/registerShow/{date}/{name}/{seat}/{movie_id}/{theatre_id}/{price}")
    public ResponseEntity<?> show(@PathVariable LocalDate date, @PathVariable String name, @PathVariable int seat,
            @PathVariable Movie movie_id, @PathVariable Theatre theatre_id, @PathVariable int price) {
        logger.debug("Registering a new show");
        Shows s = new Shows();
        s.setDate(date);
        s.setName(name);
        s.setSeat(seat);
        s.setMovie(movie_id);
        s.setTheatre(theatre_id);
        s.setPrice(price);
        sRepo.save(s);
        logger.info("Show registered successfully: {}", s.getShow_id());
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/shows")
    public List<Shows> getAllShows() {
        logger.debug("Getting all shows");
        return sService.getAllShows();
    }

    @GetMapping("/specficShow/{date}/{theatre_id}/{movie_id}")
    public ResponseEntity<List<Shows>> getByDate(@PathVariable LocalDate date, @PathVariable int theatre_id,
            @PathVariable int movie_id) {
        logger.debug("Getting specific shows by date, theatre, and movie");
        List<Shows> s = sRepo.showsByAll(date, theatre_id, movie_id);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/theatresByMovie/{movie_id}")
    public ResponseEntity<List<Theatre>> getTheatreIdsByMovie(@PathVariable int movie_id) {
        logger.debug("Getting theatre IDs by movie");
        List<Integer> theatreIds = sRepo.findTheatreIdsByMovieId(movie_id);
        List<Theatre> t = tRepo.findAllById(theatreIds);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    @GetMapping("/moviesByDate/{date}")
    public ResponseEntity<List<Movie>> getMoviesByDate(@PathVariable LocalDate date) {
        logger.debug("Getting movies by date");
        List<Integer> movieIds = sRepo.movByDate(date);
        List<Movie> m = mRepo.findAllById(movieIds);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<Shows> getById(@PathVariable int id) {
        try {
            logger.debug("Getting show by ID: {}", id);
            Shows show = sRepo.findById(id).orElse(null);
            if (show == null) {
                throw new IdNotFoundException("There is no show existing with the given id");
            }
            return new ResponseEntity<>(show, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while getting show by ID: {}", id, e);
            throw new IdNotFoundException("There is no show existing with the given id");
        }
    }

    @ExceptionHandler(HousefullException.class)
    public ResponseEntity<?> existUser(Exception e) {
        logger.error("HousefullException occurred: {}", e.getMessage(), e);
        ErrorDetail er = new ErrorDetail();
        er.setErCode(HttpStatus.NOT_FOUND.toString());
        er.setErMsg(e.getMessage());
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<?> idNotFoundException(Exception e) {
        logger.error("IdNotFoundException occurred: {}", e.getMessage(), e);
        ErrorDetail er = new ErrorDetail();
        er.setErCode(HttpStatus.NOT_FOUND.toString());
        er.setErMsg(e.getMessage());
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }
}

