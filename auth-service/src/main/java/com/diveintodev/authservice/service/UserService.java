package com.diveintodev.authservice.service;

import com.diveintodev.authservice.entity.User;
import com.diveintodev.authservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;
    public String registerUser(User user) {

        if(user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            return "user : " + user.getUsername() + " registered with id : " + user.getId();
        }
        return "error registering user";
    }

    public String generateToken(String username) {

        String token = jwtService.generateToken(username);

        return token;
    }
}
