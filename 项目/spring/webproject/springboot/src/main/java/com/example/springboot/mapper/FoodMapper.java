package com.example.springboot.mapper;

import com.example.springboot.entity.food;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodMapper {
    food findfood(Integer food_id);
    void updateFood_rest(Integer food_id,Integer numbers);

    void backFood_rest(Integer food_id,Integer numbers);
}
