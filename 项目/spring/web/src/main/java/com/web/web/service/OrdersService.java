package com.web.web.service;

import com.web.web.entity.Orders;
import com.web.web.mapper.changeorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    private changeorder changeorder;

    //id为空则新增，id不为空则修改
    public int save(Orders orders){
        if(orders.getOrder_id()==null){
            return changeorder.insert(orders);
        }else {
            return changeorder.update(orders);
        }
    }
}
