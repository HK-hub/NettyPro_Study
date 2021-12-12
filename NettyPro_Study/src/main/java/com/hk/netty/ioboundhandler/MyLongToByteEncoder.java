package com.hk.netty.ioboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author : HK意境
 * @ClassName : MyLongToByteEncoder
 * @date : 2021/12/11 14:53
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {

        System.out.println("MyLongToByteEncoder.encode() 被调用");
        System.out.println("msg=" + msg);


        // 发出消息
        out.writeLong(msg);
    }
}
