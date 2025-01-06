package com.embarkx.firstjobapp.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    // review service
    private final ReviewService reviewService;

    // constructor
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }
    
    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if(isReviewSaved) {
            return new ResponseEntity<>(review, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> updateReviewById(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        boolean updated = reviewService.updateReviewById(companyId, reviewId, review);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean deleted = reviewService.deleteReviewById(companyId, reviewId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



}
