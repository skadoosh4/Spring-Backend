package com.example.demo;

import org.springframework.http.ResponseEntity;

public interface ICommand <E , T>{
    ResponseEntity<T> execute(E entity);
}
