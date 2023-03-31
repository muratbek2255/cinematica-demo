package com.example.transactionpractice.repository;

import com.example.transactionpractice.entity.User;
import com.example.transactionpractice.entity.token.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ResetPasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);

    @Query(value = "SELECT t FROM password_reset_token t WHERE t.active = true and t.user = :user", nativeQuery = true)
    List<PasswordResetToken> findActiveTokensForUser(User user);
}
