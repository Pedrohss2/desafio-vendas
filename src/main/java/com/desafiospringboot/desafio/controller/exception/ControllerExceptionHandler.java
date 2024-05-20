package com.desafiospringboot.desafio.controller.exception;

import com.desafiospringboot.desafio.dto.erros.CustomError;
import com.desafiospringboot.desafio.dto.erros.ValidationError;
import com.desafiospringboot.desafio.service.exception.BancoDeDadosException;
import com.desafiospringboot.desafio.service.exception.RecursoNaoEncontrado;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Duration;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Duration duration = Duration.ofHours(3L);
    @ExceptionHandler(RecursoNaoEncontrado.class)
    public ResponseEntity<CustomError> resourceNotFound(RecursoNaoEncontrado e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now().minus(duration), status.value(), e.getMessage());

        return ResponseEntity.status(status).body(customError);
    }


    @ExceptionHandler(BancoDeDadosException.class)
    public ResponseEntity<CustomError> bancoDeDadosException(BancoDeDadosException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now().minus(duration), status.value(), e.getMessage());

        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError validationError = new ValidationError(Instant.now().minus(duration), status.value(), "Dados invalidos");

        for(FieldError error : e.getBindingResult().getFieldErrors()) {
            validationError.addError(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(validationError);
    }

}
