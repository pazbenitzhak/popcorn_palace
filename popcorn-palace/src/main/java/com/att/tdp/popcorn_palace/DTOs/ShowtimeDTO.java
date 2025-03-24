package com.att.tdp.popcorn_palace.DTOs;

import com.att.tdp.popcorn_palace.model.Movie;
import com.att.tdp.popcorn_palace.model.Theatre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.NotBlank;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShowtimeDTO {
    private Long movieId;
    private double price;
    private String theater;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}