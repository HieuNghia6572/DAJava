package com.example.DAJava.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chitiet_donhang")
public class ChiTietDonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int soluong;

    @ManyToOne
    @JoinColumn(name = "sanpham_id")
    private SanPham sanpham;
    @ManyToOne
    @JoinColumn(name = "donhang_id")
    private DonHang donhang;
}
