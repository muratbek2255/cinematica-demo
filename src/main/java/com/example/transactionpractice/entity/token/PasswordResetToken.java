package com.example.transactionpractice.entity.token;


import com.example.transactionpractice.entity.User;
import com.example.transactionpractice.entity.audit.DateAudit;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "password_reset_token")
public class PasswordResetToken extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "token_name", nullable = false, unique = true)
    String token;

    @Column(name = "expiry_dt", nullable = false)
    Instant expiryDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    User user;

    @Column(name = "is_active", nullable = false)
    Boolean active;

    @Column(name = "is_claimed", nullable = false)
    Boolean claimed;

}
