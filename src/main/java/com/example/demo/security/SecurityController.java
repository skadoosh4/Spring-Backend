package com.example.demo.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/special")
    public String special(){
        return "SPECIAL";
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/basic")
    public String basic(){
        return "BASIC";
    }
}
