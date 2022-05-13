package com.example.payment_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
@AllArgsConstructor
public class GeneralAppException extends Exception{
    private final String message;
}
