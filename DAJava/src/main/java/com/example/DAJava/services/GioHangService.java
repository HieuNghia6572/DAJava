package com.example.DAJava.services;

import com.example.DAJava.entity.GioHang;
import com.example.DAJava.entity.SanPham;
import com.example.DAJava.repository.ISanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
@Service
@SessionScope
public class GioHangService {
    private List<GioHang> giohangs = new ArrayList<>();
    @Autowired
    private ISanPhamRepository sanphamRepository;
    public void addToGiohang(Long sanphamId, int soluong) {
        SanPham sanpham = sanphamRepository.findById(sanphamId).orElseThrow(() -> new IllegalArgumentException("Product not found: " + sanphamId));
        giohangs.add(new GioHang(sanpham, soluong));
    }
    public List<GioHang> getGiohang() {
        return giohangs;
    }
    public void removeFromGiohang(Long sanphamId) {
        giohangs.removeIf(item -> item.getSanpham().getId().equals(sanphamId));
    }
    public void clearCart() {
        giohangs.clear();
    }
}
