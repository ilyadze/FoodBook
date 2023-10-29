package com.example.foodbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LocalException extends ResponseStatusException {
    public  LocalException(HttpStatus status,String message){
        super(status,message);
    }
}
