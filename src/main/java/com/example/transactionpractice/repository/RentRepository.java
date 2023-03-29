package com.example.transactionpractice.repository;

import com.example.transactionpractice.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RentRepository extends JpaRepository<Rent, Long> {

    @Query(value = "SELECT new Rent(r.id, r.row, r.place, r.isRent, r.statusRent, r.movie) FROM Rent r WHERE r.id = ?1")
    Rent getById(@Param("id") long id);


}
