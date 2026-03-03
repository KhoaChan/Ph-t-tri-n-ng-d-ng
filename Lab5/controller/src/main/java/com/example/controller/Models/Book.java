package com.example.controller.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Đánh dấu đây là một Entity của JPA
public class Book {
    
    @Id // Xác định khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng ID
    private Long id;
    
    private String title;
    private String author;
    private String image;
    private Double price;

    public Book() {}

    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // Bạn giữ nguyên toàn bộ Getter và Setter ở dưới nhé
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}