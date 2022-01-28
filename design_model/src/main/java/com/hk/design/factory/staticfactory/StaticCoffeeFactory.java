package com.hk.design.factory.staticfactory;

import com.hk.design.factory.simplefactory.AmericanCoffee;
import com.hk.design.factory.simplefactory.Coffee;
import com.hk.design.factory.simplefactory.LatteCoffee;

/**
 * @author : HK意境
 * @ClassName : SimpleCoffeeFactory
 * @date : 2022/1/20 16:28
 * @description : 简单工厂模式
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class StaticCoffeeFactory {


    public static Coffee createCoffee(Class<? extends Coffee> clazz){

        if(clazz.equals(AmericanCoffee.class)){
            // 美咖啡
            return new AmericanCoffee();
        }else if(clazz.equals(LatteCoffee.class)){
            return new LatteCoffee();
        }else {
            return null ;
        }

    }


}
