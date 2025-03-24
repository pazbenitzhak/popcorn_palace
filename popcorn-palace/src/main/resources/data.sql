DELETE FROM bookings;
DELETE FROM showtimes;
DELETE FROM movies;
DELETE FROM theatres;
SELECT setval(pg_get_serial_sequence('movies', 'id'), 1, false);
SELECT setval(pg_get_serial_sequence('showtimes', 'id'), 1, false);
SELECT setval(pg_get_serial_sequence('theatres', 'id'), 1, false);
INSERT INTO movies (title, genre, duration, rating, release_year) VALUES
                                               ('The Dark Knight', 'action', 120, 8.7, 2008),
                                               ('Iron Man', 'action', 111, 8.4,2008);
INSERT INTO theatres (theatre_name) VALUES
                               ('Dolby'),
                               ('Chewbacca'),
                               ('Marvel'),
                               ('Disney');