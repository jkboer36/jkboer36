package com.example.textspring.service.iml;

import com.example.textspring.entity.Food;
import com.example.textspring.mapper.FoodMapper;
import com.example.textspring.service.FoodService;
import com.example.textspring.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FoodServiceIml implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Override
    public Page searchfood(Integer pagenum, Integer pagesize, String foodname, String foodtype) {
        int startId = (pagenum - 1) * pagesize, size = pagesize;
        List<Food> data=foodMapper.selectPageWithParam(startId,size,foodname,foodtype);
        int total = foodMapper.selectTotalWithParam(foodname,foodtype);
        Page page = new Page();
        page.setTotal(total);
        page.setData(data);
        return page;
    }

    //id为空则新增，id不为空则修改
    @Override
    public int save(Food food){
        if(food.getFood_id()==null & food.getFood_name()!=null & food.getFood_price()!=null & food.getFood_rest()!=null & food.getFood_type()!=null){
            return foodMapper.insert(food);
        }else {
            return foodMapper.update(food);
        }
    }

    @Override
    public Food findfood(Integer food_id){
        return foodMapper.findfood(food_id);
    }


    @Override
    public void updateFood_rest(Integer food_id,Integer numbers){
        foodMapper.updateFood_rest(food_id,numbers);
    }


    @Override
    public void backFood_rest(Integer food_id,Integer numbers){
        foodMapper.backFood_rest(food_id,numbers);
    }

}
