package com.embarkx.firstjobapp.review.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyService;
import com.embarkx.firstjobapp.review.Review;
import com.embarkx.firstjobapp.review.ReviewRepository;
import com.embarkx.firstjobapp.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
    
    // review repository
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;
    
    // constructor
    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }
    
    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    //overide
    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReviewById(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.getCompanyById(companyId) != null) {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompanyById(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

}
