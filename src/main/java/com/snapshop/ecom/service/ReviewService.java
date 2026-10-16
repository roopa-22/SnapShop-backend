package com.snapshop.ecom.service;

import com.snapshop.ecom.exception.ProductException;
import com.snapshop.ecom.model.Review;
import com.snapshop.ecom.model.User;
import com.snapshop.ecom.request.ReviewRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    public Review CreateReview(ReviewRequest req, User user) throws ProductException;

    public List<Review> getAllReviews(Long productId);


}
