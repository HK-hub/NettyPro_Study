package com.hk.design.singleton;

/**
 * @author : HK意境
 * @ClassName : TwiceCheck
 * @date : 2022/1/19 22:24
 * @description :单例模式————双检查
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class TwiceCheck {

    // volatile 关键字用于防止指令重排，适用于读多写少的情况
    private static volatile TwiceCheck instance ;

    private TwiceCheck(){

    }


    // 提供哦你一个静态的共有方法，加入双重检查代码，解决线程安全问题，同时解决懒加载问题
    public static synchronized TwiceCheck getInstance(){

        if (instance == null){

            synchronized (TwiceCheck.class){

                if (instance == null){
                    instance = new TwiceCheck();
                }
            }
        }
        return instance ;
    }


}
