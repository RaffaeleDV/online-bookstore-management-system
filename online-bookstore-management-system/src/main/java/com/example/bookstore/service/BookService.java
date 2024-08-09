// BookService.java
package com.example.bookstore.service;

import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }    

    public Book findById(Long bookId) {
        return bookRepository.findById(bookId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + bookId));
    }

    

    // Other methods for updating, deleting, etc.
}
