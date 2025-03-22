package com.att.tdp.popcorn_palace.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.att.tdp.popcorn_palace.model.Showtime;
import org.springframework.stereotype.Repository;


@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    // since we are already mostly covered by default methods:
    // add a new showtime + update showtime information - save(Showtime showtime)
    // delete a showtime - deleteById(Long id)
    // fetch showtime by ID - findById(Long id)

    // Query to find overlapping showtimes at the same theatre
    @Query("SELECT s FROM Showtime s WHERE s.theatre.id = ?1 " +
    "AND ((s.startTime>=?2 AND s.startTime <= ?3)) OR " +
    "((s.endTime >= ?2 AND s.endTime <= ?3)) OR " +
    "(s.endTime < ?2 AND s.endTime > ?3)")
    List<Showtime> findOverlappingShowtimes(Long theatreId, LocalDateTime addedStartTime, LocalDateTime addedEndTime);

    @Query("SELECT s FROM Showtime s WHERE s.movie.id = ?1 " +
    "AND s.theatre.id = ?2 AND s.startTime = ?3 AND s.endTime = ?4))")
    Optional<Showtime> findByIdentifiers(Long movieId, Long theatreId, LocalDateTime addedStartTime, LocalDateTime addedEndTime);
}