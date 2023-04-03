package com.example.transactionpractice.service;

import com.example.transactionpractice.entity.User;
import com.example.transactionpractice.entity.UserRole;
import com.example.transactionpractice.repository.RefreshTokenRepository;
import com.example.transactionpractice.repository.UserRepository;
import com.example.transactionpractice.utils.JwtUtils;
import com.example.transactionpractice.dto.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.Instant;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static reactor.core.publisher.Mono.when;


@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private RefreshTokenServiceImpl refreshTokenService;

    @Mock
    private UserDeviceServiceImpl userDeviceService;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new AuthenticationService(userRepository, passwordEncoder, jwtUtils, authenticationManager,
                refreshTokenService, userDeviceService, refreshTokenRepository);
    }

    @Test
    public void testRegistrationUser() {


        RegistrationRequest userRequest = new RegistrationRequest();
        userRequest.setPhoneNumber("+996707058355");
        userRequest.setPassword("jajhssw");


        User user = new User();

        user.setId(1L);
        user.setPhoneNumber("+996707058355");
        user.setPassword("jajhssw");
        user.setUserRole(UserRole.USER);
        user.setCreatedAt(Timestamp.from(Instant.now()));
        user.setIsAccountExpired(Boolean.TRUE);
        user.setActive(Boolean.TRUE);
        user.setIsAccountLocked(Boolean.TRUE);
        user.setEnabled(Boolean.TRUE);
        user.setIsTwilioVerified(Boolean.FALSE);

        verify(userRepository).save(user);

        var token = Mockito.when(jwtUtils.generateToken(user)).thenReturn("encodedToken");
        var refreshToken = Mockito.when(userService.generateToken(user)).thenReturn("encodedRefreshToken");
        AuthenticationResponse authenticationResponse1 = new AuthenticationResponse(token.toString(), refreshToken.toString());

        AuthenticationResponse authenticationResponse = userService.registrationUser(userRequest);

        Assertions.assertEquals(authenticationResponse1, authenticationResponse);
        Assertions.assertEquals(userRequest.getPhoneNumber(), user.getPhoneNumber());
        Assertions.assertEquals(userRequest.getPassword(), user.getPassword());
    }


}