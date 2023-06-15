//package com.cts.it.controller;
//
//import com.cts.it.exception.HousefullException;
//import com.cts.it.exception.IdNotFoundException;
//import com.cts.it.model.ErrorDetail;
//import com.cts.it.model.Movie;
//import com.cts.it.model.SeatBooked;
//import com.cts.it.repository.SeatBookedRepository;
//import com.cts.it.service.SeatBookedService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
//public class SeatBookedController {
//    private SeatBookedService seatBookedService;
//    
//    @Autowired
//    SeatBookedRepository sbRepo;
//
//    @Autowired
//    public SeatBookedController(SeatBookedService seatBookedService) {
//        this.seatBookedService = seatBookedService;
//    }
//
//    @GetMapping("/seatbooked")
//    public List<SeatBooked> getAllSeatBooked() {
//        return seatBookedService.getAllSeatBooked();
//    }
//    
//    @PostMapping("/registerSeatBook")
//	public ResponseEntity<?> seatBooked(@RequestBody SeatBooked seatBook) {
//    	
//    	sbRepo.save(seatBook);
//		return new ResponseEntity<SeatBooked>(seatBook, HttpStatus.OK);
//	}
//    
//    @ExceptionHandler(HousefullException.class)
//   	public ResponseEntity existUser(Exception e) {
//   		ErrorDetail er=new ErrorDetail();
//   		er.setErCode(HttpStatus.NOT_FOUND.toString());
//   		er.setErMsg(e.getMessage());
//   		return new ResponseEntity(er,HttpStatus.NOT_FOUND);
//   	}
//   	
//   	
//   	@ExceptionHandler(IdNotFoundException.class)
//   	public ResponseEntity idNotFoundException(Exception e) {
//   		ErrorDetail er= new ErrorDetail();
//   		er.setErCode(HttpStatus.NOT_FOUND.toString());
//   		er.setErMsg(e.getMessage());
//   		return new ResponseEntity(er,HttpStatus.NOT_FOUND);
//   	}
//   	
//}

package com.cts.it.controller;

import com.cts.it.exception.HousefullException;
import com.cts.it.exception.IdNotFoundException;
import com.cts.it.model.ErrorDetail;
import com.cts.it.model.Movie;
import com.cts.it.model.SeatBooked;
import com.cts.it.repository.SeatBookedRepository;
import com.cts.it.service.SeatBookedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class SeatBookedController {
    private static final Logger logger = LoggerFactory.getLogger(SeatBookedController.class);
    
    @Autowired
    private SeatBookedService seatBookedService;
    
    @Autowired
    private SeatBookedRepository sbRepo;

    @GetMapping("/seatbooked")
    public List<SeatBooked> getAllSeatBooked() {
        logger.debug("Getting all seat bookings");
        return seatBookedService.getAllSeatBooked();
    }
    
    @PostMapping("/registerSeatBook")
    public ResponseEntity<?> seatBooked(@RequestBody SeatBooked seatBook) {
        logger.debug("Registering a new seat booking");
        sbRepo.save(seatBook);
        logger.info("Seat booking registered successfully: {}", seatBook.getId());
        return new ResponseEntity<>(seatBook, HttpStatus.OK);
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

