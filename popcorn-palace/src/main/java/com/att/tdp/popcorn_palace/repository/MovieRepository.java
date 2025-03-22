package com.att.tdp.popcorn_palace.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import com.att.tdp.popcorn_palace.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // no need to put functions here, since we are already covered by default methods:
    // add a new movie + update movie information - save(movie M)
    // delete a new movie - deleteById(Long id)
    // fetch all movies - findAll()

    //need to be able to find a movie by its title for the update function
    Optional<Movie> findByTitle(String title);
    
}