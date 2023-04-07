package com.example.board.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleExceptionNotFound(Exception exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(exception.getMessage(), status);
    }

    @ExceptionHandler(UserWithEmailAlreadyExist.class)
    public ResponseEntity<Object> handleExceptionUserWithEmailAlreadyExist(Exception exception) {
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(exception.getMessage(), status);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error -> {
            String fieldName = ((FieldError) error).getField();
            String defaultMessage = error.getDefaultMessage();
            errors.put(fieldName, defaultMessage);
        }));
        return errors;
    }
}
