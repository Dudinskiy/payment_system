package com.example.payment_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException exception) {
        String response = exception.getMessage();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GeneralAppException.class)
    public ResponseEntity<String> handleGeneralAppException(GeneralAppException exception) {
        String response = exception.getMessage();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
