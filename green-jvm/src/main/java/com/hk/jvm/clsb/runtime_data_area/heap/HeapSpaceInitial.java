package com.hk.jvm.clsb.runtime_data_area.heap;

/**
 * @author : HK意境
 * @ClassName : HeapSpaceInitial
 * @date : 2022/1/25 20:05
 * @description : -Xms 用来设置初始的 堆空间(年轻代+老年代)的初始内存大小，
 *                     -X 是 JVM 的运行参数，
 *                     ms 是 memory start 的缩写
 *                -Xmx 用来设置堆空间的最大内存大小
 *
 *            默认情况下：
 *                初始内存大小=电脑物理内存/64
 *                最大内存大小=定脑物理内存/4
 *
 *            手动设置：
 *                -Xms=600m -Xmx=600m
 *                在开发中建议把初始内存和最大内存设置为相同的值
 *
 *            查看当前参数：
 *                  方式一：
 *                          jps 显示当前进程后，
 *                          jstat -gc 进程id
 *                  方式二：
 *                          -XX:+PrintGCDetails 虚拟机参数
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class HeapSpaceInitial {

    public static void main(String[] args) throws InterruptedException {

        Runtime runtime = Runtime.getRuntime();

        // 返回Java 虚拟机中堆的最大内存大小
        var maxMemory = runtime.maxMemory() / 1024 / 1024 ;

        // 返回 Java虚拟机中堆空闲内存大小
        long freeMemory = runtime.freeMemory() / 1024 /1024;

        // 放回Java虚拟机中堆内存总量，初始化的总量
        var totalMemory = runtime.totalMemory() /1024 /1024 ;

        System.out.println("max=" + maxMemory);
        System.out.println("free=" + freeMemory);
        System.out.println("total="+totalMemory);




    }


}
