package com.hk.design.factory.simplefactory;


/**
 * @author : HK意境
 * @ClassName : Coffee
 * @date : 2022/1/20 16:27
 * @description : 咖啡类
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */

public abstract class Coffee {
    public  void addMilk(){
        System.out.println("加牛奶");
    }
    public  void addSugar(){
        System.out.println("加糖");

    }
    public abstract String getName();
}
