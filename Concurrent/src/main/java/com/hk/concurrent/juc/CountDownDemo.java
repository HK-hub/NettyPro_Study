package com.hk.concurrent.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author : HK意境
 * @ClassName : CountDownDemo
 * @date : 2021/12/19 19:58
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class CountDownDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 号同学离开教室");

                // 计数-1
                countDownLatch.countDown();

            },""+i).start();
        }

        // 等待
        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + " 班长锁门走人了");

    }






}
