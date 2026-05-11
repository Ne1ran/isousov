package com.example.MadelaPractice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Унифицированная обёртка ответа API")
public class ApiResponse<T> {

    @Schema(description = "true при успехе, false при ошибке")
    private boolean success = true;

    @Schema(description = "Полезная нагрузка при успехе")
    private T data;

    @Schema(description = "Заполняется при ошибке")
    private ApiErrorPayload error;

    public static <T> ApiResponse<T> ok(T data) {
        ApiResponse<T> r = new ApiResponse<>();
        r.setSuccess(true);
        r.setData(data);
        return r;
    }

    public static <T> ApiResponse<T> fail(ApiErrorPayload error) {
        ApiResponse<T> r = new ApiResponse<>();
        r.setSuccess(false);
        r.setError(error);
        return r;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiErrorPayload getError() {
        return error;
    }

    public void setError(ApiErrorPayload error) {
        this.error = error;
    }
}
