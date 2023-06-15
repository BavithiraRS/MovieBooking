package com.cts.it.service;

import com.cts.it.model.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    
    List<Booking> getByUserId(int user_id);

    Booking getBookingById(int booking_id);

    Booking pushBooking(Booking newBooking);

    Booking updateBooking(Booking updatedBooking, int booking_id);

    void deleteBookingById(int booking_id);
}

