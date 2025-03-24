package com.att.tdp.popcorn_palace.controller.requestobjects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingRequest {

    @NotNull(message = "showtime Id can't be null")
    @Min(value = 1,message = "showtime ID duration can't be less than 1")
    private Long showtimeId;
    //assumption: no need to take care of users/user Ids at all, they are received properly by the client server
    @NotNull(message = "user Id can't be null")
    private UUID userId;
    
    @NotNull(message = "seat number can't be null")
    @Min(value = 1, message = "seat number value should be at least 1")
    private int seatNumber;
}