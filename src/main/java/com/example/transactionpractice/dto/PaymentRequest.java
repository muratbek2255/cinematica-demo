package com.example.transactionpractice.dto;

import com.example.transactionpractice.entity.Rent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class PaymentRequest {

    private Integer price;

    private String accountCheck;

    private Rent rentRequest;
}
