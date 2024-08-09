package com.example.bookstore.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(Long userId) {

        return userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        
    }

    
}
