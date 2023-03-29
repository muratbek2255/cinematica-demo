package com.example.transactionpractice.repository;

import com.example.transactionpractice.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RentRepository extends JpaRepository<Rent, Long> {

    @Query(value = "SELECT * FROM rents WHERE rents.id = ?1", nativeQuery = true)
    Rent getById(@Param("id") long id);


}
