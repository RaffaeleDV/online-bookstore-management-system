// OrderController.java
package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;
import com.example.bookstore.model.OrderItem;
import com.example.bookstore.model.User;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderService.findOrdersByUser(userId);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long userId, @RequestBody List<OrderItemRequest> items) {
        // Retrieve the user by ID
        User user = userService.findById(userId);
            

        // Create a new order
        Order order = new Order();
        order.setUser(user);
        //order.setOrderDate(LocalDateTime.now());
        //order.setStatus(OrderStatus.PENDING);

        Double totalPrice = 0.0;

        // Process each item in the order
        for (OrderItemRequest itemRequest : items) {
            // Retrieve the book by ID
            Book book = bookService.findById(itemRequest.getBookId());
                

            // Calculate the price for this item
            Double itemPrice = book.getPrice() * (itemRequest.getQuantity());
            totalPrice = totalPrice += (itemPrice);

            // Create an OrderItem object
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(book);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(itemPrice);

            // Add the OrderItem to the order
            order.getItems().add(orderItem);
        }

        // Set the total price of the order
        order.setTotalPrice(totalPrice);

        // Save the order to the database
        orderService.save(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}

class OrderItemRequest {
    private Long bookId;
    private int quantity;
    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Constructors, Getters, and Setters
}


enum OrderStatus {
    PENDING, COMPLETED, CANCELLED
}