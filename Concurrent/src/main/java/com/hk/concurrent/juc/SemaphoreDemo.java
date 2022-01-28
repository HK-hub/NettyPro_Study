package com.hk.concurrent.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author : HK意境
 * @ClassName : SemaphoreDemo
 * @date : 2021/12/19 20:27
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class SemaphoreDemo {
    // 信号量机制


    public static void main(String[] args) {

        // 初始化信号量
        Semaphore semaphore = new Semaphore(3);

        // 模拟6俩车
        for (int i = 0; i < 6; i++) {
            new Thread(()-> {

                try {
                    // 加锁，申请临界资源
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 车辆抢占了停车位");

                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + " 离开了");


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    semaphore.release();
                }

            },""+i).start();
        }



    }




}
