package com.example.textspring.service.iml;

import com.example.textspring.entity.OrderFood;
import com.example.textspring.entity.Orders;
import com.example.textspring.mapper.FoodMapper;
import com.example.textspring.mapper.OrderMapper;
import com.example.textspring.service.OrderService;
import com.example.textspring.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceIml implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private FoodMapper foodMapper;

    @Override
    public Page selectAll(Integer pagenum, Integer pagesize) {
        int startId = (pagenum - 1) * pagesize, size = pagesize;
        List<Orders> data=orderMapper.selectAll(startId,size);
        int total=orderMapper.selectTotal();
        Page page = new Page();
        page.setTotal(total);
        page.setData(data);
        return page;
    }

    @Override
    public Page selectByUserid(Integer pagenum, Integer pagesize, Integer userid, String orderstate) {
        int startId = (pagenum - 1) * pagesize, size = pagesize;
        List<OrderFood> data = new ArrayList<>();
        orderMapper.selectByUserid(startId,size,userid,orderstate).forEach(orders -> {
            data.add(new OrderFood(orders, foodMapper.selectByFoodid(orders.getFood_id())));
        });
        int total=orderMapper.selectTotalUser(userid,orderstate);
        Page page = new Page();
        page.setTotal(total);
        page.setData(data);
        return page;
    }

    @Override
    public Page selectByWorkerid(Integer pagenum, Integer pagesize, Integer workerid, Integer orderstate) {
        int startId = (pagenum - 1) * pagesize, size = pagesize;
        List<Orders> data=orderMapper.selectByWorkerid(startId,size,workerid,orderstate);
        int total=orderMapper.selectTotalWorker(workerid,orderstate);
        Page page = new Page();
        page.setTotal(total);
        page.setData(data);
        return page;
    }

    @Override
    public Page selectByOrderState(Integer pagenum, Integer pagesize, Integer orderstate) {
        int startId = (pagenum - 1) * pagesize, size = pagesize;
        List<Orders> data=orderMapper.selectByOrderState(startId,size,orderstate);
        int total=orderMapper.selectTotalState(orderstate);
        Page page = new Page();
        page.setTotal(total);
        page.setData(data);
        return page;
    }

    @Override
    //id为空则新增，id不为空则修改
    public int save(Orders orders){
        if(orders.getOrder_id()==null & orders.getUser_id()!=null & orders.getFood_id()!=null & orders.getOrder_state()!=null & orders.getNumbers()!=null){
            return orderMapper.insert(orders);
        }else {
            return orderMapper.update(orders);
        }
    }

    @Override
    public Integer insertorder(Integer user_id,Integer food_id, Integer numbers){
        orderMapper.insertorder(user_id, food_id, numbers);
        return 1;
    }

    @Override
    public Orders findorder(Integer order_id){
        return orderMapper.findorder(order_id);
    }


    @Override
    public void deleteorder(Integer order_id){
        orderMapper.delByOrder_id(order_id);
    }


    @Override
    public void getorder(Integer order_id,Integer worker_id,Integer state){
        orderMapper.getorder(order_id,worker_id,state);
    }
}
