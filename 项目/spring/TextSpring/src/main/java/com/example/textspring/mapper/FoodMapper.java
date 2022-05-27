package com.example.textspring.mapper;

import com.example.textspring.entity.Food;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface FoodMapper {

    //查询满足条件的商品
    @Select("select * from food where food_name like concat(\"%\",#{foodname},\"%\") and food_type like concat(\"%\",#{foodtype},\"%\") limit #{pagenum},#{pagesize}")
    List<Food> selectPageWithParam(Integer pagenum, Integer pagesize, String foodname,String foodtype);

    //查询满足条件的所有用户数
    @Select("select count(*) from food where food_name like concat(\"%\",#{foodname},\"%\") and food_type like concat(\"%\",#{foodtype},\"%\")")
    int selectTotalWithParam(String foodname,String foodtype);

    @Select("select * from food where food_id =#{food_id}")
    Food selectByFoodid(Integer food_id);

    //新增或修改
    @Insert("INSERT into food(food_name, img_url, food_price, food_rest, food_type)" +
            " VALUES (#{food_name}, #{img_url}, #{food_price}, #{food_rest}, #{food_type})")
    int insert(Food food);

    int update(Food food);

    //删除
    @Delete("delete from food where food_id = #{food_id}")
    Integer delete(@Param("food_id") Integer food_id);

    Food findfood(Integer food_id);
    void updateFood_rest(Integer food_id,Integer numbers);

    void backFood_rest(Integer food_id,Integer numbers);
}
