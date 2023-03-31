package com.example.transactionpractice.service;


import com.example.transactionpractice.dto.PaymentCheckRequest;
import com.example.transactionpractice.dto.PaymentRequest;
import com.example.transactionpractice.entity.Payment;
import com.example.transactionpractice.entity.PaymentStatus;
import com.example.transactionpractice.entity.Rent;
import com.example.transactionpractice.entity.StatusRent;
import com.example.transactionpractice.repository.PaymentRepository;
import com.example.transactionpractice.repository.RentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;


@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final RentRepository rentRepository;


    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, RentRepository rentRepository) {
        this.paymentRepository = paymentRepository;
        this.rentRepository = rentRepository;
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
    public String addPayment(PaymentRequest paymentRequest, long id) {

        Payment payment = paymentRepository.getById(id);

        Rent rent = rentRepository.getById(paymentRequest.getRentRequest().getId());

        if(payment.getIsChecked().equals(Boolean.TRUE)) {

            payment.setStatus(PaymentStatus.STATUS_CREATED);
            payment.setCreated_at(Timestamp.from(Instant.now()));
            payment.setFinished(Boolean.FALSE);
            payment.setRent(rent);
            payment.setSumOfFavour(paymentRequest.getPrice());
            payment.setAccountCheck(paymentRequest.getAccountCheck());

            paymentRepository.save(payment);

            return "Payment Created";

        } else {
            return "You have some with problem with AccountCheck or SumOfFavour";
        }
    }

    @Override
    public String setPayment(long id) {
        Payment payment = paymentRepository.getById(id);

        payment.setFinished(Boolean.TRUE);

        if(payment.getFinished() == Boolean.TRUE) {
            payment.setStatus(PaymentStatus.STATUS_SUCCESS);
            payment.getRent().setStatusRent(StatusRent.YOURRENT);
        }

        payment.setUpdated_at(Timestamp.from(Instant.now()));

        paymentRepository.save(payment);

        return "Your status in payment: " + payment.getStatus();
    }

    @Override
    public String rollbackPayment(long id) {

        Payment payment = paymentRepository.getById(id);

        switch (payment.getStatus()) {
            case STATUS_SUCCESS -> {
                long Milli = Math.abs(payment.getUpdated_at().getTime() - new Date().getTime());

                if(Milli < 1080000) {
                    payment.setStatus(PaymentStatus.STATUS_ROLLBACK);
                    payment.setUpdated_at(Timestamp.from(Instant.now()));
                    paymentRepository.save(payment);
                    return "Payment Rollbacked";
                }
                else return "3 days gone";

            }
            case STATUS_CREATED -> {
                payment.setStatus(PaymentStatus.STATUS_ROLLBACK);
                payment.setUpdated_at(Timestamp.from(Instant.now()));
                paymentRepository.save(payment);
                return "Payment Rollbacked";
            }
            default -> {
                return "You don't have payment or your status fail";
            }
        }
    }
}
