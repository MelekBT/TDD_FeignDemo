package com.example.feign.demo.exceptions;

public class DomainNotFoundException extends RuntimeException {
    public DomainNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
