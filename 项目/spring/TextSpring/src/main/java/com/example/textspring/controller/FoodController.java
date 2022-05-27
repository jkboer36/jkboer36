package com.example.textspring.controller;

import com.example.textspring.controller.common.result;
import com.example.textspring.entity.Food;
import com.example.textspring.mapper.FoodMapper;
import com.example.textspring.service.FoodService;
import com.example.textspring.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodMapper foodMapper;

    @GetMapping("/searchfood")
    public Page searchfood(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String foodname, @RequestParam String foodtype)
    {
        return foodService.searchfood(pageNum,pageSize,foodname,foodtype);
    }


    //添加或修改
    @PostMapping("/change")
    public result save(@RequestBody Food food){
        if (foodService.save(food)==1){
            return result.success("修改商品信息成功");
        }
        else {
            return result.error("无效商品id");
        }

    }

    //删除
    @PostMapping("/delete")
    public result delete(@RequestBody Food food){
        if(foodMapper.delete(food.getFood_id())==1){
            return result.success("删除商品成功");
        }
        else {
            return result.error("无效商品id");
        }
    }

}
