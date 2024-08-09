// OrderService.java
package com.example.bookstore.service;

import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderItem;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    //@Autowired
    //private UserRepository userRepository;

    public List<Order> findOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order createOrder(User user, List<OrderItem> items) {
        Order order = new Order();
        order.setUser(user);
        order.setItems(items);
        order.setTotalPrice(calculateTotalPrice(items));
        //order.setStatus(OrderStatus.PENDING);
        return orderRepository.save(order);
    }

    private double calculateTotalPrice(List<OrderItem> items) {
        return items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }

    // Other methods for updating order status, etc.

    public void save(Order order){
        orderRepository.save(order);
    }
}


enum OrderStatus {
    PENDING, COMPLETED, CANCELLED
}
