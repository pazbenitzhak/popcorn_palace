package com.att.tdp.popcorn_palace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieServ) {
        this.movieService = movieServ;
    } 


    @GetMapping("/movies/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> allMovies = this.movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    
    @PostMapping
    //check in the input whether it's valid
    public ResponseEntity<?> addMovie(@Valid @RequestBody MovieRequest movieRequest, BindingResult res) {
        //check inputs and handle case
        if (res.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        //everything is good, can move on to the service
        Movie movieToResp = this.movieService.addMovie(movieRequest.getTitle(),
        movieRequest.getGenre(),movieRequest.getDuration(),movieRequest.getRating(),movieRequest.getReleaseYear());
        return ResponseEntity.status(HttpStatus).body(movieToResp);
    }


    @PostMapping("/update/{movieTitle}")
    public ResponseEntity<?> updateMovie(@PathVariable @Size(min=1, max=80, message = "Movie title shall be in length of
    between 3 and 80 characters") String movieTitle, @Valid @RequestBody MovieRequest movieRequest, BindingResult res) {
        //check inputs and handle case
        if (res.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        //everything is good, can move on to the service
        Movie movieToResp = this.movieService.updateMovie(movieRequest.getTitle(),
        movieRequest.getGenre(),movieRequest.getDuration(),movieRequest.getRating(),movieRequest.getReleaseYear());
        return ResponseEntity.status(HttpStatus);
    }

    @DeleteMapping("/{movieTitle}")
    public ResponseEntity<String> deleteMovie(@PathVariable @Size(min=1, max=80, message = "Movie title shall be in length of
    between 3 and 80 characters") String movieTitle) {
        //TODO: make sure variable is alright
        boolean wasMovieActuallyDeleted = this.movieService.deleteMovie(movieTitle);
        if (wasMovieActuallyDeleted) {
            return ResponseEntity.ok("The movie %s was deleted from the movies table",movieTitle);
        }
        else {
            ResponseEntity.status(404).body("The movie %s didn't exist in the movies table",movieTitle);
        }
    }
}