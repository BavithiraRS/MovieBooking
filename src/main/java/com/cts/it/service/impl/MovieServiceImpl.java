package com.cts.it.service.impl;

import com.cts.it.exception.IdNotFoundException;
import com.cts.it.model.Movie;
import com.cts.it.repository.MovieRepository;
import com.cts.it.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    

    @Override
    public Movie pushMovie(Movie newMovie) {
        return null;
    }

    @Override
    public Movie updateMovie(Movie updatedMovie, int movie_id) {
        return null;
    }

    @Override
    public void deleteMovie(int movie_id) {
        movieRepository.deleteById(movie_id);
    }

	@Override
	public Movie getMovieById(int movie_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
