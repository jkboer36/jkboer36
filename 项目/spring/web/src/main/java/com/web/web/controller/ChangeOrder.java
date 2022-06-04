package com.web.web.controller;

import com.web.web.common.result;
import com.web.web.entity.Orders;
import com.web.web.mapper.changeorder;
import com.web.web.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/changeorder")
public class ChangeOrder {

    @Autowired
    private changeorder changeorder;

    @Autowired
    private OrdersService ordersService;

    //新增和修改
    @PostMapping("/change")
    public result save(@RequestBody Orders orders){
        if (ordersService.save(orders)==1){
            return result.success("修改订单成功");
        }
        else {
            return result.error("修改失败，请检查输入订单信息是否正确");
        }
    }

    //删除
    @PostMapping("/delete")
    public result delete(@RequestBody Orders orders) {
        if (changeorder.delete(orders.getOrder_id()) == 1) {
            return result.success("删除订单成功");
        } else {
            return result.error("无效订单id");
        }
    }

}
