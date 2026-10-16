package com.snapshop.ecom.repository;

import com.snapshop.ecom.model.Cart;
import com.snapshop.ecom.model.CartItem;
import com.snapshop.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT c FROM CartItem c where c.cart = :cart and c.product = :product and c.size = :size and c.userId = :userId")
    public CartItem isCartItemExists(@Param("cart") Cart cart, @Param("product") Product product,
                                     @Param("size") String size, @Param("userId") Long userId);
}
