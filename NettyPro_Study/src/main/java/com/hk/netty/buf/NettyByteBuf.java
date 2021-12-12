package com.hk.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author : HK意境
 * @ClassName : NettyByteBuf
 * @date : 2021/12/10 10:58
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NettyByteBuf {


    public static void main(String[] args) {


        // 非池化 Unpooled
        // 创建一个 ByteBuf ： 该对象包含一个 byte[10] 数组，长度韦10
        ByteBuf buffer = Unpooled.buffer(10);

        /***
         * ByteBUf :
         *      capacity : 返回byte 数组的长度
         *      writableBytes : 返回剩余可写字节
         *      readableBytes : 返回剩余可读字节
         *      resetReadIndex: 重置下一个读取位置（置0）
         *      isReadable: 是否可读
         *  在 Netty 的 ByteBuf 中不需要使用 flip() 进行模式转换
         *      ：底层维护了readIndex 和 writeIndex
         *
         *
         */


        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.writeByte(i);
        }

        System.out.println("剩余可读字节数："+buffer.readableBytes());
        // 输出
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
        }
        System.out.println("剩余可读字节数："+buffer.readableBytes());



    }






}
