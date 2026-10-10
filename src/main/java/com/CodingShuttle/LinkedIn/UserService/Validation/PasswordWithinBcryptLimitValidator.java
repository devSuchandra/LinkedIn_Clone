package com.CodingShuttle.LinkedIn.UserService.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.nio.charset.StandardCharsets;

public class PasswordWithinBcryptLimitValidator
        implements ConstraintValidator<PasswordWithinBcryptLimit, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isBlank()) {
            return true;
        }
        return password.getBytes(StandardCharsets.UTF_8).length <= 72;
    }
}
