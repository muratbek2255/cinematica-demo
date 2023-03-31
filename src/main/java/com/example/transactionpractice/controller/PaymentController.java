package com.example.transactionpractice.controller;


import com.example.transactionpractice.dto.PaymentCheckRequest;
import com.example.transactionpractice.dto.PaymentRequest;
import com.example.transactionpractice.entity.Payment;
import com.example.transactionpractice.entity.PaymentStatus;
import com.example.transactionpractice.repository.PaymentRepository;
import com.example.transactionpractice.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    private final PaymentRepository paymentRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public PaymentController(PaymentServiceImpl paymentService, PaymentRepository paymentRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.paymentService = paymentService;
        this.paymentRepository = paymentRepository;
        this.kafkaTemplate = kafkaTemplate;
    }


    @PostMapping("/check")
    public ResponseEntity<String> checkPayment(@RequestBody PaymentCheckRequest paymentRequest) {
        return ResponseEntity.status(201).body(paymentService.checkPayment(paymentRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> addPayment(@RequestBody PaymentRequest paymentRequest,
                                             @PathVariable int id) {
        return ResponseEntity.status(201).body(paymentService.addPayment(paymentRequest, id));
    }

    @PutMapping("/setStatus/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable int id) {
        return ResponseEntity.status(201).body(paymentService.setPayment(id));
    }

    @PutMapping("/rollbackStatus/{id}")
    public ResponseEntity<String> rollbackStatus(@PathVariable int id) {
        return ResponseEntity.status(201).body(paymentService.rollbackPayment(id));
    }

    @GetMapping("/sendMsgToKafka")
    public void sendMessageToKafka() {
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
