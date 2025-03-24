package com.att.tdp.popcorn_palace.controller;
import com.att.tdp.popcorn_palace.model.Movie;
import com.att.tdp.popcorn_palace.model.Showtime;
import com.att.tdp.popcorn_palace.model.Theatre;
import com.att.tdp.popcorn_palace.service.ShowtimeService;
import com.att.tdp.popcorn_palace.utils.MovieNotFoundException;
import com.att.tdp.popcorn_palace.utils.ShowtimeOverlapException;
import com.att.tdp.popcorn_palace.utils.TheatreNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.att.tdp.popcorn_palace.DTOs.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @Autowired
    public ShowtimeController(ShowtimeService showtimeServ) {
        this.showtimeService = showtimeServ;
    }

    @GetMapping("/{showtimeId}")
    public ResponseEntity<?> getShowtime(@PathVariable Long showtimeId) {
        if (showtimeId<=0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid showtimeId, was given a non-positive integer");
        }
        Optional<Showtime> showtimeOpt = this.showtimeService.getShowtimeById(showtimeId);
        if (showtimeOpt.isEmpty()) {
            return ResponseEntity.status(404).body(String.format("Showtime with id %d doesn't exist in the showtime table",showtimeId));
        }
        else {
            Showtime showtime = showtimeOpt.get();
            Map<String, String> response = getStringStringMap(showtime);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    private static Map<String, String> getStringStringMap(Showtime showtime) {
        Map<String, String> response = new HashMap<>();
        response.put("id", showtime.getId().toString());
        Movie movie = showtime.getMovie();
        response.put("movieId", movie.getId().toString());
        Theatre theatre = showtime.getTheatre();
        response.put("theater", theatre.getTheatreName());
        response.put("startTime", showtime.getStartTime().toString());
        response.put("endTime", showtime.getEndTime().toString());
        return response;
    }

    @PostMapping
    public ResponseEntity<?> addShowtime(@RequestBody ShowtimeDTO showtimeRequest) {
        //check inputs and handle case
        String validation = validateShowtimeDTOInputs(showtimeRequest);
        if (!validation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validation);
        }
        //everything is good, can move on to the service
        try {
            Showtime showtimeToResp = this.showtimeService.addShowtime(showtimeRequest.getMovieId(),showtimeRequest.getPrice(),showtimeRequest.getTheater(),showtimeRequest.getStartTime(),showtimeRequest.getEndTime());
            Map<String, String> response = getStringStringMap(showtimeToResp);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (TheatreNotFoundException theatreExcep) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("The %s theatre is not part of our cinema, please choose another theatre",showtimeRequest.getTheater()));
        }
        catch (MovieNotFoundException movieExcep) {
            return ResponseEntity.status(404).body(String.format("The movie with ID %d doesn't currently show in our cinema",showtimeRequest.getMovieId()));
        }
        catch (ShowtimeOverlapException showExcep) {
            return ResponseEntity.status(409).body(String.format("This showtime overlaps with another one presenting at the %s theatre. Please choose a different time or a try a different theatre",showtimeRequest.getTheater()));
        }
    }


    @PostMapping("/update/{showtimeId}")
    public ResponseEntity<?> updateShowtime(@PathVariable Long showtimeId,@RequestBody ShowtimeDTO showtimeRequest) {
        //check inputs and handle case
        if (showtimeId<=0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid showtimeId, was given a non-positive integer");
        }
        String validation = validateShowtimeDTOInputs(showtimeRequest);
        if (!validation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validation);
        }
        //everything is good, can move on to the service
        try {
            Showtime showtimeToResp = this.showtimeService.updateShowtime(showtimeId,showtimeRequest.getMovieId(),showtimeRequest.getPrice(),showtimeRequest.getTheater(),showtimeRequest.getStartTime(),showtimeRequest.getEndTime());
            return ResponseEntity.ok(HttpStatus.OK);
        }
        catch (TheatreNotFoundException theatreExcep) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("The %s theatre is not part of our cinema, please choose another theatre",showtimeRequest.getTheater()));
        }
        catch (MovieNotFoundException movieExcep) {
            return ResponseEntity.status(404).body(String.format("The movie with ID %d doesn't currently show in our cinema",showtimeRequest.getMovieId()));
        }
        catch (ShowtimeOverlapException showExcep) {
            return ResponseEntity.status(409).body(String.format("The desired times for the updated showtime overlap with another one presenting at the %s theatre. Please choose a different time or a try a different theatre",showtimeRequest.getTheater()));
        }
    }

    @DeleteMapping("/{showtimeId}")
    public ResponseEntity<String> deleteShowtime(@PathVariable Long showtimeId) {
        if (showtimeId<=0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid showtimeId, was given a non-positive integer");
        }
        boolean wasShowtimeActuallyDeleted = this.showtimeService.deleteShowtime(showtimeId);
        if (wasShowtimeActuallyDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(String.format("The showtime with ID %d was deleted from the showtimes table",showtimeId));
        }
        else {
            return ResponseEntity.status(404).body(String.format("The showtime with ID %d didn't exist in the showtimes table",showtimeId));
        }
    }

    public String validateShowtimeDTOInputs(ShowtimeDTO showtimeRequest) {
        String res = "";
        if (showtimeRequest.getMovieId()<=0) {
            res+="Movie ID must be greater than 0, ";
        }
        if (showtimeRequest.getPrice()<=0) {
            res+="Price must be greater than 0 (movies aren't free), ";
        }
        if (showtimeRequest.getTheater()==null) {
            res+="The user must specify a theater in the showtimes table, ";
        }
        if (res.endsWith(", ")) {
            res = res.substring(0, res.length()-2);
        }
        return res;
    }
}