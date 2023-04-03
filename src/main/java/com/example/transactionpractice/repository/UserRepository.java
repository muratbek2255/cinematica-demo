package com.example.transactionpractice.repository;

import com.example.transactionpractice.entity.Rent;
import com.example.transactionpractice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT new User(r.id, r.phoneNumber, r.password, r.userRole, r.createdAt, " +
            "r.credentialsExpiryDate, r.isAccountExpired, r.isAccountLocked, r.active, r.enabled, r.isTwilioVerified) FROM User r WHERE r.id = ?1", nativeQuery = true)
    User getById(@Param("id") long id);

}
