package com.example.textspring.mapper;

import com.example.textspring.entity.Orders;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    //查询所有订单
    @Select("select * from orders limit #{pagenum},#{pagesize}")
    List<Orders> selectAll(Integer pagenum, Integer pagesize);

    @Select("select count(*) from orders")
    int  selectTotal();

    //查询用户订单
    @Select("select * from orders where user_id=#{userid} and order_state like concat(\"%\",#{orderstate},\"%\") limit #{pagenum},#{pagesize}")
    List<Orders> selectByUserid(Integer pagenum, Integer pagesize,Integer userid,String orderstate);

    @Select("select count(*) from orders where user_id=#{userid} and order_state like concat(\"%\",#{orderstate},\"%\")")
    int  selectTotalUser(Integer userid, String  orderstate);

    @Select("select * from orders where worker_id=#{workerid} and order_state like concat(\"%\",#{orderstate},\"%\") limit #{pagenum},#{pagesize}")
    List<Orders> selectByWorkerid(Integer pagenum, Integer pagesize,Integer workerid,Integer orderstate);

    @Select("select count(*) from orders where worker_id=#{workerid} and order_state like concat(\"%\",#{orderstate},\"%\")")
    int  selectTotalWorker(Integer workerid, Integer orderstate);

    //查询可接取订单
    @Select("select * from orders where order_state=#{orderstate} limit #{pagenum},#{pagesize}")
    List<Orders> selectByOrderState(Integer pagenum, Integer pagesize,Integer orderstate);

    @Select("select count(*) from orders where order_state=#{orderstate}")
    int  selectTotalState(Integer orderstate);



    //新增或修改
    @Insert("INSERT into orders(user_id, food_id, numbers, worker_id, order_state) VALUES " +
            "(#{user_id}, #{food_id}, #{numbers}, #{worker_id}, #{order_state})")
    int insert(Orders orders);

    int update(Orders orders);

    //删除
    @Delete("delete from orders where order_id = #{order_id}")
    Integer delete(@Param("order_id") Integer order_id);

    Integer insertorder(Integer user_id,Integer food_id, Integer numbers);

    Orders findorder(Integer order_id);

    void delByOrder_id(Integer order_id);

    void getorder(Integer order_id,Integer worker_id,Integer state);

}
