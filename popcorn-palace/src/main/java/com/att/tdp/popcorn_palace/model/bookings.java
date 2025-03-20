package com.att.tdp.popcorn_palace;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;

    private UUID userId;

    @Min(1)
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