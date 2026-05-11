package com.example.MadelaPractice.exception;

import org.springframework.http.HttpStatus;

public class NoNameException extends ApplicationException {

    public NoNameException(String message) {
        super(ErrorCodes.NAME_REQUIRED, HttpStatus.BAD_REQUEST, message);
    }
}
