package com.CodingShuttle.LinkedIn.UserService.Services;

import com.CodingShuttle.LinkedIn.UserService.DTO.SignUpRequestDto;
import com.CodingShuttle.LinkedIn.UserService.DTO.UserDto;
import com.CodingShuttle.LinkedIn.UserService.Entity.User;
import com.CodingShuttle.LinkedIn.UserService.Exception.DuplicateEmailException;
import com.CodingShuttle.LinkedIn.UserService.Repository.UserRepository;
import com.CodingShuttle.LinkedIn.UserService.Utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
        String normalizedEmail = signUpRequestDto.getEmail().strip().toLowerCase(Locale.ROOT);
        if (emailExists(normalizedEmail)) {
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
            if (emailExists(normalizedEmail)) {
                throw new DuplicateEmailException();
            }
            throw exception;
        } catch (RuntimeException exception) {
            log.error("Failed to save user during signup", exception);
            throw exception;
        }
        return modelMapper.map(savedUser, UserDto.class);
    }

    private boolean emailExists(String email) {
        try {
            return userRepository.existsByEmail(email);
        } catch (RuntimeException exception) {
            log.error("Failed to check email during signup", exception);
            throw exception;
        }
    }
}
