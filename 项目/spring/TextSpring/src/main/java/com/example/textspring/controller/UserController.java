package com.example.textspring.controller;

import com.example.textspring.controller.common.UserResult;
import com.example.textspring.controller.common.UserpasswordDto;
import com.example.textspring.controller.common.result;
import com.example.textspring.entity.Food;
import com.example.textspring.entity.Orders;
import com.example.textspring.entity.User;
import com.example.textspring.mapper.UserMapper;
import com.example.textspring.service.FoodService;
import com.example.textspring.service.OrderService;
import com.example.textspring.service.UserService;
import com.example.textspring.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FoodService foodService;

    @Autowired
    private OrderService orderService;



    @PostMapping("/login")
    public UserResult login(@RequestBody User user){
        if (user.getUser_account()== null || user.getUser_password() == null){
            return UserResult.fail(user);
        }
        return userService.login(user);

    }


    @PostMapping("/register")
    public UserResult register(@RequestBody User user){
        if (user.getUser_account()== null || user.getUser_password() == null || user.getUser_name() == null){
            return UserResult.fail(user);
        }
        return userService.register(user);

    }


    @PostMapping("/changepassword")
    public UserResult changepassword(@RequestBody UserpasswordDto userpasswordDto){
        if (userpasswordDto.getUser_account()== null || userpasswordDto.getUser_password() == null || userpasswordDto.getUser_newpassword() == null){
            return UserResult.fail(userpasswordDto);
        }
        return userService.changepassword(userpasswordDto);

    }



    @GetMapping("/searchuser")
    public Page searchuser(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String username)
    {
        return userService.searchuser(pageNum,pageSize,username);
    }

    //新增和修改
    @PostMapping("/change")
    public result save(@RequestBody User user){
        if (userService.save(user)==1){
            return result.success("修改用户信息成功");
        }
        else {
            return result.error("无效用户id");
        }
    }


    //删除
    @PostMapping("/delete")
    public result delete(@RequestBody User user) {
        System.out.println(user.getUser_id());
        if (userMapper.delete(user.getUser_id()) == 1) {
            return result.success("删除用户成功");
        } else {
            return result.error("无效用户id");
        }
    }

    @PostMapping("/buy")
    public result buy(@RequestBody Orders ord) {
        if (ord.getUser_id() == null || ord.getFood_id() == null || ord.getNumbers() == null) {
            return result.fail(ord,"空请求");
        }
        Food foodtest = foodService.findfood(ord.getFood_id());
        User usertest = userService.finduser(ord.getUser_id());
        //测试事物和用户实体是否存在，以及库存是否足够
        if(foodtest == null){
            return result.fail(ord,"食品不存在");
        }
        if(usertest == null)
        {
            return result.fail(ord,"用户不存在");
        }
        if(foodtest.getFood_rest() <ord.getNumbers())
        {
            return result.fail(ord,"库存不足");
        }
        orderService.insertorder(ord.getUser_id(),ord.getFood_id(),ord.getNumbers());
        //减少库存
        foodService.updateFood_rest(ord.getFood_id(),ord.getNumbers());
        return result.succ(ord);
    }

    @PostMapping("/pay")
    public result pay(@RequestBody Orders ord){
        if(ord.getOrder_id()==null){
            return result.fail(ord,"空请求");
        }
        Orders ordertest = orderService.findorder(ord.getOrder_id());
        if(ordertest==null){
            return result.fail(ord,"订单不存在");
        }
        Integer state = ordertest.getOrder_state();
        if(state==0){
            orderService.getorder(ordertest.getOrder_id(),ordertest.getWorker_id(),1);
            return result.succ(ordertest);
        }
        else{
            return result.fail(ord,"已支付接单");
        }
    }

    @PostMapping("/returnorder")
    //退单
    public result back(@RequestBody Orders ord) {
        if (ord.getOrder_id()==null) {
            return result.fail(ord,"空请求");
        }
        Orders ordertest = orderService.findorder(ord.getOrder_id());
        if(ordertest==null) {
            return result.fail(ord,"订单不存在");
        }
        else {
            orderService.deleteorder(ord.getOrder_id());
        }
        foodService.backFood_rest(ordertest.getFood_id(),ordertest.getNumbers());
        return result.succ(ordertest);
    }
}
