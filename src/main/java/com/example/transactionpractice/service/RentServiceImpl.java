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

        return new RentResponse(
                rent.getId(), rent.getRow(), rent.getPlace(),
                rent.getIsRent(), rent.getStatusRent(), rent.getMovie());
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
                return "Status is FREE";
            }
        }
    }

    @Override
    public String rollbackRent(long id) {

        Rent rent = rentRepository.getById(id);

        if(rent.getStatusRent().equals(StatusRent.IN_PROGRESS)) {
            rent.setStatusRent(StatusRent.FREE);
        }

        return "Rollback Rent";
    }
}
