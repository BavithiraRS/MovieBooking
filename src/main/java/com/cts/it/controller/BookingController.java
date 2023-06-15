//package com.cts.it.controller;
//
//import com.cts.it.exception.HousefullException;
//import com.cts.it.exception.IdNotFoundException;
//import com.cts.it.model.Booking;
//import com.cts.it.model.ErrorDetail;
//import com.cts.it.model.Movie;
//import com.cts.it.model.Shows;
//import com.cts.it.model.Theatre;
//import com.cts.it.model.User;
//import com.cts.it.repository.BookingRepository;
//import com.cts.it.repository.ShowsRepository;
//import com.cts.it.repository.UserRepository;
//import com.cts.it.service.BookingService;
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
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
//public class BookingController {
//    private BookingService bookingService;
//    
//    @Autowired
//    BookingRepository bRepo;
//    
//    @Autowired
//    ShowsController sController;
//    
//    @Autowired
//    UserRepository uRepo;
//    
//    @Autowired
//    ShowsRepository showRepo;
//
//    @Autowired
//    public BookingController(BookingService bookingService) {
//        this.bookingService = bookingService;
//    }
//    
//    @PostMapping("/registerBooking")
//	public ResponseEntity<?> booking(@RequestBody Booking booking) {
//    	
//    	bRepo.save(booking);
//		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
//	}
//    
//    @GetMapping("/book/{user_id}/{show_id}/{seats}")
//    public ResponseEntity bookTicket(@PathVariable int user_id, @PathVariable int show_id, @PathVariable int seats) throws HousefullException {
//    	
//		User u=uRepo.findById(user_id).get();
//		Shows s=showRepo.findById(show_id).get();
//		Booking b=new Booking();
//
//		if(s.getSeat()>seats) {
//		b.setSeats(seats);
//		b.setUser(u);
//		b.setShow(s);
//		int newSeat =s.getSeat()-seats;
//		s.setSeat(newSeat);
//		bRepo.save(b);
//		showRepo.save(s);
//		
//		}else {
//			throw new HousefullException("House Full");
//		}
//		
//    	
//		return new ResponseEntity<Booking>(b, HttpStatus.OK);
//    	
//    }
//
//    @GetMapping("/bookings")
//    public List<Booking> getAllBookings() {
//        return bookingService.getAllBookings();
//    }
////
//	@GetMapping("/bookByUser/{user_id}")
//	public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable int user_id) {
//
//List<Booking> b=bRepo.bookByUser(user_id);
//return new ResponseEntity<List<Booking>>(b,HttpStatus.OK);
//}
////    
////    @GetMapping("/bookByUser/{user_id}")
////    public List<Booking> getBookingsByUser(@PathVariable int user_id) {
////        return bookingService.getByUserId(user_id);
////    }
//
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
import com.cts.it.model.Booking;
import com.cts.it.model.ErrorDetail;
import com.cts.it.model.Movie;
import com.cts.it.model.Shows;
import com.cts.it.model.Theatre;
import com.cts.it.model.User;
import com.cts.it.repository.BookingRepository;
import com.cts.it.repository.ShowsRepository;
import com.cts.it.repository.UserRepository;
import com.cts.it.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class BookingController {
    private BookingService bookingService;
    private Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    BookingRepository bRepo;

    @Autowired
    ShowsController sController;

    @Autowired
    UserRepository uRepo;

    @Autowired
    ShowsRepository showRepo;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/registerBooking")
    public ResponseEntity<?> booking(@RequestBody Booking booking) {
        bRepo.save(booking);
        logger.info("Registered a new booking: {}", booking);
        return new ResponseEntity<Booking>(booking, HttpStatus.OK);
    }

    @GetMapping("/book/{user_id}/{show_id}/{seats}")
    public ResponseEntity bookTicket(@PathVariable int user_id, @PathVariable int show_id, @PathVariable int seats) throws HousefullException {
        User u = uRepo.findById(user_id).get();
        Shows s = showRepo.findById(show_id).get();
        Booking b = new Booking();

        if (s.getSeat() > seats) {
            b.setSeats(seats);
            b.setUser(u);
            b.setShow(s);
            int newSeat = s.getSeat() - seats;
            s.setSeat(newSeat);
            bRepo.save(b);
            showRepo.save(s);
            logger.info("Ticket booked successfully for user_id: {}, show_id: {}, seats: {}", user_id, show_id, seats);
        } else {
            logger.error("Housefull Exception: House is full for show_id: {}", show_id);
            throw new HousefullException("House Full");
        }

        return new ResponseEntity<Booking>(b, HttpStatus.OK);
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/bookByUser/{user_id}")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable int user_id) {
        List<Booking> b = bRepo.bookByUser(user_id);
        logger.info("Retrieved bookings for user_id: {}", user_id);
        return new ResponseEntity<List<Booking>>(b, HttpStatus.OK);
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
