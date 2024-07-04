package com.example.DAJava.services;



import com.example.DAJava.entity.CartItem;
import com.example.DAJava.entity.SanPham;
import com.example.DAJava.repository.ISanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();
    @Autowired
    private ISanPhamRepository productRepository;

    public CartItem getCartItemByProductId(Long productId) {
        return cartItems.stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    public void addToCart(Long productId, int quantity) {
        SanPham product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
        CartItem existingItem = getCartItemByProductId(productId);
        if (existingItem != null) {
            // Sản phẩm đã có trong giỏ hàng, tăng số lượng
            int newQuantity = existingItem.getQuantity() + quantity;
            existingItem.setQuantity(newQuantity);
        } else {
            // Sản phẩm chưa có trong giỏ hàng, thêm mới
            cartItems.add(new CartItem(product, quantity));
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void removeFromCart(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public void clearCart() {
        cartItems.clear();
    }

    public void updateQuantity(Long productId, int quantity) {
        CartItem item = getCartItemByProductId(productId);
        if (item != null) {
            item.setQuantity(quantity);
        }
    }

    public double getTotalPrice() {
        return cartItems.stream()
                .mapToDouble(item -> item.getProduct().getGia() * item.getQuantity())
                .sum();
    }
}
