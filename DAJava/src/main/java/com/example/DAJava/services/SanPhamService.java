package com.example.DAJava.services;

import com.example.DAJava.entity.SanPham;
import com.example.DAJava.repository.ISanPhamRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Transactional
@Service
//Dương Đức Tài
public class SanPhamService {
    private final ISanPhamRepository sanPhamRepository;
    // Retrieve all sanpham from the database
    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }
    // Retrieve a sanpham by its id
    public Optional<SanPham> getSanPhamById(Long id) {
        return sanPhamRepository.findById(id);
    }
    // Add a new sanpham to the database
    public SanPham addSanPham(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }


    // Delete a sanpham by its id
    public void deleteSanPham(Long id) {
        if (!sanPhamRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        sanPhamRepository.deleteById(id);
    }

    public SanPham updateSanPham(@NotNull SanPham sanPham) {
        SanPham existingSanpham = sanPhamRepository.findById(sanPham.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        sanPham.getId() + " does not exist."));
        existingSanpham.setTen(sanPham.getTen());
        existingSanpham.setGia(sanPham.getGia());
        /*existingSanpham.setDescription(product.getDescription());*/
        existingSanpham.setImgUrl(sanPham.getImgUrl());
        existingSanpham.setTheloai(sanPham.getTheloai());
        return sanPhamRepository.save(existingSanpham);
    }
}
