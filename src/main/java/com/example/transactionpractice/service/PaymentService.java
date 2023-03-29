package com.example.transactionpractice.service;

import com.example.transactionpractice.dto.PaymentCheckRequest;
import com.example.transactionpractice.dto.PaymentRequest;

public interface PaymentService {

    public String checkPayment(PaymentCheckRequest paymentCheckRequest);

    public String addStatus(PaymentRequest paymentRequest, long id);

    public String setStatus(long id);

    public String rollbackPayment(long id);

    public String getByStatus(String status);
}
