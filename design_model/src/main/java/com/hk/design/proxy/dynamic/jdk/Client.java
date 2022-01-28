package com.hk.design.proxy.dynamic.jdk;

import java.util.Properties;

/**
 * @author : HK意境
 * @ClassName : Client
 * @date : 2022/1/22 15:22
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class Client {

    public static void main(String[] args) {

        SellTickets proxyObject = new ProxyFactory().getProxyObject();

        proxyObject.sell();


        System.out.println(proxyObject.getClass());


        // 让程序一直执行
        while(true) {
            ;
        }

    }


}
