package com.example.springboot.service;

import com.example.springboot.entity.food;
import com.example.springboot.entity.orders;
import com.example.springboot.mapper.FoodMapper;
import com.example.springboot.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public Integer insertorder(Integer user_id,Integer food_id, Integer numbers){
        orderMapper.insertorder(user_id, food_id, numbers);
        return 1;
    }

    public orders findorder(Integer order_id){
        return orderMapper.findorder(order_id);
    }

    public void deleteorder(Integer order_id){
        orderMapper.delByOrder_id(order_id);
    }

    public void update_state(Integer order_id,Integer state){
        orderMapper.update_state(order_id,state);
    }

}
