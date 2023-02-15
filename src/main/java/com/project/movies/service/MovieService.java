package com.project.movies.service;

import com.project.movies.exception.MovieNotFoundException;
import com.project.movies.model.Movie;
import com.project.movies.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    public List<Movie> findAllMovies(){
        return movieRepository.findAll();
    }

    public Movie findMovieById(ObjectId id) throws MovieNotFoundException {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Id passed does not exist!"));
        return movie;
    }

    public Optional<Movie> findMovieByImdbId(String imdbId) throws MovieNotFoundException {
        return Optional.ofNullable(movieRepository.findByImdbId(imdbId).orElseThrow(() -> new MovieNotFoundException("No movie found with the imdbId : " + imdbId)));
    }
}
