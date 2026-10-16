package com.snapshop.ecom.controller;

import com.snapshop.ecom.exception.CartItemException;
import com.snapshop.ecom.exception.UserException;
import com.snapshop.ecom.model.CartItem;
import com.snapshop.ecom.model.User;
import com.snapshop.ecom.response.ApiResponse;
import com.snapshop.ecom.service.CartItemService;
import com.snapshop.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse>deleteCartItem(@PathVariable Long cartItemId,
                                                     @RequestHeader("Authorization")String jwt) throws UserException, CartItemException {

        User user = userService.findUserProfileByJwt(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Deleted Item from cart");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long cartItemId,
                                                   @RequestBody CartItem cartItem,
                                                   @RequestHeader("Authorization") String jwt) throws UserException, CartItemException {

        User user = userService.findUserProfileByJwt(jwt);

        CartItem updatedCartItem = cartItemService.updateCartItem(user.getId(),cartItemId,cartItem);

        return new ResponseEntity<>(updatedCartItem,HttpStatus.OK);
    }
}
