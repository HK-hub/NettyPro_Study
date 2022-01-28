package com.hk.design.factory.simplefactory;


import java.util.ArrayList;

/**
 * @author : HK意境
 * @ClassName : AmericanCoffee
 * @date : 2022/1/20 16:39
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class AmericanCoffee extends Coffee {

    public AmericanCoffee(){
        addMilk();
        addSugar();
        getName();
    }

    @Override
    public void addMilk() { System.out.println("给咖啡加奶"); }

    @Override
    public void addSugar() { System.out.println("给咖啡加糖"); }

    @Override
    public String getName() {
        System.out.println("美式咖啡");
        return "美式咖啡"; }
}
