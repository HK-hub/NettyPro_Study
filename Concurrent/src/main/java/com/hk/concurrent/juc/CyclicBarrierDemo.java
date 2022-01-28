package com.hk.concurrent.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : HK意境
 * @ClassName : CyclicBarrierDemo
 * @date : 2021/12/19 20:05
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class CyclicBarrierDemo {

    // 创建固定值
    private static final int NUMBER = 7 ;


    public static void main(String[] args) {

        // 创建循环栅栏 CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("集齐七颗龙珠，召唤神龙");
        });

        for (int i = 0; i < 7; i++) {

            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 星龙珠被收集到了");

                //等待
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }, ""+i) ;

        }


    }



}
