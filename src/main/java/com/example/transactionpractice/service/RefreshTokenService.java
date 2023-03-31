package com.example.transactionpractice.service;

import com.example.transactionpractice.entity.token.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    public Optional<RefreshToken> findByToken(String token);

    public void verifyExpiration(RefreshToken token);

    public void deleteById(long id);

    public void increaseCount(RefreshToken refreshToken);
}
