package com.att.tdp.popcorn_palace.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "showtimes")
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