package com.example.DAJava.controller;

import com.example.DAJava.entity.SanPham;
import com.example.DAJava.services.SanPhamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/sanphams")
public class SanPhamController {
    @Autowired
    private final SanPhamService sanphamService;

/*    @Autowired
    private final TheLoaiyService theloaiService;*/


    @GetMapping
    public String showAllSanPhams(Model model){
        List<SanPham> sanphams = sanphamService.getAllSanPhams();
        model.addAttribute("sanphams", sanphams);
        return "sanpham/list";
    }

    @GetMapping("/")
    public String home(){ return "home/index";}

    @GetMapping("/add")
    public String addSanPhamForm(Model model) {
        model.addAttribute("sanpham",new SanPham());
  /*      model.addAttribute("theloais",theloaiService.getAllTheLoais());*/
        return "sanpham/add";
    }

    @PostMapping("/add")
    public String addSanPham(@Valid @ModelAttribute("sanpham") SanPham sanpham, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
           /* model.addAttribute("theloais",theloaiService.getAllTheLoais());*/
            return "sanpham/add";
        }
        sanphamService.addSanPham(sanpham);
        return "redirect:/sanphams";
    }

    @GetMapping("/delete/{id}")
    public String deleteSanPham(@PathVariable("id") Long id) {
        sanphamService.deleteSanPham(id);
        return "redirect:/sanphams";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        SanPham sanpham = sanphamService.getSanPhamById(id);
        model.addAttribute("sanpham", sanpham);
        /*model.addAttribute("theloais", theloaiService.getAllCategories());*/
        return "/sanpham/edit";
    }

    //test
    @PostMapping("/edit/{id}")
    public String updateSanPham(@PathVariable("id") Long id, @Valid SanPham sanpham,
                             BindingResult result) {
        if (result.hasErrors()) {
            sanpham.setId(id);
            return "/sanpham/edit";
        }
        sanphamService.updateSanPham(sanpham);
        /*model.addAttribute("theloais", theloaiService.getAllTheLoais());*/
        return "redirect:/sanphams";
    }

}
