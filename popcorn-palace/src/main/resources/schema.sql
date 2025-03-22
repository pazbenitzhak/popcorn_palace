-- CREATE TABLE IF NOT EXISTS task (
--                                     description VARCHAR(64) NOT NULL,
--     completed   VARCHAR(30) NOT NULL);
CREATE TABLE IF NOT EXISTS movies (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(80) NOT NULL,
    genre VARCHAR(20), 
    duration INT, 
    rating DECIMAL(3,1),
    release_year INT NOT NULL);  

CREATE TABLE IF NOT EXISTS theatres (
    id BIGSERIAL PRIMARY KEY,
    -- theatre_name  VARCHAR(60) NOT NULL,
    theatre_size INT NOT NULL
    ); 

CREATE TABLE IF NOT EXISTS showtimes (
    id BIGSERIAL PRIMARY KEY,
    price DECIMAL(3,1) NOT NULL,
    movie_id INT NOT NULL,
    theatre_id INT NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies(id),
    FOREIGN KEY (theatre_id) REFERENCES theatres(id)
    ); 


CREATE TABLE IF NOT EXISTS bookings (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    showtime_id INT NOT NULL,
    seat_number INT NOT NULL,
    FOREIGN KEY (showtime_id) REFERENCES showtimes(id)
    ); 