package com.hk.concurrent.test;

import org.junit.jupiter.api.Test;

/**
 * @author : HK意境
 * @ClassName : ThreadTest
 * @date : 2021/12/12 20:59
 * @description : 当用户线程全部结束后，只有守护进程，那么JVM 会结束运行
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */

public class ThreadTest {


    @Test
    public void threadName(){

        // 新建一个线程，重写run 方法
        Thread thread = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true){

            }
        },"newThread");

        // 设置守护线程
        thread.setDaemon(true);

        //开启线程
        thread.start();

        System.out.println(Thread.currentThread().getName() + ", over");
    }

}
