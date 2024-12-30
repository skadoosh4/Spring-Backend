package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class ExternalCatFactsDownException extends CustomBaseException{
    public ExternalCatFactsDownException(){
        super(HttpStatus.SERVICE_UNAVAILABLE , new SimpleResponse("The external API is down. Not our fault"));
    }
}
