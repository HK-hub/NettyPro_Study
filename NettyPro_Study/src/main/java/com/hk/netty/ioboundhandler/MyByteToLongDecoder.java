package com.hk.netty.ioboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author : HK意境
 * @ClassName : MyByteToLongDecoder
 * @date : 2021/12/11 14:37
 * @description :
 * @Todo : 自定义编解码器
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /**
     * @methodName :
     * @author : HK意境
     * @date : 2021/12/11 14:38
     * @description :
     * @Todo :
     * @params :
         * @param : ByteBuf in ： 入站的 ByteBuf
         * @param : List 集合： 将解码后的数据传入下一个handler
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {


        // long 类型占用8 个字节，需要判断有八个字节才能读取一个long
        if (in.readableBytes() >= 8){
            out.add(in.readLong());
        }




    }
}
