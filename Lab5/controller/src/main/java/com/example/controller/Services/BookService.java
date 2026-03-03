package com.example.controller.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.controller.Models.Book;
import com.example.controller.Repositories.BookRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll(); // Lấy từ Database
    }

    public void addBook(Book book) {
        bookRepository.save(book); // JPA tự động tạo ID và lưu
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