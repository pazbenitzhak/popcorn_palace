package com.att.tdp.popcorn_palace.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import com.att.tdp.popcorn_palace.model.Booking;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // no need to put functions here, since we are already covered by default methods:
    // add a new movie + update movie information - save(movie M)
    // delete a new movie - deleteById(Long id)
    // need to option to query by showtimeId + seat number

    @Query("SELECT b FROM Booking b WHERE b.showtime.id = ?1 " +
    "AND b.seatNumber = ?2")
    Optional<Booking> findExistingSeatBookings(Long showtimeId, int seatNumber);

    
}