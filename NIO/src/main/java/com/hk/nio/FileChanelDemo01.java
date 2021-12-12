package com.hk.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : HK意境
 * @ClassName : FileChanelDemo01
 * @date : 2021/12/8 13:22
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class FileChanelDemo01 {

    public static void main(String[] args) throws Exception {

        // 读取文件
        RandomAccessFile accessFile = new RandomAccessFile("F:\\JavaCode\\NIO\\src\\main\\resources\\01.txt", "rw");

        // 创建FileChannel
        FileChannel channel = accessFile.getChannel();


        // 创建 buffer 缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 读取数据到 缓冲区
        int read = channel.read(buffer);

        while (read != -1){
            System.out.println("读取了：" + read);
            buffer.flip();

            while(buffer.hasRemaining()){
                System.out.println((char) buffer.get());
            }
            buffer.clear();
            read = channel.read(buffer) ;

        }
        accessFile.close();
        System.out.println("操作结束");

    }



}




