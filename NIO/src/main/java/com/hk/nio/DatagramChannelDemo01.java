package com.hk.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author : HK意境
 * @ClassName : DatagramChannelDemo01
 * @date : 2021/12/8 14:49
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class DatagramChannelDemo01 {

    @Test
    public void sendDatagram() throws IOException, InterruptedException {

        // 创建 DatagramChannel
        DatagramChannel sendDatagramChannel = DatagramChannel.open();

        // 发送地址
        InetSocketAddress sendAddress = new InetSocketAddress("127.0.0.1",9999);

        // 发送数据 buffer
        //ByteBuffer buffer = ByteBuffer.wrap("发送数据 send".getBytes(StandardCharsets.UTF_8));


        System.out.println("开始发送： ");
        // 发送数据
        while (true){
            ByteBuffer buffer = ByteBuffer.wrap("发送数据 send".getBytes(StandardCharsets.UTF_8));
            sendDatagramChannel.send(buffer,sendAddress);
            System.out.println("完成发送：");
            Thread.sleep(2000);
        }


    }


    @Test
    public void receiveDatagram() throws IOException {

        // 接收
        DatagramChannel receiveDatagramChannel = DatagramChannel.open();

        // buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // bind
        InetSocketAddress sendAddress = new InetSocketAddress(9999);
        receiveDatagramChannel.bind(sendAddress);


        // 接收
        while(true){
            buffer.clear();
            SocketAddress socketAddress = receiveDatagramChannel.receive(buffer);
            buffer.flip() ;

            System.out.println("地址： " + socketAddress.toString());
            System.out.println("接收数据：" +Charset.forName("UTF-8").decode(buffer).toString());

        }



    }


}
