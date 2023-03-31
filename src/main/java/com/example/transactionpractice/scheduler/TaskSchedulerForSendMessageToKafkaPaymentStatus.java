package com.example.transactionpractice.scheduler;


import com.example.transactionpractice.entity.Payment;
import com.example.transactionpractice.entity.PaymentStatus;
import com.example.transactionpractice.repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class TaskSchedulerForSendMessageToKafkaPaymentStatus {

    private final PaymentRepository paymentRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public TaskSchedulerForSendMessageToKafkaPaymentStatus(PaymentRepository paymentRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.paymentRepository = paymentRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(cron = "*/30 * * * * *")
    @SchedulerLock(name = "PaymentStatusTaskSchedulerTask")
    public void scheduledSelectFromDBTask() throws InterruptedException {

        log.info("Checking status of payment!");

        List<Payment> payments = paymentRepository.findAll();

        payments.forEach(
                payment -> {
                    if(payment.getStatus().equals(PaymentStatus.STATUS_PROCESS)) {
                        kafkaTemplate.send("cinematica", "Payment id: " + payment.getId() +
                                " and his payment status: " + payment.getStatus());
                    }
                }
        );
    }
}
