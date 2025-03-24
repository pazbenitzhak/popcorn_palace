package com.att.tdp.popcorn_palace;

import com.att.tdp.popcorn_palace.controller.*;
import com.att.tdp.popcorn_palace.model.*;
import com.att.tdp.popcorn_palace.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@WebMvcTest(controllers = {MovieController.class, ShowtimeController.class, BookingController.class})
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PopcornPalaceApplicationTests {
//	@Autowired
	private MockMvc mockMvc;

	@Mock
	private MovieController movieController;

	@Mock
	private BookingController bookingController;

	@Mock
	private ShowtimeController showtimeController;

	private Movie movie1, movie2, movie3;
	private Showtime showtime;
	private Booking booking;
	private Theatre theatre;
    @Autowired
    private MovieService movieService;

	@BeforeEach
	void setUp() {
		movie1 = new Movie(1L,"The Dark Knight",2008,"action",120,8.7);
		movie2 = new Movie(2L,"Iron Man",2008,"action",111,8.4);
		movie3 = new Movie(3L,"Oppenheimer",2023,"thriller", 180,9.1);
		theatre = new Theatre(1L,"Dolby");
		showtime = new Showtime(1L,movie1,theatre, LocalDateTime.of(2025, 1, 7, 11, 11),LocalDateTime.of(2025, 1, 7, 13, 41),30.5);
//		booking = new Booking()
	}

	@Test
	//here at first there are supposed to be only 2 films: Dark Knight and Iron Man
	void getAllMovies() throws Exception {
		List<Movie> movies = Arrays.asList(movie1, movie2);
		when(movieService.getAllMovies()).thenReturn(movies);
		mockMvc.perform(get("/movies"))
				.andExpect(status().isOk())
				.andExpect((ResultMatcher) jsonPath("$[0].title").value("The Dark Knight"))
				.andExpect((ResultMatcher) jsonPath("$[1].title").value("Iron Man"));

	}

	@Test
	void contextLoads() {
	}

}
