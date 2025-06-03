package com.kuit.baemin.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Review {
    private Long reviewId;
    private Long userId;
    private Long restaurantId;
    private Integer rating; // 1~5
    private String content;
    private LocalDateTime createdAt;
}
