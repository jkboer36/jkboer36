package com.web.web.mapper;

import com.web.web.entity.Orders;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface changeorder {

    //新增或修改
    @Insert("INSERT into orders(user_id, food_id, numbers, worker_id, order_state) VALUES " +
            "(#{user_id}, #{food_id}, #{numbers}, #{worker_id}, #{order_state})")
    int insert(Orders orders);

    int update(Orders orders);

    //删除
    @Delete("delete from orders where order_id = #{order_id}")
    Integer delete(@Param("order_id") Integer order_id);
}
