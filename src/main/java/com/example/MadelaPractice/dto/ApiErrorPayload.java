package com.example.MadelaPractice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Детали ошибки в едином формате")
public class ApiErrorPayload {

    @Schema(description = "Машиночитаемый код", example = "ENTITY_NOT_FOUND")
    private String code;

    @Schema(description = "Сообщение для пользователя или разработчика")
    private String message;

    @Schema(description = "Время возникновения ошибки (UTC)")
    private Instant timestamp;

    @Schema(description = "Путь HTTP-запроса")
    private String path;

    @Schema(description = "Ошибки валидации полей")
    private List<FieldViolation> fieldErrors;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<FieldViolation> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldViolation> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
