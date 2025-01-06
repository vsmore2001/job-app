package com.embarkx.firstjobapp.review;

import java.util.List;

public interface ReviewService {
    public List<Review> getAllReviews(Long companyId);
    public boolean addReview(Long companyId, Review review);
    public Review getReviewById(Long companyId, Long reviewId);
    public boolean updateReviewById(Long companyId, Long reviewId, Review review);
    public boolean deleteReviewById(Long companyId, Long reviewId);
}
