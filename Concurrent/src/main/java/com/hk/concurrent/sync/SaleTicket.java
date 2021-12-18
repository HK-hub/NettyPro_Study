package com.hk.concurrent.sync;

import lombok.Data;
import lombok.SneakyThrows;

/**
 * @author : HK意境
 * @ClassName : SaleTicket
 * @date : 2021/12/12 21:42
 * @description :

 * @Bug :
 * @Modified :
 * @Version : 1.0
 */



public class SaleTicket {

    public static void main(String[] args) {

        // 创建 Ticket
        Ticket ticket = new Ticket();

        // 创建线程
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                for (int i = 0 ; i < 40 ; ++i){
                    ticket.sale();
                    Thread.sleep(1000L);
                }

            }
        }, "saler1").start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                for (int i = 0 ; i < 40 ; ++i){
                    ticket.sale();
                    Thread.sleep(1000L);

                }

            }
        }, "saler2").start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                for (int i = 0 ; i < 40 ; ++i){
                    ticket.sale();
                    Thread.sleep(1000L);

                }

            }
        }, "saler3").start();

    }

}



@Data
class Ticket{

    // 票的总数
    private int number = 30;

    /**
     * 操作方法：卖票
     *  同步线程
     *
     */
    public synchronized void sale() {

        // 票的数量
        /*synchronized((Object) number){

            if (number > 0){
                number-- ;
                System.out.println(Thread.currentThread().getName() + " 正在买票, " + " 剩余票数："+ number);
            }

        }*/

        // 可重入锁 ： ReentrantLock



        if (number > 0){
            number-- ;
            System.out.println(Thread.currentThread().getName() + " 正在买票, " + " 剩余票数："+ number);
        }


    }

}