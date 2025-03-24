package com.att.tdp.popcorn_palace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.att.tdp.popcorn_palace.service.BookingService;
import com.att.tdp.popcorn_palace.utils.SeatAlreadyTakenException;
import com.att.tdp.popcorn_palace.utils.ShowtimeNotExistsForBookingException;
import com.att.tdp.popcorn_palace.controller.requestobjects.*;
import com.att.tdp.popcorn_palace.model.Booking;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingServ) {
        this.bookingService = bookingServ;
    } 
    
    @PostMapping
    //check in the input whether it's valid
    public ResponseEntity<?> makeBooking(@Valid @RequestBody BookingRequest bookingRequest, BindingResult res) {
        //check inputs and handle case
        if (res.hasErrors()) {
            return ResponseEntity.badRequest().body(res.getAllErrors());
        }
        //everything is good, can move on to the service
        try {
            Booking booking = this.bookingService.makeBooking(bookingRequest.getShowtimeId(), bookingRequest.getSeatNumber(), bookingRequest.getUserId());
            Map<String, String> response = new HashMap<>();
            response.put("bookingId", booking.getId().toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (SeatAlreadyTakenException seatExcept) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(String.format("Seat number %d is already booked, please try to book another seat",bookingRequest.getSeatNumber()));
        }
        catch (ShowtimeNotExistsForBookingException showtimeNotExistExcept) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Showtime with id number %d does not exist",bookingRequest.getShowtimeId()));
        }


}
}