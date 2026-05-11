package com.example.MadelaPractice.exception;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends ApplicationException {

    public EntityAlreadyExistsException(String message) {
        super(ErrorCodes.ENTITY_ALREADY_EXISTS, HttpStatus.CONFLICT, message);
    }
}
