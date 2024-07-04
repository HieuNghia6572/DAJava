package com.example.DAJava.controller;

import com.example.DAJava.entity.CartItem;
import com.example.DAJava.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        return "/cart/cart";
    }
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        CartItem existingItem = cartService.getCartItemByProductId(productId);
        if (existingItem != null) {
            // Sản phẩm đã có trong giỏ hàng, tăng số lượng
            int newQuantity = existingItem.getQuantity() + quantity;
            cartService.updateQuantity(productId, newQuantity);
        } else {
            // Sản phẩm chưa có trong giỏ hàng, thêm mới
            cartService.addToCart(productId, quantity);
        }
        return "redirect:/cart";
    }
    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }
    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
    @PostMapping("/update")
    @ResponseBody
    public Map<String, Double> updateQuantity(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.updateQuantity(productId, quantity);

        // Tính toán giá mới của sản phẩm
        double newPrice = cartService.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .map(item -> item.getProduct().getGia() * quantity)
                .orElse(0.0);

        // Tính toán tổng giá trị giỏ hàng
        double totalPrice = cartService.getTotalPrice();

        Map<String, Double> response = new HashMap<>();
        response.put("newPrice", newPrice);
        response.put("totalPrice", totalPrice);
        return response;
    }

}
