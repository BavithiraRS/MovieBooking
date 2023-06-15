package com.cts.it.service.impl;

import com.cts.it.model.Booking;
import com.cts.it.repository.BookingRepository;
import com.cts.it.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(int booking_id) {
        return null;
    }

    @Override
    public Booking pushBooking(Booking newBooking) {
        return null;
    }

    @Override
    public Booking updateBooking(Booking updatedBooking, int booking_id) {
        return null;
    }

    @Override
    public void deleteBookingById(int booking_id) {

    }
//
//	@Override
//	public List<Booking> getByUserId(int user_id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	

	@Override
	public List<Booking> getByUserId(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

