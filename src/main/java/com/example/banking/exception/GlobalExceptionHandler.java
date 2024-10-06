package com.example.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", String.valueOf(ex.getStatus()));
        response.put("code", ex.getCode());
        response.put("title", ex.getTitle());
        response.put("detail", ex.getDetail());
        HttpStatus status = ex.getStatus();

        return new ResponseEntity<>(response, status); // Change status as needed
    }
}
