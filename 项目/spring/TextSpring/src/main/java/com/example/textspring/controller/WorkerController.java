package com.example.textspring.controller;


import com.example.textspring.controller.common.result;
import com.example.textspring.entity.Orders;
import com.example.textspring.entity.User;
import com.example.textspring.service.OrderService;
import com.example.textspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/getorder")
    public result getorder(@RequestBody Orders ord){
        if(ord.getOrder_id()==null||ord.getWorker_id()==null){
            return result.fail(ord,"空请求");
        }
        Orders ordertest = orderService.findorder(ord.getOrder_id());
        User workertest = userService.finduser(ord.getWorker_id());
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
            orderService.getorder(ord.getOrder_id(),ord.getWorker_id(),2);
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
    public result sureorder(@RequestBody Orders ord){
        if(ord.getOrder_id()==null){
            return result.fail(ord,"空请求");
        }
        Orders ordertest = orderService.findorder(ord.getOrder_id());
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
            orderService.getorder(ordertest.getOrder_id(),ordertest.getWorker_id(),3);
            return result.succ(ordertest);
        }
    }


}
