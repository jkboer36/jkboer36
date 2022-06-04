package com.web.web.mapper;

import com.web.web.entity.Food;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface changefood {

    //新增或修改
    @Insert("INSERT into food(food_name, img_url, food_price, food_rest, food_type)" +
            " VALUES (#{food_name}, #{img_url}, #{food_price}, #{food_rest}, #{food_type})")
    int insert(Food food);

    int update(Food food);

    //删除
    @Delete("delete from food where food_id = #{food_id}")
    Integer delete(@Param("food_id") Integer food_id);

}
