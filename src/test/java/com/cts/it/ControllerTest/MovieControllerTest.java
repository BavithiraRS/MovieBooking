//package com.cts.it.ControllerTest;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.cts.it.controller.MovieController;
//import com.cts.it.exception.IdNotFoundException;
//import com.cts.it.model.Movie;
//import com.cts.it.model.Theatre;
//import com.cts.it.repository.MovieRepository;
//import com.cts.it.service.MovieService;
//
//@ExtendWith(MockitoExtension.class)
//class MovieControllerTest {
//
//    @Mock
//    private MovieService movieService;
//
//    @Mock
//    private MovieRepository movieRepository;
//
//    @InjectMocks
//    private MovieController movieController;
//
//    @Test
//    void testGetAllMovies_Positive() {
//        // Mock the behavior of the movieService
//        List<Movie> expectedMovies = new ArrayList<>();
//        expectedMovies.add(new Movie());
//        expectedMovies.add(new Movie());
//        when(movieService.getAllMovies()).thenReturn(expectedMovies);
//
//        // Call the controller method
//        ResponseEntity<?> responseEntity = movieController.getAllMovies(null);
//
//        // Verify the result
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(expectedMovies, responseEntity.getBody());
//    }
//    
////    @Test
////    void testGetAllMovies_Negative() {
//// 
////        when(movieService.getAllMovies()).thenReturn(null);
////        
////        List <Movie> actualMovies = movieController.getAllMovies();
////         assertNull(actualMovies);
////    }
//}
//
//
//  
//
