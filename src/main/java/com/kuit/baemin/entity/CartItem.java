package com.kuit.baemin.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CartItem {
    private Long cartItemId;
    private Long userId;
    private Long restaurantId;
    private Long menuId;
    private Integer quantity;
    private LocalDateTime createdAt;
}

