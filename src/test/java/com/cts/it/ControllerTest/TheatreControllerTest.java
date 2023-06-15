package com.cts.it.ControllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.cts.it.model.*;

import com.cts.it.controller.TheatreController;
import com.cts.it.repository.TheatreRepository;
import com.cts.it.service.TheatreService;

@ExtendWith(MockitoExtension.class)
class TheatreControllerTest {

    @Mock
    private TheatreService theatreService;
    
    @InjectMocks
    private TheatreController theatreController;

    @Test
    void testGetAllTheatres_Positive() {
        
        List<Theatre> expectedTheatres = new ArrayList<>();
        expectedTheatres.add(new Theatre());
        expectedTheatres.add(new Theatre());
        when(theatreService.getAllTheatres()).thenReturn(expectedTheatres);
        
       
        List<Theatre> actualTheatres = theatreController.getAllTheatres();
        
    
        assertEquals(expectedTheatres, actualTheatres);
    }
     
    @Test
    void testGetAllTheatres_Negative() {
     
        when(theatreService.getAllTheatres()).thenReturn(null);

        List<Theatre> actualTheatres = theatreController.getAllTheatres();
        
     
        assertNull(actualTheatres);
    }
}
