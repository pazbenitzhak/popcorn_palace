package com.att.tdp.popcorn_palace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;


public interface BookingRepository extends JpaRepository<Booking, Long> {
    // no need to put functions here, since we are already covered by default methods:
    // add a new movie + update movie information - save(movie M)
    // delete a new movie - deleteById(Long id)
    // need to option to query by showtimeId + seat number

    @Query("SELECT b FROM Booking b WHERE b.showtime.id = ?1 " +
    "AND b.seatNumber = ?2")
    Optional<Booking> findExistingSeatBookings(Long showtimeId, int seatNumber);

    
}