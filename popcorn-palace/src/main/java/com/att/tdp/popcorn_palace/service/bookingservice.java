package com.att.tdp.popcorn_palace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.Optional;

import com.att.tdp.popcorn_palace.model.Booking;
import com.att.tdp.popcorn_palace.model.Showtime;
import com.att.tdp.popcorn_palace.repository.BookingRepository;
import com.att.tdp.popcorn_palace.repository.ShowtimeRepository;
import com.att.tdp.popcorn_palace.utils.SeatAlreadyTakenException;
import com.att.tdp.popcorn_palace.utils.ShowtimeNotExistsForBookingException;



@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ShowtimeRepository showtimeRepository;

    @Autowired
    public BookingService(BookingRepository bookingRep, ShowtimeRepository showtimeRep) {
        this.bookingRepository = bookingRep;    
        this.showtimeRepository = showtimeRep;
    }


    public Booking makeBooking(Long showtimeId, int seatNumber, UUID userId) {
        //check if booking for this seat and showtime exists
        Optional<Booking> bookingOpt = this.bookingRepository.findExistingSeatBookings(showtimeId, seatNumber);
        //if so, return exception that the seat is already taken
        if (bookingOpt.isPresent()) {
            throw new SeatAlreadyTakenException(String.format("the seat number %d in showtime ID %d is already taken",seatNumber,showtimeId));
        }
        // else, need to create the booking. first verify that the showtime indeed exists
        //query the showtime to it exists
        Optional<Showtime> showtimeOpt = this.showtimeRepository.findById(showtimeId);
        //if showtime does not exist throw exception
        if (!showtimeOpt.isPresent()) {
            throw new ShowtimeNotExistsForBookingException(String.format("showtime with ID %d does not exist",showtimeId));
        }
        //else, create a new booking object, get a booking id and return the object
        Showtime showtimeObj = showtimeOpt.get();
        Booking booking = new Booking();
        booking.setShowtime(showtimeObj);
        booking.setSeatNumber(seatNumber);
        booking.setUserId(userId);
        return this.bookingRepository.save(booking);
        // //query the theatre to find number of seats, assume that theatre id was properly inserted
        // //to db for showtime registry (not check existence of theatre instance)
        // Theatre theatre = showtimeForBooking.getTheatre();
        // int theatreMaxSeatNum = theatre.getSize();
        // //if theatre size is smaller then desired seatNumebr then return seat number too big exception
        // if (seatNumber>theatreMaxSeatNum) {
        //     throw new SeatNumber
        // }

    }
}
