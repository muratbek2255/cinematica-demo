package com.example.transactionpractice.scheduler;


import lombok.extern.slf4j.Slf4j;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class TaskSchedulerForSendMessageToKafkaPaymentStatus {

    @Scheduled(cron = "*/30 * * * * *")
    @SchedulerLock(name = "PaymentStatusTaskSchedulerTask")
    @KafkaListener(
            topics = "cinematica",
            groupId = "groupId"
    )
    public void scheduledSelectFromDBTask(String data) throws InterruptedException {

        log.info("Checking status of payment!" + data);
    }
}
