package com.att.tdp.popcorn_palace.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Min;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Min(0)
    private double price;

    public Long getId() {
        return this.id;
    }

    // no set id function so users aren't able to change the id of a showtime

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie inpMovie) {
        this.movie = inpMovie;
    }

    public Theatre getTheatre() {
        return this.theatre;
    }

    public void setTheatre(Theatre inpTheatre) {
        this.theatre = inpTheatre;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime inpStartTime) {
        this.startTime = inpStartTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalDateTime inpEndTime) {
        this.endTime = inpEndTime;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double inpPrice) {
        this.price = inpPrice;
    }
}