package com.hk.quickgraphql.controller;

import com.hk.quickgraphql.domain.User;
import com.hk.quickgraphql.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;


    /**
     * 查询用户
     */
    @RequestMapping("/query")
    public String queryUser(String id) {
        User users = userMapper.selectById("111111111");
        System.out.println(users);
        return users.toString();
    }



    /**
     * 删除用户
     */
    @RequestMapping("/delete")
    public String deleteUser(String id) {
        userMapper.deleteById(id);
        return "delete success";
    }
}
