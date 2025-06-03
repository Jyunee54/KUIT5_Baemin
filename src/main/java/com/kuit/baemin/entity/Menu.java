package com.kuit.baemin.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Menu {
    private Long menuId;
    private Long restaurantId;
    private String name;
    private Integer price;
    private String imageUrl;
    private LocalDateTime createdAt;
}

