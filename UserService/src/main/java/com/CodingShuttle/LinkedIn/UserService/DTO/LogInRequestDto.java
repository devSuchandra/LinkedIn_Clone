package com.CodingShuttle.LinkedIn.UserService.DTO;

import lombok.Data;

@Data
public class LogInRequestDto {
    private String email;
    private String password;
}
