package com.example.DAJava.controller;

import com.example.DAJava.entity.SanPham;
import com.example.DAJava.services.SanPhamService;
import com.example.DAJava.services.TheLoaiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
//Dương Đức Tài
@Controller
@RequiredArgsConstructor
@RequestMapping("/sanphams")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private TheLoaiService theLoaiService;
    // Đảm bảo bạn đã injectCategoryService
    // Display a list of all sanpham
    @GetMapping
    public String showSanphamList(Model model) {
        model.addAttribute("sanphams", sanPhamService.getAllSanPham());
        return "/sanphams/sanphams-list";
    }
    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("sanpham", new SanPham());
        model.addAttribute("theloais", theLoaiService.getAllTheloai()); //Load sanphams
        return "/sanphams/add-sanpham";
    }
    // Process the form for adding a new product
    @PostMapping("/add")
    public String  addSanpham(@Valid SanPham sanPham, BindingResult result, @RequestParam("image") MultipartFile imageFile) {
        if (result.hasErrors()) {
            return "/sanphams/add-sanpham";
        }
        if (!imageFile.isEmpty()) {
            try {
                String imageName = saveImage(imageFile);
                sanPham.setImgUrl("/images/" +imageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sanPhamService.addSanPham(sanPham);
        return "redirect:/sanphams";
    }
    private String saveImage(MultipartFile image) throws IOException {
        File saveFile = new ClassPathResource("static/images").getFile();
        String fileName = UUID.randomUUID()+ "." + StringUtils.getFilenameExtension(image.getOriginalFilename());
        Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName);
        Files.copy(image.getInputStream(), path);
        return fileName;
    }
    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        SanPham sanPham = sanPhamService.getSanPhamById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid sanpham Id:" + id));
        model.addAttribute("sanpham", sanPham);
        model.addAttribute("theloais", theLoaiService.getAllTheloai()); //Load theloais
        return "/sanphams/update-sanpham";
    }
    // Process the form for updating a product
    @PostMapping("/update/{id}")
    public String updateSanPham(@PathVariable Long id, @Valid SanPham sanPham,
                                BindingResult result,@RequestParam("image") MultipartFile imageFile ) {
        if (result.hasErrors()) {
            sanPham.setId(id); // set id to keep it in the form in case of errors
            return "/sanphams/update-sanpham";
        }
        sanPhamService.updateSanPham(sanPham);
        return "redirect:/sanphams";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteSanPham(@PathVariable Long id) {
        sanPhamService.deleteSanPham(id);
        return "redirect:/sanphams";
    }
}
