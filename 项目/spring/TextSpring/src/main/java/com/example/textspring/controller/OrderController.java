package com.example.textspring.controller;

import com.example.textspring.controller.common.result;
import com.example.textspring.entity.Orders;
import com.example.textspring.entity.User;
import com.example.textspring.mapper.OrderMapper;
import com.example.textspring.mapper.UserMapper;
import com.example.textspring.service.OrderService;
import com.example.textspring.service.UserService;
import com.example.textspring.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;


    @GetMapping("/searchorderAll")
    public Page searchorderAll(@RequestParam Integer pageNum, @RequestParam Integer pageSize)
    {
        return orderService.selectAll(pageNum,pageSize);
    }

    @GetMapping("/searchorderU")
    public Page searchorderU(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam Integer userid,@RequestParam String orderstate)
    {
        return orderService.selectByUserid(pageNum,pageSize,userid,orderstate);
    }

    @GetMapping("/searchorderR")
    public Page searchorderR(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam Integer workerid,@RequestParam Integer orderstate)
    {
        System.out.println(workerid);
        return orderService.selectByWorkerid(pageNum,pageSize,workerid,orderstate);
    }

    @GetMapping("/searchorderW")
    public Page searchorderW(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam Integer orderstate)
    {
        return orderService.selectByOrderState(pageNum,pageSize,orderstate);
    }


    @PostMapping("/change")
    public result save(@RequestBody Orders orders){
        if (orderService.save(orders)==1){
            return result.success("修改订单成功");
        }
        else {
            return result.error("修改失败，请检查输入订单信息是否正确");
        }
    }

    //删除
    @PostMapping("/delete")
    public result delete(@RequestBody Orders orders) {
        if (orderMapper.delete(orders.getOrder_id()) == 1) {
            return result.success("删除订单成功");
        } else {
            return result.error("无效订单id");
        }
    }
}
