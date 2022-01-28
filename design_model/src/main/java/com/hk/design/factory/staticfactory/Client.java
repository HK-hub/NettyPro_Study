package com.hk.design.factory.staticfactory;

import com.hk.design.factory.simplefactory.AmericanCoffee;
import com.hk.design.factory.simplefactory.LatteCoffee;
import com.hk.design.factory.simplefactory.SimpleCoffeeFactory;

/**
 * @author : HK意境
 * @ClassName : Client
 * @date : 2022/1/20 16:26
 * @description : 客户端定咖啡的客户端
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class Client {



    public static void main(String[] args) {

        CoffeeStore coffeeStore = new CoffeeStore();

        coffeeStore.provideCoffee(AmericanCoffee.class);
        coffeeStore.provideCoffee(LatteCoffee.class);


    }


}





