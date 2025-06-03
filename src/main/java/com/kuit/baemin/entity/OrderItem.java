package com.kuit.baemin.entity;

import lombok.Data;

@Data
public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long menuId;
    private Integer quantity;
    private Integer price;
}

