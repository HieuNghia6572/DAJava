package com.example.DAJava.entity;

public class GioHang {
    private SanPham sanpham;
    private int soluong;
    // Constructors
    public GioHang(SanPham sanpham, int soluong) {
        this.sanpham = sanpham;
        this.soluong = soluong;
    }
    // Getters and Setters
    public SanPham getSanpham() {
        return sanpham;
    }
    public void setSanpham(SanPham sanpham) {
        this.sanpham = sanpham;
    }
    public int getSoluong() {
        return soluong;
    }
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
