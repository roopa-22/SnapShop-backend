package com.snapshop.ecom.repository;

import com.snapshop.ecom.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o where o.user.id = :userId AND o.orderStatus IN ('PLACED','CONFIRMED','SHIPPED','DELEIVERED')")
    public List<Order> getUsersOrders(@Param("userId") Long userId);
}
