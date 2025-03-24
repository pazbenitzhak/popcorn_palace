package com.att.tdp.popcorn_palace.DTOs;

//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingDTO {

    private Long showtimeId;
    //assumption: no need to take care of users/user Ids at all, they are received properly by the client server
    private UUID userId;
    private int seatNumber;
}