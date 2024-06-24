package com.example.DAJava.validator.annotation;

import com.example.DAJava.validator.ValidTheLoaiIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidTheLoaiIdValidator.class)
@Documented
public @interface ValidTheLoaiId {
    String message() default "ID Thể loại không hợp lệ ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
