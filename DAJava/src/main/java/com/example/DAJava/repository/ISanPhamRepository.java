package com.example.DAJava.repository;

import com.example.DAJava.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Dương Đức Tài
@Repository
public interface ISanPhamRepository extends JpaRepository<SanPham, Long> {
}
