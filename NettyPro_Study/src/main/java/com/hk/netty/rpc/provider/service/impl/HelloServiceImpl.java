package com.hk.netty.rpc.provider.service.impl;

import com.hk.netty.rpc.funcation.HelloService;

/**
 * @author : HK意境
 * @ClassName : HelloServiceImpl
 * @date : 2021/12/12 16:11
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class HelloServiceImpl implements HelloService {



    @Override
    public String sayHello(String msg) {
        System.out.println("客户端收到消息...");
        if (msg != null){
            return "你好客户端，我已经收到你的消息了：{" + msg + "}" ;
        }else {
            return "你好客户端，我已经收到你的消息了,但是没有内容" ;
        }

    }
}
