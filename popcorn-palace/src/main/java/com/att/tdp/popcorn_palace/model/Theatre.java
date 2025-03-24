package com.att.tdp.popcorn_palace.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "theatres")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @NotNull
    private String theatreName;

    //Assumption: user does not try to book a seat that does not exist in the theatre.
    // i assume that the user has some kind of a map of the theatre and able to choose a seat,
    // maybe only not knowing about the availability of the seat
    //hence there is no need to verify that the user tried to do that
    //and there is no need to save a theatre's size

    public Long getId() {
        return this.id;
    }

    // no set id function so users aren't able to change the id of a theatre

    public String getTheatreName() {
        return this.theatreName;
    }

    public void setTheatreName(String inpName) {
        this.theatreName = inpName;
    }
}