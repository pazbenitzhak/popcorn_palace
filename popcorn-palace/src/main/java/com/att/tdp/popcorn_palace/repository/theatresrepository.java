package com.att.tdp.popcorn_palace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    // we'll mostly need the basic functions that are already implemented by the extension
    // we also need to be able to search a movie Theatre by its name

    List<Theatre> findByName(String name);
    
}