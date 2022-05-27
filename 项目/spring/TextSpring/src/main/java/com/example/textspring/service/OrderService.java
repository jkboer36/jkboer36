package com.example.textspring.service;

import com.example.textspring.entity.Orders;
import com.example.textspring.utils.Page;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public Page selectAll(Integer pagenum, Integer pagesize);
    public Page selectByUserid(Integer pagenum, Integer pagesize,Integer userid,String orderstate);
    Page selectByWorkerid(Integer pagenum, Integer pagesize,Integer workerid,Integer orderstate);
    public Page selectByOrderState(Integer pagenum, Integer pagesize,Integer orderstate);
    public int save(Orders orders);
    public Integer insertorder(Integer user_id,Integer food_id, Integer numbers);

    public Orders findorder(Integer order_id);

    public void deleteorder(Integer order_id);

    public void getorder(Integer order_id,Integer worker_id,Integer state);
}
