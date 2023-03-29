package com.example.transactionpractice.controller;


import com.example.transactionpractice.dto.RentResponse;
import com.example.transactionpractice.service.RentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rent")
public class RentController {

    private final RentServiceImpl rentService;

    @Autowired
    public RentController(RentServiceImpl rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentResponse> getRentById(@PathVariable int id) {
        return ResponseEntity.status(200).body(rentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> addStatusRent(@PathVariable int id) {
        return ResponseEntity.status(201).body(rentService.addRent(id));
    }

    @PutMapping("/rollback/{id}")
    public ResponseEntity<String> rollbackRent(@PathVariable int id) {
        return ResponseEntity.status(201).body(rentService.rollbackRent(id));
    }
}
