package com.example.springboot.controller;

import com.example.springboot.entity.food;
import com.example.springboot.entity.orders;
import com.example.springboot.entity.result;
import com.example.springboot.entity.user;
import com.example.springboot.service.FoodService;
import com.example.springboot.service.OrderService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/buy")
    public result buy(@RequestBody orders ord) {
        if (ord.getUser_id() == null || ord.getFood_id() == null || ord.getNumbers() == null) {
            return result.fail(ord,"空请求");
        }
        food foodtest = foodService.findfood(ord.getFood_id());
        user usertest = userService.finduser(ord.getUser_id());
        //测试事物和用户实体是否存在，以及库存是否足够
        if(foodtest == null){
            return result.fail(ord,"食品不存在");
        }
        if(usertest == null)
        {
            return result.fail(ord,"用户不存在");
        }
        if(foodtest.getFood_rest() <ord.getNumbers())
        {
            return result.fail(ord,"库存不足");
        }
        orderService.insertorder(ord.getUser_id(),ord.getFood_id(),ord.getNumbers());
        //减少库存
        foodService.updateFood_rest(ord.getFood_id(),ord.getNumbers());
        return result.succ(ord);
    }

    @PostMapping("/return")
    //退单
    public result back(@RequestBody orders ord) {
        if (ord.getOrder_id()==null) {
            return result.fail(ord,"空请求");
        }
        orders ordertest = orderService.findorder(ord.getOrder_id());
        if(ordertest==null) {
            return result.fail(ord,"订单不存在");
        }
        else {
            orderService.deleteorder(ord.getOrder_id());
        }
        foodService.backFood_rest(ordertest.getFood_id(),ordertest.getNumbers());
        return result.succ(ordertest);
    }
}
