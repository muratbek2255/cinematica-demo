package com.example.transactionpractice.service;


import com.example.transactionpractice.dto.AuthenticationRequest;
import com.example.transactionpractice.dto.RegistrationRequest;
import com.example.transactionpractice.dto.AuthenticationResponse;
import com.example.transactionpractice.entity.User;
import com.example.transactionpractice.entity.UserRole;
import com.example.transactionpractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse registrationUser(RegistrationRequest registerRequest) {

        User user = new User();


        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setUserRole(UserRole.USER);

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(
                jwtToken
        );


        return authenticationResponse;
    }

    public AuthenticationResponse authentication(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));

        var user = userRepository.findByPhoneNumber(authenticationRequest.getUsername());

        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtToken);

        return authenticationResponse;
    }
}
