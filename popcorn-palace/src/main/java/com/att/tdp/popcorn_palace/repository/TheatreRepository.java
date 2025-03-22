package com.att.tdp.popcorn_palace.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.att.tdp.popcorn_palace.model.Theatre;
import java.util.Optional;
import org.springframework.stereotype.Repository;


@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    // we'll mostly need the basic functions that are already implemented by the extension
    // we also need to be able to search a movie Theatre by its name

    Optional<Theatre> findByName(String name);
    
}