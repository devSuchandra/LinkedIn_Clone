package com.CodingShuttle.LinkedIn.PostService.Exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
