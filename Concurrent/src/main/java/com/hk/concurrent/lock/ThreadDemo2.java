package com.hk.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : HK意境
 * @ClassName : ThreadDemo2
 * @date : 2021/12/19 13:06
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class ThreadDemo2 {

    public static void main(String[] args) {

        Share share = new Share();

        // +1
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                share.incr();
            }
        },"AA").start();


        // +1
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                share.decr();
            }
        },"BB").start();

        // +1
        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                share.incr();
            }
        },"CC").start();


        new Thread(() ->{
            for (int i = 0; i < 10; i++) {
                share.decr();
            }
        },"DD").start();

    }







}



class Share{

    private int number = 0 ;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // +1
    public void incr(){

        // 上锁
        lock.lock();
        try {

            // 判断
            while(number != 0){
                condition.await();
            }
            // 干活
            ++number ;
            System.out.println(Thread.currentThread().getName() + " :: " + number);

            // 通知,发送信号
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
        }

    }

    // -1
    public void decr(){

        // 上锁
        lock.lock();
        try {

            // 判断
            while(number != 1){
                condition.await();
            }
            // 干活
            --number ;
            // 通知
            System.out.println(Thread.currentThread().getName() + " :: " + number);
            // 通知,发送信号
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
        }

    }





}