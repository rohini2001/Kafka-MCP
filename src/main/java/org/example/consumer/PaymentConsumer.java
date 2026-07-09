package org.example.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentConsumer {

    @KafkaListener(
            topics = "payments",
            groupId = "payment-group")
    public void consume(String message){

        System.out.println("Payment Received : " + message);

    }

}