package com.example.demo;

import org.springframework.http.ResponseEntity;

public interface IQuery <I , O>{
    ResponseEntity<O> execute(I input);
}
