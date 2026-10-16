package com.snapshop.ecom.service;

import com.snapshop.ecom.exception.ProductException;
import com.snapshop.ecom.model.Cart;
import com.snapshop.ecom.model.User;
import com.snapshop.ecom.request.AddItemRequest;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    public Cart createCart(User user);

    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;

    public Cart findUserCart(Long userId);


}
