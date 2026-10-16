package com.snapshop.ecom.service;

import com.snapshop.ecom.model.OrderItem;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemService {

    public OrderItem createOrderItem(OrderItem orderItem);
}

