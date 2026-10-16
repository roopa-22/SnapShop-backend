package com.snapshop.ecom.service;

import com.snapshop.ecom.exception.CartItemException;
import com.snapshop.ecom.exception.UserException;
import com.snapshop.ecom.model.Cart;
import com.snapshop.ecom.model.CartItem;
import com.snapshop.ecom.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;


}
