package com.example.jwtdemo.controller;

import com.example.jwtdemo.model.AuthRequest;
import com.example.jwtdemo.model.AuthResponse;
import com.example.jwtdemo.model.User;
import com.example.jwtdemo.service.JwtService;
import com.example.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    @Operation(summary = "Register a new user")
    public String signup(@RequestBody User user){
        userService.saveUser(user);
        return "User Registration Successful";
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user and return JWT token")
    public AuthResponse login(@RequestBody AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        String token = jwtService.generateToken(request.getUsername());
        return new AuthResponse(token);
    }
}
