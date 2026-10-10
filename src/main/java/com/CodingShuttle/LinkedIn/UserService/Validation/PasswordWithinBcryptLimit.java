package com.CodingShuttle.LinkedIn.UserService.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordWithinBcryptLimitValidator.class)
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface PasswordWithinBcryptLimit {
    String message() default "Password must be 72 bytes or less to fit BCrypt limits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
