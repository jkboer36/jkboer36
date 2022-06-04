package com.web.web.service;

import com.web.web.entity.Food;
import com.web.web.mapper.changefood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    @Autowired
    private changefood changefood;

    //id为空则新增，id不为空则修改
    public int save(Food food){
        if(food.getFood_id()==null){
            return changefood.insert(food);
        }else {
            return changefood.update(food);
        }
    }
}
