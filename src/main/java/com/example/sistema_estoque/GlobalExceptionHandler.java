package com.example.sistema_estoque;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> tratarRunTimeException(RuntimeException ex) {

        ErrorResponse erro = new ErrorResponse(
                400,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.badRequest().body(erro);
    }
}
