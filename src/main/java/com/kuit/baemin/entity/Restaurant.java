package com.kuit.baemin.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Restaurant {
    private Long restaurantId;
    private String name;
    private String category;
    private String description;
    private Float rating;
    private Integer deliveryFee;
    private LocalDateTime createdAt;
}

