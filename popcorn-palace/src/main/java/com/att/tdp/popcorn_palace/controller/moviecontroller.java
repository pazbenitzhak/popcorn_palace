package com.att.tdp.popcorn_palace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.att.tdp.popcorn_palace.service.MovieService;
import com.att.tdp.popcorn_palace.controller.requestobjects.*;
import com.att.tdp.popcorn_palace.model.Movie;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieServ) {
        this.movieService = movieServ;
    } 


    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> allMovies = this.movieService.getAllMovies();
        return ResponseEntity.ok(allMovies);
    }

    
    @PostMapping
    //check in the input whether it's valid
    public ResponseEntity<?> addMovie(@Valid @RequestBody MovieRequest movieRequest, BindingResult res) {
        //check inputs and handle case
        if (res.hasErrors()) {
            return ResponseEntity.badRequest().body(res.getAllErrors());
        }
        //everything is good, can move on to the service
        Movie movieToResp = this.movieService.addMovie(movieRequest.getTitle(),
        movieRequest.getGenre(),movieRequest.getDuration(),movieRequest.getRating(),movieRequest.getReleaseYear());
        return ResponseEntity.status(404).body(movieToResp);
    }


    @PostMapping("/update/{movieTitle}")
    public ResponseEntity<?> updateMovie(@PathVariable @Size(min=1, max=80, message = "Movie title shall be in length of between 3 and 80 characters") String movieTitle, @Valid @RequestBody MovieRequest movieRequest, BindingResult res) {
        //check inputs and handle case
        if (res.hasErrors()) {
            return ResponseEntity.badRequest().body(res.getAllErrors());
        }
        //everything is good, can move on to the service
        Movie movieToResp = this.movieService.updateMovie(movieRequest.getTitle(),
        movieRequest.getGenre(),movieRequest.getDuration(),movieRequest.getRating(),movieRequest.getReleaseYear());
        return ResponseEntity.ok(movieToResp);
    }

    @DeleteMapping("/{movieTitle}")
    public ResponseEntity<String> deleteMovie(@PathVariable @Size(min=1, max=80, message = "Movie title shall be in length of between 3 and 80 characters") String movieTitle) {
        boolean wasMovieActuallyDeleted = this.movieService.deleteMovie(movieTitle);
        if (wasMovieActuallyDeleted) {
            return ResponseEntity.ok(String.format("The movie %s was deleted from the movies table",movieTitle));
        }
        else {
            return ResponseEntity.status(404).body(String.format("The movie %s didn't exist in the movies table",movieTitle));
        }
    }
}