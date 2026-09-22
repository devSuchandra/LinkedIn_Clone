package com.CodingShuttle.LinkedIn.PostService.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timeStamp;
    private String errorMessage;
    private HttpStatus statusCode;


    public ApiError() {
        this.timeStamp = LocalDateTime.now();
    }
    public ApiError(String errorMessage, HttpStatus statusCode) {
        this();
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }
}
