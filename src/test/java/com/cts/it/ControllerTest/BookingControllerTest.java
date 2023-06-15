package com.cts.it.ControllerTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.it.controller.BookingController;
import com.cts.it.model.Booking;
import com.cts.it.model.Theatre;
import com.cts.it.repository.BookingRepository;
import com.cts.it.service.BookingService;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingController bookingController;

    @Test
    void testGetAllBookings_Positive() {
        
        List<Booking> expectedBookings = new ArrayList<>();
        expectedBookings.add(new Booking(/* booking details */));
        expectedBookings.add(new Booking(/* booking details */));
        when(bookingService.getAllBookings()).thenReturn(expectedBookings);

        
        List<Booking> actualBookings = bookingController.getAllBookings();

       
        assertEquals(expectedBookings, actualBookings);
    }
//    @Test
//    void testGetAllBookings_Negative() {
//     
//        when(bookingService.getAllBookings()).thenReturn(null);
//
//        List<Booking> actualBookings = bookingController.getAllBookings();
//        
//     
//        assertNull(actualBookings);
//    }

}
