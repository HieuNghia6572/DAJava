package com.example.DAJava.controller;

import com.example.DAJava.entity.CartItem;
import com.example.DAJava.entity.Order;
import com.example.DAJava.services.CartService;
import com.example.DAJava.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @GetMapping("/checkout")
    public String checkout(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        double totalPrice = cartService.getTotalPrice();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);
        return "/cart/checkout";
    }

    @PostMapping("/submit")
    public String submitOrder(String customerName, String customerAddress, String customerPhone, String customerNote) {
        List<CartItem> cartItems = cartService.getCartItems();
        if (cartItems.isEmpty()) {
            return "redirect:/cart"; // Redirect if cart is empty
        }
        orderService.createOrder(customerName, customerAddress, customerPhone, customerNote, cartItems);
        return "redirect:/order/confirmation";
    }

    @GetMapping("/confirmation")
    public String orderConfirmation(Model model) {
        model.addAttribute("message", "Your order has been successfully placed.");
        return "cart/order-confirmation";
    }

    @GetMapping("/list")
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/list";
    }

    @GetMapping("/details/{id}")
    public String orderDetails(@PathVariable("id") Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("totalPrice", cartService.getTotalPrice());
        model.addAttribute("order", order);

        return "order/details";
    }
}