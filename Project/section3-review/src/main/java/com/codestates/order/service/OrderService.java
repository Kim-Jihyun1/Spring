package com.codestates.order.service;

import com.codestates.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    public Order createOrder(Order order) {
        return order;
    }

    public Order findOrder(long orderId) {
        return new Order(1L, 1L);
    }

    // 주문 수정 메서드는 사용하지 않습니다.

    public List<Order> findOrders() {
        return List.of(new Order(1L, 1L),
                new Order(2L, 2L));
    }

    public void cancelOrder() {
    }
}
