package com.example.transactionpractice.controller;


import com.example.transactionpractice.dto.AuthenticationRequest;
import com.example.transactionpractice.dto.AuthenticationResponse;
import com.example.transactionpractice.dto.RegistrationRequest;
import com.example.transactionpractice.service.AuthenticationService;
import com.example.transactionpractice.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    private final JwtService jwtService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest
                                                                   registerRequest) {
        return ResponseEntity.status(201).body(authenticationService.registrationUser(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest
                                                                       authenticationRequest) {
        return ResponseEntity.status(201).body(authenticationService.authentication(authenticationRequest));
    }
}