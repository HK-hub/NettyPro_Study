package com.hk.design.factory.absfactory.factory;

import com.hk.design.factory.absfactory.product.Dessert;
import com.hk.design.factory.absfactory.product.Trimisu;
import com.hk.design.factory.simplefactory.Coffee;
import com.hk.design.factory.simplefactory.LatteCoffee;

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
public class ItalyDessertFactory implements DessertFactory{

    @Override
    public Coffee createCoffee() {

        return new LatteCoffee();
    }

    @Override
    public Dessert createDessert() {

        return new Trimisu();
    }
}
