package com.project.movies.service;

import com.project.movies.exception.MovieNotFoundException;
import com.project.movies.model.Movie;
import com.project.movies.model.Review;
import com.project.movies.repository.MovieRepository;
import com.project.movies.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String imdbId, String reviewBody) throws MovieNotFoundException {
        Review review = reviewRepository.insert(new Review(reviewBody));
        movieRepository.findByImdbId(imdbId).orElseThrow(()-> new MovieNotFoundException("Movie with ImdbId: "+imdbId+" not found."));
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();
        return review;
    }

}
