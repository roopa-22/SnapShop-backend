package com.snapshop.ecom.repository;

import com.snapshop.ecom.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("SELECT c FROM Cart c where c.user.id = :userId")
    public Cart findByUserId(@Param("userId") Long userId);
}
