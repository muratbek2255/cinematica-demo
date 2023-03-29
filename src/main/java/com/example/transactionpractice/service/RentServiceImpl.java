package com.example.transactionpractice.service;

import com.example.transactionpractice.dto.RentResponse;
import com.example.transactionpractice.entity.Rent;
import com.example.transactionpractice.entity.StatusRent;
import com.example.transactionpractice.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RentServiceImpl implements RentService{

    private final RentRepository rentRepository;

    @Autowired
    public RentServiceImpl(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Override
    public RentResponse getById(long id) {

        Rent rent = rentRepository.getById(id);
        System.out.println(rent.getStatusRent());

        var rentResponse = new RentResponse();

        rentResponse.setId(rent.getId());
        rentResponse.setRow(rent.getRow());
        rentResponse.setPlace(rent.getPlace());
        rentResponse.setIsRent(rent.getIsRent());
        rentResponse.setStatusRent(rent.getStatusRent());
        rentResponse.setMovie(rent.getMovie());

        return rentResponse;
    }

    @Override
    public String addRent(long id) {

        Rent rent = rentRepository.getById(id);

        switch (rent.getStatusRent()) {
            case IN_PROGRESS -> {
                return "Rent is in progress";
            }
            case YOURRENT -> {
                return "Rent is in been not free";
            }
            default -> {
                rent.setStatusRent(StatusRent.IN_PROGRESS);
                rentRepository.save(rent);
                return "Status in Progress";
            }
        }
    }

    @Override
    public String rollbackRent(long id) {

        Rent rent = rentRepository.getById(id);

        if(rent.getStatusRent().equals(StatusRent.IN_PROGRESS)) {
            rent.setIsRent(Boolean.TRUE);
            rent.setStatusRent(StatusRent.FREE);
        }

        return "Rollback Rent";
    }
}
