package com.att.tdp.popcorn_palace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRep) {
        this.movieRepository = movieRep;    
    }

    public List<Movie> getAllMovies() {
        List<Movie> moviesList = this.movieRepository.findAll();
        return moviesList;
    }

    public Movie addMovie(String title, String genre, int duration, double rating, int releaseYear) {
        //get existing movie object
        Optional<Movie> movie = this.movieRepository.findByTitle(title);
        if (movie.isPresent()) {//movie with the same title already exists, then update the info with
        // the input provided here
            return updateMovie(title,genre,duration,rating,releaseYear);
        }
        //create new movie object
        Movie movie = new Movie();
        movie.setTitle(title);
        //TODO: maybe limit genres
        movie.setGenre(genre);
        movie.setDuration(duration);
        movie.setRating(rating);
        movie.setReleaseYear(releaseYear);
        return this.movieRepository.save(movie);
    }

    public Movie updateMovie(String title, String genre, int duration, double rating, int releaseYear) {
        //get existing movie object
        Optional<Movie> movie = this.movieRepository.findByTitle(title);
        if (!movie.isPresent()) {//need to create the movie, add it
            return addMovie(title,genre,duration,rating,releaseYear);
        }// else need to update info
        Movie movieObj = movie.get();
        //TODO: maybe limit genres
        movieObj.setGenre(genre);
        movieObj.setDuration(duration);
        movieObj.setRating(rating);
        movieObj.setReleaseYear(releaseYear);
        return this.movieRepository.save(movieObj);
    }

    public boolean deleteMovie(String title) {
        Optional<Movie> movie = this.movieRepository.findByTitle(title);
        if (!movie.isPresent()) {//movie does not exist already
            return false;
        }// else need to update info
        Movie movieObj = movie.get();
        Long movieId = movieObj.getId();
        this.movieRepository.deleteById(movieId);
        return true;
    }
}
