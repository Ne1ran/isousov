package com.example.MadelaPractice.exception;

import com.example.MadelaPractice.dto.ApiErrorPayload;
import com.example.MadelaPractice.dto.ApiResponse;
import com.example.MadelaPractice.dto.FieldViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<Void>> handleApplication(ApplicationException ex, HttpServletRequest request) {
        ApiErrorPayload payload = buildError(ex.getCode(), ex.getMessage(), request.getRequestURI(), null);
        return ResponseEntity.status(ex.getHttpStatus()).body(ApiResponse.fail(payload));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException ex,
                                                               HttpServletRequest request) {
        List<FieldViolation> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> new FieldViolation(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        ApiErrorPayload payload = buildError(ErrorCodes.VALIDATION_ERROR, "Ошибка валидации полей",
                request.getRequestURI(), violations);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(payload));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotReadable(HttpMessageNotReadableException ex,
                                                               HttpServletRequest request) {
        ApiErrorPayload payload = buildError(ErrorCodes.BAD_REQUEST, "Некорректное тело запроса или JSON",
                request.getRequestURI(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(payload));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntime(RuntimeException ex, HttpServletRequest request) {
        ApiErrorPayload payload = buildError(ErrorCodes.INTERNAL_ERROR, "Внутренняя ошибка сервера",
                request.getRequestURI(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponse.fail(payload));
    }

    private static ApiErrorPayload buildError(String code, String message, String path,
                                              List<FieldViolation> fieldErrors) {
        ApiErrorPayload p = new ApiErrorPayload();
        p.setCode(code);
        p.setMessage(message);
        p.setTimestamp(Instant.now());
        p.setPath(path);
        p.setFieldErrors(fieldErrors);
        return p;
    }
}
