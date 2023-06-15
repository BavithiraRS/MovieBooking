//package com.cts.it.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import com.cts.it.model.*;
//import com.cts.it.repository.MovieRepository;
//import com.cts.it.service.MovieService;
//import com.cts.it.exception.*;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
//public class MovieController {
//    private MovieService movieService;
//    
//    @Autowired
//    MovieRepository mRepo;
//
//    @Autowired
//    public MovieController(MovieService movieService) {
//        this.movieService = movieService;
//    }
//
//    @GetMapping("/movies")
//    public ResponseEntity<?> getAllMovies(@RequestParam Map<String, String> dates) {
//        List<Movie> movies = movieService.getAllMovies();
//        return ResponseEntity.status(HttpStatus.OK).body(movies);
//    }
//    
//   @PostMapping("/registermovie")
//   public ResponseEntity<?> movie(@RequestBody Movie movie) {
//   	
//    	mRepo.save(movie);
//		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
//	}
//    
//   
//
//    @GetMapping("/moviess/{movie_id}")
//    public ResponseEntity<?> getMovieById(@PathVariable int movie_id) {
//    	try {
//        Movie movie = mRepo.findById(movie_id).get();
//		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
//    	}catch(Exception e){
//    		throw new IdNotFoundException("There is no such movie existing with the given id:" +movie_id);
//    	}
//    }
//    
//    @DeleteMapping("/delmovies/{movie_id}")
//    public ResponseEntity<?> deleteMovie(@PathVariable int movie_id) {
//        try {
//            mRepo.deleteById(movie_id);
//            
//            return ResponseEntity.ok().build();
//        } catch (Exception e){
//    		throw new IdNotFoundException("There is no such movie existing with the given id:" +movie_id);
//}
//    }
//
//        
//        		
//    
//    @ExceptionHandler(HousefullException.class)
//	public ResponseEntity existUser(Exception e) {
//		ErrorDetail er=new ErrorDetail();
//		er.setErCode(HttpStatus.NOT_FOUND.toString());
//		er.setErMsg(e.getMessage());
//		return new ResponseEntity(er,HttpStatus.NOT_FOUND);
//	}
//	
//	
//	@ExceptionHandler(IdNotFoundException.class)
//	public ResponseEntity idNotFoundException(Exception e) {
//		ErrorDetail er= new ErrorDetail();
//		er.setErCode(HttpStatus.NOT_FOUND.toString());
//		er.setErMsg(e.getMessage());
//		return new ResponseEntity(er,HttpStatus.NOT_FOUND);
//	}
//
//	
//}
//
package com.cts.it.controller;

import lombok.extern.slf4j.Slf4j;
import com.cts.it.model.*;
import com.cts.it.repository.MovieRepository;
import com.cts.it.service.MovieService;
import com.cts.it.exception.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001","http://localhost:3002"})
@Slf4j
public class MovieController {
    private MovieService movieService;
    private Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    MovieRepository mRepo;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovies(@RequestParam Map<String, String> dates) {
        List<Movie> movies = movieService.getAllMovies();
        logger.info("Retrieved all movies");
        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }

    @PostMapping("/registermovie")
    public ResponseEntity<?> movie(@RequestBody Movie movie) {
        mRepo.save(movie);
        logger.info("Registered a new movie: {}", movie);
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }

    @GetMapping("/moviess/{movie_id}")
    public ResponseEntity<?> getMovieById(@PathVariable int movie_id) {
        try {
            Movie movie = mRepo.findById(movie_id).get();
            logger.info("Retrieved movie with ID: {}", movie_id);
            return new ResponseEntity<Movie>(movie, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while retrieving movie with ID: {}", movie_id);
            throw new IdNotFoundException("There is no such movie existing with the given id:" + movie_id);
        }
    }

    @DeleteMapping("/delmovies/{movie_id}")
    public ResponseEntity<?> deleteMovie(@PathVariable int movie_id) {
        try {
            mRepo.deleteById(movie_id);
            logger.info("Deleted movie with ID: {}", movie_id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error while deleting movie with ID: {}", movie_id);
            throw new IdNotFoundException("There is no such movie existing with the given id:" + movie_id);
        }
    }

    @ExceptionHandler(HousefullException.class)
    public ResponseEntity existUser(Exception e) {
        logger.error("Housefull Exception: {}", e.getMessage());
        ErrorDetail er = new ErrorDetail();
        er.setErCode(HttpStatus.NOT_FOUND.toString());
        er.setErMsg(e.getMessage());
        return new ResponseEntity(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity idNotFoundException(Exception e) {
        logger.error("ID Not Found Exception: {}", e.getMessage());
        ErrorDetail er = new ErrorDetail();
        er.setErCode(HttpStatus.NOT_FOUND.toString());
        er.setErMsg(e.getMessage());
        return new ResponseEntity(er, HttpStatus.NOT_FOUND);
    }
}

