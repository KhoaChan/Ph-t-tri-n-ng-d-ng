package com.example.controller.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.controller.Models.Book;
import com.example.controller.Repositories.BookRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Page<Book> searchBooks(String keyword, String category, Pageable pageable) {
        if ((keyword == null || keyword.isBlank()) && (category == null || category.isBlank())) {
            return bookRepository.findAll(pageable);
        }
        if (keyword == null || keyword.isBlank()) {
            return bookRepository.findByCategory(category, pageable);
        }
        if (category == null || category.isBlank()) {
            return bookRepository.findByTitleContainingIgnoreCase(keyword, pageable);
        }
        return bookRepository.findByTitleContainingIgnoreCaseAndCategory(keyword, category, pageable);
    }

    public List<String> getAllCategories() {
        return bookRepository.findDistinctCategories();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public void updateBook(Book updatedBook) {
        bookRepository.save(updatedBook); // Nếu đối tượng đã có ID, hàm save sẽ thực hiện lệnh Update
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}