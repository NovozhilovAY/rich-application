package com.example.richapplication.controller;


import com.example.richapplication.exceptions.ResourceNotFoundException;
import com.example.richapplication.exceptions.ResponseErrors;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseErrors> handleException(ResourceNotFoundException e) {
        ResponseErrors errors = new ResponseErrors();
        errors.addError(e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<ResponseErrors> handleDBException(PSQLException e){
        ResponseErrors errors = new ResponseErrors();
        errors.addError(e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
