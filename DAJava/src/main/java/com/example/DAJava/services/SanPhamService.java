package com.example.DAJava.services;

import com.example.DAJava.entity.SanPham;
import com.example.DAJava.repository.ISanPhamRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SanPhamService {
    @Autowired
    private ISanPhamRepository sanphamRepository;

    public SanPham getSanPhamById(Long id){
        return sanphamRepository.findById(id).orElse(null);
    }

    public void addSanPham(SanPham sanpham){
        sanphamRepository.save(sanpham);
    }

    public List<SanPham> getAllSanPhams(){
        return sanphamRepository.findAll();
    }

    public void deleteSanPham(Long id){
        sanphamRepository.deleteById(id);
    }

    public void updateSanPham(@NotNull SanPham sanpham){
        if (sanpham == null || sanpham.getId() == null) {
            throw new IllegalArgumentException("Invalid book object");
        }

        SanPham existingSanPham = getSanPhamById(sanpham.getId());


        existingSanPham.setTen(sanpham.getTen());
        existingSanPham.setGia(sanpham.getGia());
/*
        existingSanPham.setCategory(sanpham.getCategory());
*/


        sanphamRepository.save(existingSanPham);
    }
}
