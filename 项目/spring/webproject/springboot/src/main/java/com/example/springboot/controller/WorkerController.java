package com.example.springboot.controller;


import com.example.springboot.entity.orders;
import com.example.springboot.entity.result;
import com.example.springboot.entity.user;
import com.example.springboot.service.OrderService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/getorder")
    public result getorder(@RequestBody orders ord){
        if(ord.getOrder_id()==null||ord.getWorker_id()==null){
            return result.fail(ord,"空请求");
        }
        orders ordertest = orderService.findorder(ord.getOrder_id());
        user workertest = userService.finduser(ord.getWorker_id());
        if(ordertest==null){
            return result.fail(ord,"订单不存在");
        }
        if(workertest==null){
            return result.fail(ord,"骑手不存在");
        }

        Integer state = ordertest.getOrder_state();
        if(state==0){
            return result.fail(ord,"未支付");
        }
        else if(state==1){
            orderService.update_state(ord.getOrder_id(),2);
            return result.succ(ord);
        }
        else if(state==2){
            return result.fail(ord,"已接单");
        }
        else if(state==3){
            return result.fail(ord,"已送达");
        }
        else{
            return result.fail(ord,"未知状态错误");
        }
    }

    @PostMapping("/sureorder")
    public result sureorder(@RequestBody orders ord){
        if(ord.getOrder_id()==null){
            return result.fail(ord,"空请求");
        }
        orders ordertest = orderService.findorder(ord.getOrder_id());
        if(ordertest==null){
            return result.fail(ord,"订单不存在");
        }
        Integer state = ordertest.getOrder_state();
        if(state==0){
            return result.fail(ord,"未支付");
        }
        else if(state==1){
            return result.fail(ord,"未接单");
        }
        else {
            orderService.update_state(ordertest.getOrder_id(),3);
            return result.succ(ordertest);
        }
    }


}
