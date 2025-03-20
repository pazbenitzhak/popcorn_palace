package com.att.tdp.popcorn_palace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // no need to put functions here, since we are already covered by default methods:
    // add a new movie + update movie information - save(movie M)
    // delete a new movie - deleteById(Long id)
    // fetch all movies - findAll()
    
}