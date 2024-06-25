package com.example.DAJava.entity;

public class CartItem {
    private SanPham product;
    private int quantity;
    // Constructors
    public CartItem(SanPham product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    // Getters and Setters
    public SanPham getProduct() {
        return product;
    }
    public void setProduct(SanPham product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}