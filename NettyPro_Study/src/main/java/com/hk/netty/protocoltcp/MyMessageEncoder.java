package com.hk.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author : HK意境
 * @ClassName : MyMessageEncoder
 * @date : 2021/12/11 19:38
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class MyMessageEncoder extends MessageToByteEncoder<MessageProtocol> {



    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {

        System.out.println("---------------数据编码：-------------------");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());


    }
}
