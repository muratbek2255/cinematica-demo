package com.example.transactionpractice.controller;


import com.example.transactionpractice.dto.LikeDto;
import com.example.transactionpractice.service.MovieServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MovieController {

    private final MovieServiceImpl service;

    @Autowired
    public MovieController(MovieServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/addlikes")
    public ResponseEntity<String> updateSpeaker(@RequestBody LikeDto likes) {
        try {
            service.addLikeToMovie(likes);
            return new ResponseEntity<>("Likes successfully added.", HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            log.warn("Exception in controller:", ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
