package com.example.transactionpractice.repository;

import com.example.transactionpractice.entity.Payment;
import com.example.transactionpractice.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT * FROM payments WHERE payments.id = ?1", nativeQuery = true)
    Payment getById(@Param("id") long id);
}
