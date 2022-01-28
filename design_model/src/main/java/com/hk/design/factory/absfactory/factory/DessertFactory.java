package com.hk.design.factory.absfactory.factory;

import com.hk.design.factory.absfactory.product.Dessert;
import com.hk.design.factory.simplefactory.Coffee;

/**
 * @author : HK意境
 * @ClassName : DessertFactory
 * @date : 2022/1/20 19:29
 * @description : 抽象工厂
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public interface DessertFactory {

    // 生产咖啡的功能
    public Coffee createCoffee() ;

    // 生产甜品的功能
    public Dessert createDessert() ;


}
