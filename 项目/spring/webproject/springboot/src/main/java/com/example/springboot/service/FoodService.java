package com.example.springboot.service;

import com.example.springboot.entity.food;
import com.example.springboot.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    @Autowired
    private FoodMapper foodMapper;

    public food findfood(Integer food_id){
        return foodMapper.findfood(food_id);
    }

    public void updateFood_rest(Integer food_id,Integer numbers){
        foodMapper.updateFood_rest(food_id,numbers);
    }

    public void backFood_rest(Integer food_id,Integer numbers){
        foodMapper.backFood_rest(food_id,numbers);
    }
}
