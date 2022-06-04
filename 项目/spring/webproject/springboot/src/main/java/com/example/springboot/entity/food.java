package com.example.springboot.entity;

import lombok.Data;

@Data
public class food {
    private Integer food_id;
    private String food_name;
    private Double food_price;
    private String food_picture;
    private String  food_type;
    private Integer food_rest;
}
