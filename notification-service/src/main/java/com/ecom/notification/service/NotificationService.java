package com.ecom.notification.service;

import com.ecom.notification.order.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @KafkaListener(topics="order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent){

        System.out.println("Got message from order-placed event");

    }
}
