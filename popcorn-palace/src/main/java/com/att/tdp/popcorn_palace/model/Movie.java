package com.att.tdp.popcorn_palace.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int releaseYear;
    private String genre;

    @Min(0)
    private int duration;


    @Min(0)
    @Max(10)
    private double rating;

    public Long getId() {
        return this.id;
    }

    // no set id function so users aren't able to change the id of a movie

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String inpTitle) {
        this.title = inpTitle;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public void setReleaseYear(int inpReleaseYear) {
        this.releaseYear = inpReleaseYear;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int inpDuration) {
        this.duration = inpDuration;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String inpGenre) {
        this.genre = inpGenre;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double inpRating) {
        this.rating = inpRating;
    }
}