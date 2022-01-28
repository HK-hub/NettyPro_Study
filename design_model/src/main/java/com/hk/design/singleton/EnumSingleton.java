package com.hk.design.singleton;

import lombok.Data;

/**
 * @author : HK意境
 * @ClassName : EnumSingleton
 * @date : 2022/1/20 13:01
 * @description :单例模式，枚举实现
 * @Todo : 推荐使用
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class EnumSingleton {

    public static void main(String[] args) {
        System.out.println(Singleton.INSTANCE );
        EnumSingleton instance = Singleton.getInstance();
        EnumSingleton instance1 = Singleton.getInstance();
        System.out.println(instance1.hashCode());
        System.out.println(instance1.hashCode());

    }
}


enum Singleton{

    //类函数调用媒介
    INSTANCE;

    private static EnumSingleton instance = new EnumSingleton();


    // 私有化构造方法

    public static EnumSingleton getInstance(){

        return instance ;
    }


}
