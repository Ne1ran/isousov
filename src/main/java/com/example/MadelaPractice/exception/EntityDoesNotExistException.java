package com.example.MadelaPractice.exception;

import org.springframework.http.HttpStatus;

public class EntityDoesNotExistException extends ApplicationException {

    public EntityDoesNotExistException(String message) {
        super(ErrorCodes.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND, message);
    }
}
