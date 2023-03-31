package com.example.transactionpractice.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "password")
    String password;

    @Column(name = "role_user")
    @Enumerated(value = EnumType.STRING)
    UserRole userRole;

    @Column(name = "created_at")
    Timestamp createdAt;

    @Column(name = "credentials_expiry_date")
    LocalDateTime credentialsExpiryDate;

    @Column(name = "is_account_non_expired")
    Boolean isAccountExpired;

    @Column(name = "is_account_non_locked")
    Boolean isAccountLocked;

    @Column(name = "is_active", nullable = false)
    private Boolean active;

    @Column(name = "is_enabled")
    Boolean enabled;

    @Column(name = "is_twilio_verified")
    Boolean isTwilioVerified;


    public User(User user) {
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(this.userRole.name()));

    }

    public void markVerificationConfirmed() {
        setTwilioVerified(true);
    }

    private void setTwilioVerified(boolean isTwilioVerified) {
        this.isTwilioVerified = isTwilioVerified;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
