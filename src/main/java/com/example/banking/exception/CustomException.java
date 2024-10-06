package com.example.banking.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonPropertyOrder({ "status", "code", "title", "detail" })
public class CustomException extends RuntimeException {

    private final HttpStatus status;
    private final String code;
    private final String title;
    private final String detail;

    public CustomException(HttpStatus status, String code, String title, String detail) {
        this.status = status;
        this.code = code;
        this.title = title;
        this.detail = detail;
    }

}
