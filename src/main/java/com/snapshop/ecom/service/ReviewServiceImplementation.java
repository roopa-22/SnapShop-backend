package com.snapshop.ecom.service;

import com.snapshop.ecom.exception.ProductException;
import com.snapshop.ecom.model.Product;
import com.snapshop.ecom.model.Review;
import com.snapshop.ecom.model.User;
import com.snapshop.ecom.repository.ProductRepository;
import com.snapshop.ecom.repository.ReviewRepository;
import com.snapshop.ecom.request.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Review CreateReview(ReviewRequest req, User user) throws ProductException {
        Product product = productService.findProductById(req.getProductId());

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
