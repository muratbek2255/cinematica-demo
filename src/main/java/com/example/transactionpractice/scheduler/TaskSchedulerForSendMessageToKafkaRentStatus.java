package com.example.transactionpractice.scheduler;

import com.example.transactionpractice.entity.Rent;
import com.example.transactionpractice.entity.StatusRent;
import com.example.transactionpractice.repository.RentRepository;

import lombok.extern.slf4j.Slf4j;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class TaskSchedulerForSendMessageToKafkaRentStatus {

    private final RentRepository rentRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public TaskSchedulerForSendMessageToKafkaRentStatus(RentRepository rentRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.rentRepository = rentRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(cron = "*/45 * * * * *")
    @SchedulerLock(name = "RentStatusSchedulerTask")
    public void scheduledSelectFromDBTask() throws InterruptedException {

        log.info("Checking status of rent!");

        List<Rent> rents = rentRepository.findAll();

        rents.forEach(
                rent -> {
                    if(rent.getStatusRent().equals(StatusRent.IN_PROGRESS)) {
                        kafkaTemplate.send("cinematica", "Rent id: " + rent.getId() +
                                " Status: " + rent.getStatusRent());
                    }
                }
        );
    }
}
