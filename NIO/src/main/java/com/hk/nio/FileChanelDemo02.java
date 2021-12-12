package com.hk.nio;

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
public class FileChanelDemo02 {

    public static void main(String[] args) throws Exception {

        // 读取文件
        RandomAccessFile accessFile = new RandomAccessFile("F:\\JavaCode\\NIO\\src\\main\\resources\\02.txt", "rw");

        // 创建FileChannel
        FileChannel channel = accessFile.getChannel();

        // 创建 buffer 缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 写数据
        String str = "qwertytyuuuuuiikjj";
        buffer.clear() ;

        // 写入数据到缓冲区
        buffer.put(str.getBytes());

        // 写缓冲区数据到文件通道
        buffer.flip() ;


        while (buffer.hasRemaining()){
            channel.write(buffer);

        }
        channel.close();
        accessFile.close();
        System.out.println("操作结束");

    }



}




