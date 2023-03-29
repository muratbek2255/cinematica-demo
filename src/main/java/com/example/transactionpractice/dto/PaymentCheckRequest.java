package com.example.transactionpractice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class PaymentCheckRequest {

    private Integer sumOfFavour;

    private String accountCheck;
}
