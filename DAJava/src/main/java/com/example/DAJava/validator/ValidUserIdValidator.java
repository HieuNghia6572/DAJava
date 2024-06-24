package com.example.DAJava.validator;

import com.example.DAJava.entity.User;
import com.example.DAJava.validator.annotation.ValidUserId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    // Nếu User có một ID (đã được lưu trữ), nó sẽ được coi là hợp lệ. Nếu User là null, nó cũng được coi là hợp lệ.
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context){
        if (user == null)
            return true;
        return user.getId() != null;
    }
}
