package com.att.tdp.popcorn_palace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;


import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final TheatreRepository theatreRepository; //to query for theatre id by theatre title
    private final MovieRepository movieRepository; //to query for the movie object by movie id


    @Autowired
    public ShowtimeService(ShowtimeRepository showtimeRep, TheatreRepository theatreRep, MovieRepository movieRep) {
        this.showtimeRepository = showtimeRep;
        this.theatreRepository = theatreRep;
        this.movieRepository = movieRep;
    }

    public Optional<Showtime> getShowtimeById(long id) {
        return this.showtimeRepository.findById(id);
    }

    public Showtime addShowtime(Long movieId, double price, String theatreName, LocalDateTime startTime, LocalDateTime endTime) {
        //find if already exists showtime
        //first find theatre ID
        Optional<Theatre> theatreOpt = this.theatreRepository.findByName(theatreName);
        if (!theatreOpt.isPresent()) {
            //no theatre at that name, throw an exception
            // i do not want to automatically create such a theatre, since it might create an 
            //opening for possible attackers to create values in the database
            throw new TheatreNotFoundException("No such theatre exists with the following name: " +theatreName);
        }
        Theatre theatre = theatreOpt.get();
        Long theatreId = theatre.getId();

        // get the movie object
        Optional<Movie> movieOpt = this.movieRepository.findById(movieId);
        if (!movieOpt.isPresent()) {
            //no theatre at that name, throw an exception
            // i do not want to automatically create such a theatre, since it might create an 
            //opening for possible attackers to create values in the database
            throw new MovieNotFoundException("No such movie exists with the following id: " +movieId);
        }
        Movie movie = movieOpt.get();

        //no need to search by price, since the price can be updated using this function
        Optional<Showtime> existShowtime = this.showtimeRepository.findByIdentifiers(movieId, theatreId, startTime, endTime);
        if (existShowtime.isPresent()) {
            //basically update the price
            Showtime showtimeReg = existShowtime.get();
            Long existShowtimeId = showtimeReg.getId();
            return updateShowtime(existShowtimeId,movieId,price,theatreName,startTime,endTime);
        }

        // no such showtime exists
        // use helper function to find overlapping times
        List<Showtime> overlappingShowtimes = this.showtimeRepository.findOverlappingShowtimes(theatreId, startTime, endTime);
        if (!overlappingShowtimes.isEmpty()) {
            throw new ShowtimeOverlapException("There already exists another overlapping showtime in the " +theatreName + " theatre");
        }

        //need to create the new showtime object
        //create new showtime object
        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setTheatre(theatre);
        showtime.setPrice(price);
        showtime.setStartTime(startTime);
        showtime.setEndTime(endTime);
        return this.showtimeRepository.save(showtime);
    }

    public Showtime updateShowtime(Long showtimeId, Long movieId, double price, String theatreName, LocalDateTime startTime, LocalDateTime endTime) {
        //get existing showtime object
        Optional<Showtime> showtimeOpt = this.showtimeRepository.findById(showtimeId);
        if (!showtimeOpt.isPresent()) {//need to create the movie, add it
            return addShowtime(movieId,price,theatreName,startTime,endTime);
        }
        // else need to update info
        Showtime showtime = showtime.get();
        Optional<Theatre> theatreOpt = this.theatreRepository.findByName(theatreName);
        if (!theatreOpt.isPresent()) {
            //no theatre at that name, throw an exception
            // i do not want to automatically create such a theatre, since it might create an 
            //opening for possible attackers to create values in the database
            throw new TheatreNotFoundException("No such theatre exists with the following name: " +theatreName);
        }
        Theatre theatre = theatreOpt.get();
        Long theatreId = theatre.getId();

        //find if there are overlapping showtimes for the fields to be updated: the new times and theatre
        List<Showtime> overlappingShowtimes = this.showtimeRepository.findOverlappingShowtimes(theatreId, startTime, endTime);
        if (!overlappingShowtimes.isEmpty()) {//there are overlapping showtimes with the newly
        //suggested showtime to update, cannot allow that so return the already existing showtime
            return showtime;
        }
        
        //no restrictions, need to update the existing showtime object
        // query the movie
        Optional<Movie> movieOpt = this.movieRepository.findById(movieId);
        if (!movieOpt.isPresent()) {
            //no theatre at that name, throw an exception
            // i do not want to automatically create such a theatre, since it might create an 
            //opening for possible attackers to create values in the database
            throw new MovieNotFoundException("No such movie exists with the following id: " +movieId);
        }
        Movie movie = movieOpt.get();
        showtime.setMovie(movie);
        showtime.setTheatre(theatre);
        showtime.setPrice(price);
        showtime.setStartTime(startTime);
        showtime.setEndTime(endTime);
        return this.showtimeRepository.save(showtime);
    }

    public void deleteShowtime(Long showtimeId) {
        Optional<Showtime> showtimeOpt = this.showtimeRepository.findById(showtimeId);
        if (!showtimeOpt.isPresent()) {//showtime does not exist already
            return;
        }// else need to delete
        this.showtimeRepository.deleteById(showtimeId);
        return;
    }
}
