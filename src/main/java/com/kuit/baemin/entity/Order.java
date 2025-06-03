package com.kuit.baemin.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Long orderId;
    private Long userId;
    private Long restaurantId;
    private Integer totalPrice;
    private String deliveryAddress;
    private String orderStatus; // ORDERED, PREPARING, DELIVERING, COMPLETED, CANCELLED
    private LocalDateTime createdAt;
}

