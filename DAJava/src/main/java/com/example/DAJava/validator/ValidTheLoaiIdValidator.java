package com.example.DAJava.validator;

import com.example.DAJava.entity.TheLoai;
import com.example.DAJava.validator.annotation.ValidTheLoaiId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidTheLoaiIdValidator implements ConstraintValidator<ValidTheLoaiId, TheLoai> {
    // Nếu TheLoai không null và có một ID (đã được lưu trữ), nó sẽ được coi là hợp lệ. Nếu TheLoai là null, nó sẽ được coi là không hợp lệ.
    public boolean isValid(TheLoai theloai, ConstraintValidatorContext context){
        return theloai != null && theloai.getId() != null;
    }

}
