//package com.cts.it.controller;
//
//import com.cts.it.exception.IdNotFoundException;
//import com.cts.it.model.Theatre;
//import com.cts.it.repository.TheatreRepository;
//import com.cts.it.service.TheatreService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
//public class TheatreController {
//    private TheatreService theatreService;
//    
//    @Autowired
//    TheatreRepository tRepo;
//
//    @Autowired
//    public TheatreController(TheatreService theatreService) {
//        this.theatreService = theatreService;
//    }
//
//    @GetMapping("/theatres")
//    public List<Theatre> getAllTheatres() {
//        return theatreService.getAllTheatres();
//    }
//  
//    @PostMapping(value="/registerTheatre", consumes = {"application/json"})
//	public ResponseEntity<?> theatre(@RequestBody Theatre theatre) {
//		theatre=tRepo.save(theatre);
//		return new ResponseEntity<Theatre>(theatre, HttpStatus.OK);
//	}
//    
//    @DeleteMapping("/deltheatre/{theatre_id}")
//    public ResponseEntity<?> deleteTheatreById(@PathVariable int theatre_id) {
//        try {
//            tRepo.deleteById(theatre_id);
//            
//            return ResponseEntity.ok().build();
//        } catch (Exception e){
//    		throw new IdNotFoundException("There is no such Theater existing with the given id:" + theatre_id);
//}
//    }
//    
//    
//}

package com.cts.it.controller;

import com.cts.it.exception.IdNotFoundException;
import com.cts.it.model.Theatre;
import com.cts.it.repository.TheatreRepository;
import com.cts.it.service.TheatreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001","http://localhost:3002"})
public class TheatreController {
    private static final Logger logger = LoggerFactory.getLogger(TheatreController.class);

    private TheatreService theatreService;

    @Autowired
    private TheatreRepository tRepo;

    @Autowired
    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping("/theatres")
    public List<Theatre> getAllTheatres() {
        logger.debug("Getting all theatres");
        return theatreService.getAllTheatres();
    }

    @PostMapping(value = "/registerTheatre", consumes = {"application/json"})
    public ResponseEntity<?> theatre(@RequestBody Theatre theatre) {
        logger.debug("Registering a new theatre");
        theatre = tRepo.save(theatre);
        logger.info("Theatre registered successfully: {}", theatre.getTheatre_id());
        return new ResponseEntity<>(theatre, HttpStatus.OK);
    }

    @DeleteMapping("/deltheatre/{theatre_id}")
    public ResponseEntity<?> deleteTheatreById(@PathVariable int theatre_id) {
        try {
            logger.debug("Deleting theatre with ID: {}", theatre_id);
            tRepo.deleteById(theatre_id);
            logger.info("Theatre deleted successfully: {}", theatre_id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error occurred while deleting theatre with ID: {}", theatre_id, e);
            throw new IdNotFoundException("There is no such Theater existing with the given id: " + theatre_id);
        }
    }
}

