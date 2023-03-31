package com.example.transactionpractice.service;

import com.example.transactionpractice.dto.LikeDto;
import com.example.transactionpractice.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Retryable
    @Transactional(timeout = 10)
    @Override
    public void addLikeToMovie(LikeDto likeDto) {

        if(likeDto.getName() != null) {
            movieRepository.findByName(likeDto.getName()).ifPresentOrElse(movie -> {
                log.info("Adding {} likes to {}", likeDto.getLikes(), movie.getName());
                movie.setLikes(movie.getLikes() + likeDto.getLikes());
                movieRepository.save(movie);
            }, () -> log.info("Movie {} not found"));
        }
    }
}
