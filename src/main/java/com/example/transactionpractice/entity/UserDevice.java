package com.example.transactionpractice.entity;


import com.example.transactionpractice.entity.audit.DateAudit;
import com.example.transactionpractice.entity.token.RefreshToken;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user_devices")
public class UserDevice extends DateAudit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(name = "device_type")
    @Enumerated(value = EnumType.STRING)
    DeviceType deviceType;

    @Column(name = "notication_token")
    String notificationToken;

    @Column(name = "device_id", nullable = false)
    String deviceId;

    @OneToOne(optional = false, mappedBy = "userDevice")
    RefreshToken refreshToken;

    @Column(name = "is_refresh_active")
    Boolean isRefreshActive;
}
