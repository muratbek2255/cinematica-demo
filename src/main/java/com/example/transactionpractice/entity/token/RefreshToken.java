package com.example.transactionpractice.entity.token;


import com.example.transactionpractice.entity.UserDevice;
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
@Table(name = "refresh_token")
public class RefreshToken extends DateAudit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "token", nullable = false, unique = true)
    String token;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_device_id", unique = true)
    UserDevice userDevice;

    @Column(name = "refresh_count")
    Long refreshCount;

    @Column(name = "expiry_dt", nullable = false)
    Instant expiryDate;

    public void incrementRefreshCount() {
        refreshCount = refreshCount + 1;
    }
}
