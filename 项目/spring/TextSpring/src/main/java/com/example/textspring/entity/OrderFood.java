package com.example.textspring.entity;

import lombok.Data;

@Data
public class OrderFood {
    private Orders orders;
    private Food food;

    public OrderFood(){}

    public OrderFood(Orders orders,Food food){
        this.orders = orders;
        this.food = food;
    }
}
