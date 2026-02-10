package com.example.covil.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Tratando o erro de estoque
    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<Object> handleSaldoInsuficiente(SaldoInsuficienteException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("erro", "ESTOQUE_INSUFICIENTE");
        body.put("mensagem", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Tratando erros de validação (aqueles do @Valid, @NotBlank, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidacao(MethodArgumentNotValidException ex) {
        // Pegamos o primeiro erro de campo encontrado
        var fieldError = ex.getBindingResult().getFieldError();

        // Se por acaso não houver erro de campo, usamos uma mensagem genérica
        String mensagem = (fieldError != null) ? fieldError.getDefaultMessage() : "Erro de validação desconhecido";

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("erro", "DADOS_INVALIDOS");
        body.put("mensagem", mensagem);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}