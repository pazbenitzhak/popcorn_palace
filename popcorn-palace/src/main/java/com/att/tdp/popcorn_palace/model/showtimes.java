package com.att.tdp.popcorn_palace;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

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

    @Min(0.0)
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
        this.startTime = inpDuration;
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