package com.att.tdp.popcorn_palace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.att.tdp.popcorn_palace.service.MovieService;
import com.att.tdp.popcorn_palace.DTOs.*;
import com.att.tdp.popcorn_palace.model.Movie;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private static final Set<String> genres = Set.of("ACTION","COMEDY","ADVENTURE","THRILLER","HORROR","FANTASY","ROMANCE","DRAMA","WESTERN","MYSTERY","SCIENCE FICTION");

    @Autowired
    public MovieController(MovieService movieServ) {
        this.movieService = movieServ;
    } 


    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> allMovies = this.movieService.getAllMovies();
        return ResponseEntity.status(HttpStatus.OK).body(allMovies);
    }

    
    @PostMapping
    //check in the input whether it's valid
//    @Valid
    public ResponseEntity<?> addMovie(@RequestBody MovieDTO movieRequest) {
        //check inputs and handle case
        String validation = validateMovieDTOInputs(movieRequest);
        if (!validation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validation);
        }
        //everything is good, can move on to the service
        Movie movieToResp = this.movieService.addMovie(movieRequest.getTitle(),
        movieRequest.getGenre(),movieRequest.getDuration(),movieRequest.getRating(),movieRequest.getReleaseYear());
        return ResponseEntity.status(HttpStatus.OK).body(movieToResp);
    }


    @PostMapping("/update/{movieTitle}")
//    @Valid
    public ResponseEntity<?> updateMovie(@PathVariable String movieTitle,@RequestBody MovieDTO movieRequest) {
        //check inputs and handle case
        if (movieTitle.length()<3||movieTitle.length()>80) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie title shall be in length of between 3 and 80 characters");
        }
        String validation = validateMovieDTOInputs(movieRequest);
        if (!validation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validation);
        }
        //everything is good, can move on to the service
        Movie movieToResp = this.movieService.updateMovie(movieRequest.getTitle(),
        movieRequest.getGenre(),movieRequest.getDuration(),movieRequest.getRating(),movieRequest.getReleaseYear());
        return ResponseEntity.status(HttpStatus.OK).body(movieToResp);
    }

    @DeleteMapping("/{movieTitle}")
    public ResponseEntity<String> deleteMovie(@PathVariable String movieTitle) {
        if (movieTitle.length()<3||movieTitle.length()>80) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Movie title shall be in length of between 3 and 80 characters");
        }
        boolean wasMovieActuallyDeleted = this.movieService.deleteMovie(movieTitle);
        if (wasMovieActuallyDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(String.format("The movie %s was deleted from the movies table",movieTitle));
        }
        else {
            return ResponseEntity.status(404).body(String.format("The movie %s didn't exist in the movies table", movieTitle));
        }
    }

    public String validateMovieDTOInputs(MovieDTO movieRequest) {
        String res = "";
        if (movieRequest.getTitle().length()<3||movieRequest.getTitle().length()>80) {
            res += "Title shall be in length of between 3 and 80 characters, ";
        }
        if (!genres.contains(movieRequest.getGenre().toUpperCase())) {
            res += "Genre shall be chosen from one of the following: " + genres.toString() + ", ";
        }
        if (movieRequest.getDuration()<=0) {
            res+="Duration shall be larger than 0 minutes, ";
        }
        if (!(movieRequest.getRating()>=0&&movieRequest.getRating()<=10)) {
            res += "Rating shall be in the range of 0 and 10, ";
        }
        if (movieRequest.getReleaseYear()<1888) {
            res += "ReleaseYear shall be larger then 1888, ";
        }
        if (res.endsWith(", ")) {
            res = res.substring(0, res.length()-2);
        }
        return res;
    }
}