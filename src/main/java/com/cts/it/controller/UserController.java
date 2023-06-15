//package com.cts.it.controller;
//
//import com.cts.it.LoginFeignClient;
//import com.cts.it.exception.*;
//import com.cts.it.exception.IdNotFoundException;
//import com.cts.it.model.ErrorDetail;
//import com.cts.it.model.Login;
//import com.cts.it.model.Movie;
//import com.cts.it.model.Theatre;
//import com.cts.it.model.User;
//import com.cts.it.repository.LoginRepository;
//import com.cts.it.repository.UserRepository;
//import com.cts.it.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
//public class UserController {
//    private UserService userService;
//    
//    @Autowired
//    UserRepository uRepo;
//    
//    @Autowired
//    UserService uS;
//    
//    @Autowired
//    LoginFeignClient loginFC;
//    
//    @Autowired
//    LoginRepository lRepo;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//    
//    @GetMapping("/lo")
//    public List<Login> getAllLogin() {
//        return lRepo.findAll();
//    }
//    
////    @PostMapping("/login")
////    public ResponseEntity<Login> login(@RequestBody Login login){
////    	loginFC.registerUser(login);
////    	return new ResponseEntity<Login>(login, HttpStatus.OK);
////
////    }
//    
//    @PostMapping("login")
//	public ResponseEntity<User> registerUser(@RequestBody User user) {
//		try {
//			if(user.getUserName().equals("admin") && user.getPassword().equals("admin")) {
//				User admin = new User();
//			
//				admin.setUserName("admin");
//				admin.setPassword("admin");
//				return new ResponseEntity<User>(admin, HttpStatus.OK);
//			}
//			User l=uRepo.getByUserName(user.getUserName());
//			System.out.println("password>>>>>>>>>>db"+l.getPassword());
//			System.out.println("password>>>>>>>>>>session"+user.getPassword());
//			System.out.println("password>>>>>>>>>>"+l.getPassword().equals(user.getPassword()));
//
//			if (l.getPassword().equals(user.getPassword())) {
//				
//				
//				return new ResponseEntity<User>(l, HttpStatus.OK);
//			}
//			return new ResponseEntity<User>(l, HttpStatus.NOT_FOUND);
//			
//		}catch(Exception e) {
//System.out.println(e);		}
//		return null;
//		
//	}
//    
//    @PostMapping("/registerUser")
//	public ResponseEntity<?> user(@RequestBody User user) {
//    	User u=uRepo.getByUserName(user.getUserName());
//    
//    	Login l=new Login();
//    	if(u!=null) {
//    		return new ResponseEntity<User>(user, HttpStatus.NOT_ACCEPTABLE);
//
//	}else {
//		l.setUserName(user.getUserName());
//    	l.setPassword(user.getPassword());
//    	l.setRoles("user");
//    	l.setUser_id(user.getUser_id());
//    	lRepo.save(l);
//    	uRepo.save(user);
//		return new ResponseEntity<User>(user, HttpStatus.OK);
//	}
//    }
//    
//    
//    @GetMapping("/users")
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
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
//
//   	
//}
package com.cts.it.controller;

import com.cts.it.LoginFeignClient;
import com.cts.it.exception.*;
import com.cts.it.model.ErrorDetail;
import com.cts.it.model.Login;
import com.cts.it.model.Movie;
import com.cts.it.model.Theatre;
import com.cts.it.model.User;
import com.cts.it.repository.LoginRepository;
import com.cts.it.repository.UserRepository;
import com.cts.it.service.UserService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    private UserRepository uRepo;


    @Autowired
    private LoginFeignClient loginFC;

    @Autowired
    private LoginRepository lRepo;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/lo")
    public List<Login> getAllLogin() {
        logger.debug("Getting all login details");
        return lRepo.findAll();
    }

    @PostMapping("login")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        try {
            logger.debug("Attempting to login user: {}", user.getUserName());
            User l = uRepo.getByUserName(user.getUserName());
            logger.debug("Retrieved user details from the repository");

            logger.debug("DB password: {}", l.getPassword());
            logger.debug("Session password: {}", user.getPassword());

            if (l.getPassword().equals(user.getPassword())) {
                logger.info("User logged in successfully: {}", user.getUserName());
                return new ResponseEntity<>(l, HttpStatus.OK);
            }

            logger.info("User login failed: {}", user.getUserName());
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error occurred during login: {}", e.getMessage(), e);
        }
        return null;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> user(@RequestBody User user) {
        User u = uRepo.getByUserName(user.getUserName());

        Login l = new Login();
        if (u != null) {
            logger.info("User registration failed. User already exists: {}", user.getUserName());
            return new ResponseEntity<>(user, HttpStatus.NOT_ACCEPTABLE);
        } else {
            l.setUserName(user.getUserName());
            l.setPassword(user.getPassword());
            l.setRoles("user");
            l.setUser_id(user.getUser_id());
            lRepo.save(l);
            uRepo.save(user);
            logger.info("User registered successfully: {}", user.getUserName());
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        logger.debug("Getting all users");
        return userService.getAllUsers();
    }

    @ExceptionHandler(HousefullException.class)
    public ResponseEntity<ErrorDetail> existUser(Exception e) {
        ErrorDetail er = new ErrorDetail();
        er.setErCode(HttpStatus.NOT_FOUND.toString());
        er.setErMsg(e.getMessage());
        logger.error("HousefullException occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorDetail> idNotFoundException(Exception e) {
        ErrorDetail er = new ErrorDetail();
        er.setErCode(HttpStatus.NOT_FOUND.toString());
        er.setErMsg(e.getMessage());
        logger.error("IdNotFoundException occurred: {}", e.getMessage(), e);
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }
}
