package com.example.transactionpractice.dto;

import com.example.transactionpractice.entity.Movie;
import com.example.transactionpractice.entity.StatusRent;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RentResponse {
    Long id;

    String row;

    String place;

    Boolean isRent;

    StatusRent statusRent;

    Movie movie;

    public RentResponse(Long id, String row, String place, Boolean isRent, StatusRent statusRent, Movie movie) {
    }
}
