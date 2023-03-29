package com.example.transactionpractice.dto;

import com.example.transactionpractice.entity.Movie;
import com.example.transactionpractice.entity.StatusRent;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class RentResponse {
    Long id;

    String row;

    String place;

    Boolean isRent;

    StatusRent statusRent;

    Movie movie;
}
