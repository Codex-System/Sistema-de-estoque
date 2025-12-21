package com.codexsystem.SistemaDeEstoque.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RecursoNaoEncontrado.class)
    public ResponseEntity<Object> handleRecursoNaoEncontrado(RecursoNaoEncontrado e){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Timestamp", LocalDate.now());
        body.put("Status", HttpStatus.NOT_FOUND.value());
        body.put("Error", "Recurso não encontrado");
        body.put("Message", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenException(RecursoNaoEncontrado e) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("Timestamp", LocalDate.now());
        body.put("Status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("Error", "Erro interno do servidor");
        body.put("Message", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
