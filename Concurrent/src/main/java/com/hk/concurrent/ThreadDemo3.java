package com.hk.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : HK意境
 * @ClassName : ThreadDemo3
 * @date : 2021/12/19 13:27
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class ThreadDemo3 {
}


class ShareResource{

    // 定义标志位
    private int flag = 1;
    // 创建 lock 锁
    private Lock lock = new ReentrantLock();

    // 创建三个 condition
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();




}