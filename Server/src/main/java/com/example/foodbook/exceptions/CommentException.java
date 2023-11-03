package com.example.foodbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CommentException extends ResponseStatusException {

    public CommentException(HttpStatus status, String message) {
        super(status,message);
    }
}
