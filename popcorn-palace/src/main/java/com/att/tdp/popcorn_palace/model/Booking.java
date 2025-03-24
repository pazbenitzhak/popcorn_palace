package com.att.tdp.popcorn_palace.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.UUID;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;
    //assumption: no need to take care of users/user Ids at all, they are received properly by the client server
    private UUID userId;
    private int seatNumber;

    public UUID getId() {
        return this.id;
    }

    // no set id function so users aren't able to change the id of a booking

    public Showtime getShowtime() {
        return this.showtime;
    }

    public void setShowtime(Showtime inpShowtime) {
        this.showtime = inpShowtime;
    }


    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(UUID inpUserId) {
        this.userId = inpUserId;
    }

    public int getSeatNumber() {
        return this.seatNumber;
    }

    public void setSeatNumber(int inpSeatNumber) {
        this.seatNumber = inpSeatNumber;
    }
}