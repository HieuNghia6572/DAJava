package com.example.DAJava.controller;

import com.example.DAJava.services.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class GioHangController {
    @Autowired
    private GioHangService cartService;
    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getGiohang());
        return "/cart/cart";
    }
    @PostMapping("/add")
    public String addToCart(@RequestParam Long sanphamId, @RequestParam int
            soluong) {
        cartService.addToGiohang(sanphamId, soluong);return "redirect:/cart";
    }
    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long sanphamId) {
        cartService.removeFromGiohang(sanphamId);
        return "redirect:/cart";
    }
    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}
