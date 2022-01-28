package com.hk.design.singleton;

/**
 * @author : HK意境
 * @ClassName : LazyTypeUnSafe
 * @date : 2022/1/19 22:10
 * @description : 单例模式————懒汉式线程不安全
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class LazyTypeUnSafe {

    private static LazyTypeUnSafe instance;

    // 私有化构造方法
    private LazyTypeUnSafe(){


    }


    // 懒汉式：提供一个静态的共有方法，当使用到该方法是，才去创建instance
    public static LazyTypeUnSafe getInstance(){

        if(instance == null){
            instance = new LazyTypeUnSafe();
        }

        return instance ;

    }


    public static void main(String[] args) {


    }

}
