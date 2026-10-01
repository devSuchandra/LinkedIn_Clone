package com.CodingShuttle.LinkedIn.UserService.Services;

import com.CodingShuttle.LinkedIn.UserService.DTO.LogInRequestDto;
import com.CodingShuttle.LinkedIn.UserService.DTO.SignUpRequestDto;
import com.CodingShuttle.LinkedIn.UserService.DTO.UserDto;
import com.CodingShuttle.LinkedIn.UserService.Entity.User;
import com.CodingShuttle.LinkedIn.UserService.Exception.DuplicateEmailException;
import com.CodingShuttle.LinkedIn.UserService.Repository.UserRepository;
import com.CodingShuttle.LinkedIn.UserService.Utils.PasswordUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
        String normalizedEmail = signUpRequestDto.getEmail().strip().toLowerCase(Locale.ROOT);
        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new DuplicateEmailException();
        }

        User user = modelMapper.map(signUpRequestDto, User.class);
        user.setFirstName(signUpRequestDto.getFirstName().strip());
        user.setLastName(signUpRequestDto.getLastName().strip());
        user.setEmail(normalizedEmail);
        user.setPassword(PasswordUtils.hashPassword(signUpRequestDto.getPassword()));
        User savedUser;
        try {
            savedUser = userRepository.saveAndFlush(user);
        } catch (DataIntegrityViolationException exception) {
            if (userRepository.existsByEmail(normalizedEmail)) {
                throw new DuplicateEmailException();
            }
            throw exception;
        }
        return modelMapper.map(savedUser, UserDto.class);
    }

    public String logIn(@Valid LogInRequestDto logInRequestDto) {
        String normalizedEmail = logInRequestDto.getEmail().strip().toLowerCase(Locale.ROOT);
        User user = userRepository.findByEmail(normalizedEmail)
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        boolean isPasswordValid = PasswordUtils.verifyPassword(logInRequestDto.getPassword(), user.getPassword());

        if (!isPasswordValid) {
            throw new BadCredentialsException("Invalid email or password");
        }
        return jwtService.generateAccessToken(user);
    }
}
