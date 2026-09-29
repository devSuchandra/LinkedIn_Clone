package com.CodingShuttle.LinkedIn.UserService.DTO;

import com.CodingShuttle.LinkedIn.UserService.Validation.PasswordWithinBcryptLimit;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequestDto {

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name must be at most 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must be at most 100 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 254, message = "Email must be at most 254 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 12, max = 128, message = "Password must be between 12 and 128 characters")
    @PasswordWithinBcryptLimit
    private String password;

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.strip();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.strip();
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.strip();
    }
}
