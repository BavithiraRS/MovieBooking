package com.cts.it.service;

import com.cts.it.model.Movie;
import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    Movie getMovieById(int movie_id);

    Movie pushMovie(Movie newMovie);

    Movie updateMovie(Movie updatedMovie, int movie_id);

    void deleteMovie(int movie_id);

}