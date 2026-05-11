package com.example.MadelaPractice.exception;

import org.springframework.http.HttpStatus;

public class NameDoesNotExistException extends ApplicationException {

    public NameDoesNotExistException(String message) {
        super(ErrorCodes.NAME_NOT_FOUND, HttpStatus.NOT_FOUND, message);
    }
}
