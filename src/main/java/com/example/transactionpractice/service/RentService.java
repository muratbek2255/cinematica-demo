package com.example.transactionpractice.service;

import com.example.transactionpractice.dto.RentResponse;

public interface RentService {

    public RentResponse getById(long id);

    public String addRent(long id);

    public String rollbackRent(long id);

}
