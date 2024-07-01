package com.example.DAJava.controller;

import com.example.DAJava.entity.SanPham;
import com.example.DAJava.services.SanPhamService;
import com.example.DAJava.services.TheLoaiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

    @Controller
    @RequiredArgsConstructor

    public class CustomerController {
        @Autowired
        private SanPhamService sanPhamService;
        @Autowired
        private TheLoaiService theLoaiService;
        // Đảm bảo bạn đã injectCategoryService
        // Display a list of all sanpham
        @GetMapping("/home")
        public String showSanphamList(Model model) {
            model.addAttribute("sanphams", sanPhamService.getAllSanPham());
            return "/customers/index";
        }
         @GetMapping("/about")
        public String About(Model model) {
            model.addAttribute("sanphams", sanPhamService.getAllSanPham());
            return "/customers/about";
        }
        @GetMapping("/contact")
        public String Contact(Model model) {
            model.addAttribute("sanphams", sanPhamService.getAllSanPham());
            return "/customers/contact";
        }
        //TD Detail
        @GetMapping("/details/{id}")
        public String Detail(@PathVariable Long id, Model model)
        {
            SanPham sanPham = sanPhamService.getSanPhamById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
            model.addAttribute("sanpham", sanPham);
            return "customers/product-detail";
        }
        @GetMapping("/blog")
        public String Blog(Model model) {
            model.addAttribute("sanphams", sanPhamService.getAllSanPham());
            return "/customers/blog";
        }
        @GetMapping("/product")
        public String ViewProduct(Model model) {
            model.addAttribute("sanphams", sanPhamService.getAllSanPham());
            return "/customers/product";
        }
}
