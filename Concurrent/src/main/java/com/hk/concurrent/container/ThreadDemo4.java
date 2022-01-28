package com.hk.concurrent.container;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author : HK意境
 * @ClassName : ThreadDemo4
 * @date : 2021/12/19 13:39
 * @description : list 集合线程不安全问题
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class ThreadDemo4 {

    public static void main(String[] args) {

        // 线程不安全
        List<String> list = new ArrayList<>();

        // Vector 解决方案
        List<String> vector = new Vector<String>();

        // Collections  工具类解决方案
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());


        // JUC  解决方案 :
        // copyOnWriteArrayList 写时复制技术，并发读，独立写
        CopyOnWriteArrayList<String> cwList = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {

            new Thread(()->{
                // 向集合中添加数据
                list.add(UUID.randomUUID().toString().substring(0,8));
                // 从集合中获取内容
                System.out.println(list);
            },""+i).start();

        }


        // HashSet 线程安全问题
        Set set = new HashSet<String>();

        // 解决方案
        // CopyOnWriteArraySet
        CopyOnWriteArraySet arraySet = new CopyOnWriteArraySet(new HashSet<String>());

        for (int i = 0; i < 10; i++) {

            new Thread(()->{
                // 向集合中添加数据
                set.add(UUID.randomUUID().toString().substring(0,8));
                // 从集合中获取内容
                System.out.println(set);
            },""+i).start();

        }


        // HashMap 线程安全问题
        Map<String, String> stringHashMap = new HashMap<String, String>();

        // 解决方案： ConcurrentHashMap
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();


    }


}
