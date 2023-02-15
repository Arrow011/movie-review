package com.project.movies.controllers;

import com.project.movies.exception.MovieNotFoundException;
import com.project.movies.model.Review;
import com.project.movies.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody Map<String,String> payload) throws MovieNotFoundException {
        return new ResponseEntity<Review>(reviewService.createReview(payload.get("imdbId"),payload.get("reviewBody")),
                HttpStatus.CREATED);
    }
}
