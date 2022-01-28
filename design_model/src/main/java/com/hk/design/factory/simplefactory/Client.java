package com.hk.design.factory.simplefactory;

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
        SimpleCoffeeFactory simpleCoffeeFactory = new SimpleCoffeeFactory();
        coffeeStore.setFactory(simpleCoffeeFactory);

        coffeeStore.provideCoffee(AmericanCoffee.class);
        coffeeStore.provideCoffee(LatteCoffee.class);


    }


}
