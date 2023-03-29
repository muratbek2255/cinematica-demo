package com.example.transactionpractice.repository;

import com.example.transactionpractice.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
