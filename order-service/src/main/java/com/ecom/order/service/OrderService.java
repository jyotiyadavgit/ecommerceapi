package com.ecom.order.service;

import com.ecom.order.client.InventoryClient;
import com.ecom.order.dto.OrderRequest;
import com.ecom.order.event.OrderPlacedEvent;
import com.ecom.order.model.Order;
import com.ecom.order.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient, KafkaTemplate kafkaTemplate){
        this.orderRepository=orderRepository;
        this.inventoryClient=inventoryClient;
        this.kafkaTemplate=kafkaTemplate;
    }



    public void placeOrder(OrderRequest orderRequest){

        boolean isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity());

        if(isProductInStock){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());

            orderRepository.save(order);

            //send message to kafka topic

            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(), "test@gmail.com");

            System.out.println("Start - sending orderPlacedEvent{} to kafka topic order-placed");
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            System.out.println("End - sending orderPlacedEvent{} to kafka topic");



        }
        else{
            throw new RuntimeException("Product with skuCode " + orderRequest.skuCode() +" is not in Stock");
        }



    }

}
