package com.example.transactionpractice.repository;

import com.example.transactionpractice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
