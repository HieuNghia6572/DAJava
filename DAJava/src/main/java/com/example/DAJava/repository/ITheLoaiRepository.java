package com.example.DAJava.repository;

import com.example.DAJava.entity.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Dương Đức Tài
@Repository
public interface ITheLoaiRepository extends JpaRepository<TheLoai, Long> {
}
