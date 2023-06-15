package com.cts.it.ControllerTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.it.controller.ShowsController;
import com.cts.it.exception.HousefullException;
import com.cts.it.exception.IdNotFoundException;
import com.cts.it.model.Shows;
import com.cts.it.model.Theatre;
import com.cts.it.repository.ShowsRepository;
import com.cts.it.service.ShowsService;

@ExtendWith(MockitoExtension.class)
class ShowsControllerTest {

    @Mock
    private ShowsRepository showsRepository;

    @Mock
    private ShowsService showsService;

    @InjectMocks
    private ShowsController showsController;

  

    @Test
    void testGetAllShows_Positive() {
        List<Shows> expectedShows = new ArrayList<>();
        expectedShows.add(new Shows());
        expectedShows.add(new Shows());
        when(showsService.getAllShows()).thenReturn(expectedShows);
        List<Shows> actualShows = showsController.getAllShows();

        assertEquals(expectedShows, actualShows);
    }
    @Test
    void testGetAllShows_Negative() {
     
        when(showsService.getAllShows()).thenReturn(null);

        List<Shows> actualShows = showsController.getAllShows();
       
		assertNull(actualShows);
    }
        @Test
        void testGetById_Positive() {
            // Mock the behavior of the showsRepository
            int id = 1;
            Shows show = new Shows(/* show details */);
            when(showsRepository.findById(id)).thenReturn(Optional.of(show));

            // Call the controller method
            ResponseEntity<Shows> responseEntity = showsController.getById(id);

            // Verify the result
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(show, responseEntity.getBody());
        }

    }


