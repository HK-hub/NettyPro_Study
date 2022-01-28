package com.hk.design.factory.absfactory;

import com.hk.design.factory.absfactory.factory.AmericanDessertFactory;
import com.hk.design.factory.absfactory.factory.DessertFactory;
import com.hk.design.factory.absfactory.factory.ItalyDessertFactory;

/**
 * @author : HK意境
 * @ClassName : Client
 * @date : 2022/1/20 19:40
 * @description : 客户端
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class Client {

    public static void main(String[] args) {

        // 创建 意大利风味甜品工厂
        DessertFactory dessertFactory = new AmericanDessertFactory();

        // 获取提拉米苏和拿铁咖啡
        dessertFactory.createDessert().show();
        System.out.println(dessertFactory.createCoffee().getName());

    }




}
