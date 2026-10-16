package com.snapshop.ecom.service;

import com.snapshop.ecom.exception.ProductException;
import com.snapshop.ecom.model.Rating;
import com.snapshop.ecom.model.User;
import com.snapshop.ecom.request.RatingRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {

    public Rating createRating(RatingRequest req, User user) throws ProductException;

    public List<Rating> getProductsRating(Long productId);
}
