package com.ljh.serve.controller;

import com.ljh.serve.entity.User;
import com.ljh.serve.response.ResponseResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : HK意境
 * @ClassName : ServeController
 * @date : 2021/12/15 20:26
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@CrossOrigin // 跨域注解，填写后即可跨域
@RestController
@RequestMapping(value = "/serve")
public class ServeController {

    @GetMapping("/test")
    public ResponseResult test(){

        return ResponseResult.SUCCESS().setData(new User("张三" , "1548"));

    }



}