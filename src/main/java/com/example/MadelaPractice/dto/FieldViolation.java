package com.example.MadelaPractice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ошибка по одному полю запроса")
public class FieldViolation {

    private String field;
    private String message;

    public FieldViolation() {
    }

    public FieldViolation(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
