package com.hk.design.singleton;

/**
 * @author : HK意境
 * @ClassName : LazyTypeThreadSafe
 * @date : 2022/1/19 22:15
 * @description : 单例模式————懒汉式线程安全
 * @Todo : 加锁 synchronized ，进行线程互斥
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class LazyTypeThreadSafe {

    private static LazyTypeThreadSafe instance;

    // 私有化构造方法
    private LazyTypeThreadSafe(){


    }


    // 懒汉式：提供一个静态的共有方法，加入了线程同步处理，当使用到该方法时，才去创建instance
    //
    public  static synchronized LazyTypeThreadSafe getInstance(){


        if(instance == null){

            instance = new LazyTypeThreadSafe();
        }

        return instance ;

    }


    public static void main(String[] args) {






    }



}
