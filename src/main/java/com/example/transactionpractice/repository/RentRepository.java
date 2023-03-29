package com.example.transactionpractice.repository;

import com.example.transactionpractice.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RentRepository extends JpaRepository<Rent, Long> {
}
