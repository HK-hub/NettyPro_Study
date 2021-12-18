package com.hk.concurrent.utils;


import lombok.Data;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * @author : HK意境
 * @ClassName : Sync
 * @date : 2021/12/12 20:17
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
public class Sync {

    public static void main(String[] args) throws FileNotFoundException {

        // 同步执行以下代码
        //log.info("do other code ...");
        // 读取文件 同步执行
        //RandomAccessFile accessFile = new RandomAccessFile("F:\\JavaCode\\Concurrent\\build.gradle", "r");

        // 异步读取文件
        new Thread(() -> {
            try {
                new RandomAccessFile("F:\\JavaCode\\Concurrent\\build.gradle", "r");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }).start();


        //
        System.out.println("do other code ....");

    }

}
