package com.kuit.baemin.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long userId;
    private String email;
    private String password;
    private String name;
    private String phone;
    private LocalDateTime createdAt;
}
