package com.cts.it.service;

import com.cts.it.model.SeatBooked;

import java.util.List;

public interface SeatBookedService {
    List<SeatBooked> getAllSeatBooked();

    SeatBooked getSeatBookedById(int seat_booked_id);

    SeatBooked pushSeatBooked(SeatBooked newSeatBooked);

    SeatBooked updateSeatBooked(SeatBooked updatedSeatBooked, int seat_booked_id);

    void deleteSeatBookedById(int seat_booked_id);
}
