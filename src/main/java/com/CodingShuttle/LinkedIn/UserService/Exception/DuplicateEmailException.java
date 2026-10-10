package com.CodingShuttle.LinkedIn.UserService.Exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super("An account with this email already exists");
    }
}
