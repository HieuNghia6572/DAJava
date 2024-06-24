package com.example.DAJava.controller;

import com.example.DAJava.entity.TheLoai;
import com.example.DAJava.services.TheLoaiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
//Dương Đức Tài
@Controller
@RequiredArgsConstructor
public class TheLoaiController {
    @Autowired
    private final TheLoaiService theLoaiService;

    @GetMapping("/theloais/add")
    public String showAddForm(Model model) {
        model.addAttribute("theloai", new TheLoai());
        return "/theloais/add-theloai";
    }

    @PostMapping("/theloais/add")
    public String addTheloai(@Valid TheLoai theLoai, BindingResult result) {
        if (result.hasErrors()) {
            return "/theloais/add-theloai";
        }
        theLoaiService.addTheloai(theLoai);
        return "redirect:/theloais";
    }

    // Hiển thị danh sách danh mục
    @GetMapping("/theloais")
    public String listTheloais(Model model) {
        List<TheLoai> theLoais = theLoaiService.getAllTheloai();
        model.addAttribute("theloais", theLoais);
        return "/theloais/theloais-list";
    }
    @GetMapping("/theloais/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        TheLoai theLoai = theLoaiService.getTheloaiById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid theloai Id:"
                        + id));
        model.addAttribute("theloai", theLoai);
        return "/theloais/update-theloai";
    }
    // POST request to update theloai
    @PostMapping("/theloais/update/{id}")
    public String updateTheloai(@PathVariable("id") Long id, @Valid TheLoai theLoai,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            theLoai.setId(id);
            return "/theloais/update-theloai";
        }
        theLoaiService.updateTheloai(theLoai);
        model.addAttribute("theloais", theLoaiService.getAllTheloai());
        return "redirect:/theloais";
    }
    // GET request for deleting theloai
    @GetMapping("/theloais/delete/{id}")
    public String deleteTheloaiById(@PathVariable("id") Long id, Model model) {
        TheLoai theLoai = theLoaiService.getTheloaiById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid theloai Id:"
                        + id));
        theLoaiService.deleteTheloaiById(id);
        model.addAttribute("theloais", theLoaiService.getAllTheloai());
        return "redirect:/theloais";
    }
}
