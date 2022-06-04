package com.example.springboot.entity;

import lombok.Data;

@Data
public class orders {
    private Integer order_id;
    private Integer user_id;
    private Integer food_id;
    private Integer numbers;
    private Integer worker_id;
    private Integer order_state;
}
