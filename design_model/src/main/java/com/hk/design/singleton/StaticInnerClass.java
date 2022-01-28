package com.hk.design.singleton;

/**
 * @author : HK意境
 * @ClassName : StaticInnerClass
 * @date : 2022/1/19 22:31
 * @description :单例模式————静态内部类
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class StaticInnerClass {

    // 静态内部类的特点：在外部类进行加载的时候，内部类不会加载，当使用到内部类的时候才进行加载，且是线程安全的加载


    // 静态内部类
    private static class Singleton{

        private static final StaticInnerClass instance = new StaticInnerClass();

    }


    public static StaticInnerClass getInstance(){

        return Singleton.instance ;
    }


}
