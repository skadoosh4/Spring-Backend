package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping("/createnewuser")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser customUser){
        Optional<CustomUser> optionalUser = userRepository.findById(customUser.getUsername());
        if(optionalUser.isEmpty()){
            CustomUser newCustomUser = new CustomUser();
            newCustomUser.setUsername(customUser.getUsername());
            newCustomUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
            userRepository.save(newCustomUser);
            return ResponseEntity.ok("New user created");
        }
        return ResponseEntity.ok("User already exists");
    }
}
