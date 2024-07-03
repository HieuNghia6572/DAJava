package com.example.DAJava.entity;

import com.example.DAJava.validator.annotation.ValidTheLoaiId;
import com.example.DAJava.validator.annotation.ValidUserId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "sanpham")
//Dương Đức Tài
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten")
    @NotEmpty(message = "Tên không được để trống")
    @Size(max = 50, min = 1, message = "Tên phải ít hơn 50 ký tự")
    private String ten;

    private String ImgUrl;
    private String mota;

    @Column(name = "gia")
    @NotNull(message = "Giá là bắt buộc")
    private Double gia;

    @ManyToOne
    @JoinColumn(name ="theloai_id")
    @ValidTheLoaiId
    private TheLoai theloai;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ValidUserId
    private User user;
}
