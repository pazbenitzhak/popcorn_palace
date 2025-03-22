package com.att.tdp.popcorn_palace;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieRequest {

    @NotBlank(message = "Title can't be an empty string")
    @Size(max= 80, "Title can't be longer than 80 characters")
    private String title;

    @Size(max=20, "Genre can't be longer than 20 characters")
    private String genre;
    

    @Min(value = 1,"Movie duration can't be shorter than a minute")
    private int duration;


    @Min(value = 0.0, message = "rating should be at least 0.0")
    @Max(value = 10.0, message = "rating must be at max 10.0")
    private double rating;

    @NotBlank(message = "Release Year must be stated")
    private int releaseYear;


}