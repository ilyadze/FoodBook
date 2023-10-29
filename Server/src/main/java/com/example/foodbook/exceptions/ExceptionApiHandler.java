package com.example.foodbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {


    @ExceptionHandler(LocalException.class)
    public ResponseEntity<?> notFoundException(LocalException exception) {
        return  new ResponseEntity<>(new AppError(exception.getBody().getStatus(),exception.getReason()), exception.getStatusCode());
    }



}
