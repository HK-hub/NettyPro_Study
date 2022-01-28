package com.hk.design.factory.absfactory.factory;

import com.hk.design.factory.absfactory.product.Dessert;
import com.hk.design.factory.absfactory.product.MatchaMousse;
import com.hk.design.factory.simplefactory.AmericanCoffee;
import com.hk.design.factory.simplefactory.Coffee;

/**
 * @author : HK意境
 * @ClassName : AmericanDessertFactory
 * @date : 2022/1/20 19:31
 * @description : 美式甜品工厂
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class AmericanDessertFactory implements DessertFactory{

    @Override
    public Coffee createCoffee() {

        return new AmericanCoffee();
    }

    @Override
    public Dessert createDessert() {

        return new MatchaMousse();
    }
}
