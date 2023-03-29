package com.example.transactionpractice.repository;

import com.example.transactionpractice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
