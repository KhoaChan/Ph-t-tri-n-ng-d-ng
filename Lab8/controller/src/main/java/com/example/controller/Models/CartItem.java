package com.example.controller.Models;

public class CartItem {
    private Long bookId;
    private String title;
    private Double price;
    private int quantity;

    public CartItem() {}

    public CartItem(Long bookId, String title, Double price, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Double getTotal() { return price * quantity; }
}
