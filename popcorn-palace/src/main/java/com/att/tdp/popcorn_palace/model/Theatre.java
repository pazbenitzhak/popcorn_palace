package com.att.tdp.popcorn_palace.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    private String name;

    //Assumption: user does not try to book a seat that does not exist in the theatre.
    // i assume that the user has some kind of a map of the theatre and able to choose a seat,
    // maybe only not knowing about the availability of the seat
    //hence there is no need to verify that the user tried to do that
    //and there is no need to save a theatre's size

    // @Min(1)
    // private int size;

    public Long getId() {
        return this.id;
    }

    // no set id function so users aren't able to change the id of a theatre

    public String getName() {
        return this.name;
    }

    public void setName(String inpName) {
        this.name = inpName;
    }

    // public int getSize() {
    //     return this.size;
    // }

    // public void setSize(int inpSize) {
    //     this.size = inpSize;
    // }
}