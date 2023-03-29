package com.example.transactionpractice.service;

import com.example.transactionpractice.RentResponse;

public interface RentService {

    public RentResponse getById(int id);

    public String addRent(int id);

    public String rollbackRent(int id);
}
