package com.att.tdp.popcorn_palace;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Min(1)
    private int size;

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

    public int getSize() {
        return this.size;
    }

    public void setSize(int inpSize) {
        this.size = inpSize;
    }
}