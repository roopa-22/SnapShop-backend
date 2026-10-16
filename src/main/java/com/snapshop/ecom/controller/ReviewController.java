package com.snapshop.ecom.controller;

import com.snapshop.ecom.exception.ProductException;
import com.snapshop.ecom.exception.UserException;
import com.snapshop.ecom.model.Review;
import com.snapshop.ecom.model.User;
import com.snapshop.ecom.request.ReviewRequest;
import com.snapshop.ecom.service.ReviewService;
import com.snapshop.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req,
                                               @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);

        Review review = reviewService.CreateReview(req,user);

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId) throws UserException, ProductException {

        List<Review> reviews = reviewService.getAllReviews(productId);

        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }
}
