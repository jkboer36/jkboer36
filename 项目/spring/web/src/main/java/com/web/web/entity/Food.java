package com.web.web.entity;

import lombok.Data;

import java.sql.Blob;


@Data
public class Food {
    private Integer food_id;
    private String food_name;
    private String img_url;
    private Double food_price;
    private Integer food_rest;
    private String food_type;

}
