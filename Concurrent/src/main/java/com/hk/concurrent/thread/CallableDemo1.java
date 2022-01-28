package com.hk.concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author : HK意境
 * @ClassName : CallableDemo1
 * @date : 2021/12/19 16:13
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class CallableDemo1 implements Callable {
    @Override
    public Integer call() throws Exception {
        System.out.println("Callable is executing");
        return 200 ;
    }


    public static void main(String[] args) throws Exception {

        // Runnable 接口创建线程
        new Thread(new RunnableDemo(),"runnablDemo").start();

        // Callable 接口创建线程
        //System.out.println("callable result = " +new CallableDemo1().call());

        new FutureTask<Integer>(new CallableDemo1()).run();

        // Lambda 表达式
        FutureTask<Integer> integerFutureTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " come in callable");
            return 1024;
        });

        // 创建一个线程
        new Thread(integerFutureTask, "integer").start();


        // 调用 futureTask 的get 方法，
        System.out.println(integerFutureTask.get());



    }

}





class RunnableDemo implements Runnable{


    @Override
    public void run() {

        System.out.println("Runnable is executing");
    }
}