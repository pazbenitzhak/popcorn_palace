package com.att.tdp.popcorn_palace.DTOs;

//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.NotBlank;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieDTO {

    private String title;
    private String genre;
    private int duration;
    private double rating;
    private int releaseYear;


}