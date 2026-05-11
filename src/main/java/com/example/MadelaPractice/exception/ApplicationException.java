package com.example.MadelaPractice.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

    private final String code;
    private final HttpStatus httpStatus;

    public ApplicationException(String code, HttpStatus httpStatus, String message) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public ApplicationException(String code, HttpStatus httpStatus, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
