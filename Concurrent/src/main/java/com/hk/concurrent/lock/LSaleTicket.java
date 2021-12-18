package com.hk.concurrent.lock;

import lombok.Data;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : HK意境
 * @ClassName : LSaleTicket
 * @date : 2021/12/12 22:01
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class LSaleTicket {


    public static void main(String[] args) {




    }

}



@Data
class LTicket{

    private int num = 30 ;

    // 可重入锁
    private final Lock lock = new ReentrantLock();

    public void sale(){

        // 上锁
        lock.lock();
        try{
            if (num > 0){
                num-- ;
                System.out.println(Thread.currentThread().getName() + " 正在买票, " + " 剩余票数："+ num);
            }
        }finally {

            // 解锁
            lock.unlock();
        }
    }

}