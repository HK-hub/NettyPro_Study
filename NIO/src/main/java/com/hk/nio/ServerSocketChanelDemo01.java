package com.hk.nio;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Locale;

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
public class ServerSocketChanelDemo01 {

    public static void main(String[] args) throws Exception {

        // 定义端口号
        int port = 8888 ;

        // 创建 ServerSocketChanel， 打开ssc
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 创建 buffer
        ByteBuffer buffer = ByteBuffer.wrap("hello java NIO".getBytes());

        // 监听端口
        ServerSocketChannel bind = ssc.bind(new InetSocketAddress(port));

        // 开启非阻塞设置
        ssc.configureBlocking(false);

        // 开始监听端口
        while(true){

            System.out.println("waiting for connections");
            SocketChannel accept = ssc.accept();
            if (accept != null) {

                System.out.println("Incoming connection from(accept.getRemoteAddress): " + accept.getRemoteAddress());
                System.out.println("Incoming connection from(accept.socket.getRemoteAddress): " + accept.getRemoteAddress());
                break;

            }else {
                System.out.println("null");
                Thread.sleep(2000);
            }

        }

        ssc.close();

    }



}




