package com.example.controller.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.controller.Models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
	Page<Book> findByCategory(String category, Pageable pageable);
	Page<Book> findByTitleContainingIgnoreCaseAndCategory(String title, String category, Pageable pageable);

	@Query("SELECT DISTINCT b.category FROM Book b")
	List<String> findDistinctCategories();
}