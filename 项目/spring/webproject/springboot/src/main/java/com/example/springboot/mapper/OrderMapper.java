package com.example.springboot.mapper;

import com.example.springboot.entity.orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    Integer insertorder(Integer user_id,Integer food_id, Integer numbers);

    orders findorder(Integer order_id);

    void delByOrder_id(Integer order_id);

    void update_state(Integer order_id,Integer state);
}
