package com.example.transactionpractice.service;

import com.example.transactionpractice.dto.PaymentCheckRequest;
import com.example.transactionpractice.dto.PaymentRequest;

public interface PaymentService {

    public String checkPayment(PaymentCheckRequest paymentCheckRequest);

    public String addPayment(PaymentRequest paymentRequest, long id);

    public String setPayment(long id);

    public String rollbackPayment(long id);

}
