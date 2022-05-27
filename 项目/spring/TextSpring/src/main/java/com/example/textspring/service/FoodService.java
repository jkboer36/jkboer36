package com.example.textspring.service;

import com.example.textspring.entity.Food;
import com.example.textspring.utils.Page;
import org.springframework.stereotype.Service;

@Service
public interface FoodService {
    public Page searchfood(Integer pagenum, Integer pagesize, String foodname, String foodtype);
    public int save(Food food);
    public Food findfood(Integer food_id);

    void updateFood_rest(Integer food_id,Integer numbers);

    void backFood_rest(Integer food_id,Integer numbers);
}
