package com.diveintodev.authservice.controller;

import com.diveintodev.authservice.entity.User;
import com.diveintodev.authservice.repository.AuthRequest;
import com.diveintodev.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register-user")
    public String registerUsers(@RequestBody User user){
        return service.registerUser(user);
    }

    @PostMapping("/generate-token")
    public String generateToken(@RequestBody AuthRequest user){
        Authentication authenticationObject = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authenticationObject.isAuthenticated()) {
            String token = service.generateToken(user.getUsername());
            return token;
        }else {
            throw new RuntimeException("username is not registered");
        }
    }
}
