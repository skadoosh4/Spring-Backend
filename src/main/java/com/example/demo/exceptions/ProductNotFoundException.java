package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends CustomBaseException{
    public ProductNotFoundException() {
        super(HttpStatus.BAD_REQUEST , new SimpleResponse("Product not Found"));
    }
}
