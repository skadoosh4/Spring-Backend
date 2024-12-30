package com.example.demo.security.jwt;


import com.example.demo.user.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CustomUser customUser){
        //This token is different than jwt
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                customUser.getUsername(),
                customUser.getPassword()
        );
        //This will fault if the credentials are not valid
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = JwtUtil.generateToken((User)authentication.getPrincipal());
        return ResponseEntity.ok(jwtToken);
    }
}
