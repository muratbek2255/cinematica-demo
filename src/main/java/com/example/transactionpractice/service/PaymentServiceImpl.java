package com.example.transactionpractice.service;


import com.example.transactionpractice.dto.PaymentCheckRequest;
import com.example.transactionpractice.dto.PaymentRequest;
import com.example.transactionpractice.dto.RentResponse;
import com.example.transactionpractice.entity.Payment;
import com.example.transactionpractice.entity.PaymentStatus;
import com.example.transactionpractice.entity.Rent;
import com.example.transactionpractice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;


@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final RentServiceImpl rentService;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, RentServiceImpl rentService) {
        this.paymentRepository = paymentRepository;
        this.rentService = rentService;
    }


    @Override
    public String checkPayment(PaymentCheckRequest paymentCheckRequest) {

        if(paymentCheckRequest.getSumOfFavour() > 500 && paymentCheckRequest.getSumOfFavour() < 25000 &&
                paymentCheckRequest.getAccountCheck() != null) {

            Payment payment = new Payment();

            payment.setIsChecked(Boolean.TRUE);
            payment.setStatus(PaymentStatus.STATUS_PROCESS);

            paymentRepository.save(payment);

            return "Order is checked!";

        } else  {

            return "Error, you have problem with sumOfFavour or AccountCheck!";

        }
    }

    @Override
    public String addStatus(PaymentRequest paymentRequest, long id) {

        Payment payment = paymentRepository.getById(id);

        if(payment.getIsChecked().equals(Boolean.TRUE)) {

            payment.setStatus(PaymentStatus.STATUS_CREATED);
            payment.setCreated_at(Timestamp.from(Instant.now()));
            payment.setFinished(Boolean.FALSE);
            payment.setSumOfFavour(paymentRequest.getPrice());
            payment.setAccountCheck(paymentRequest.getAccountCheck());

            paymentRepository.save(payment);

            return "Payment Created";
        }else {
            return "You have some with problem with AccountCheck or SumOfFavour";
        }
    }

    @Override
    public String setStatus(long id) {
        return null;
    }

    @Override
    public String rollbackPayment(long id) {
        return null;
    }

    @Override
    public String getByStatus(String status) {
        return null;
    }
}
