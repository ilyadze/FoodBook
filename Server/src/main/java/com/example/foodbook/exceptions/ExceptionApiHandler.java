package com.example.foodbook.exceptions;
import jakarta.persistence.NonUniqueResultException;
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
    @ExceptionHandler(jakarta.persistence.NonUniqueResultException.class)
    public ResponseEntity<?> notFoundException(NonUniqueResultException exception) {
        return  new ResponseEntity<>(new AppError( HttpStatus.NOT_FOUND.value(),"Ошибка в Базе данных,Таких значений несколько"), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<?> commentDataException(CommentException exception){
        return new ResponseEntity<>(new AppError(exception.getBody().getStatus(),exception.getReason()), exception.getStatusCode());
    }
}