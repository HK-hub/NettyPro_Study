package com.hk.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author : HK意境
 * @ClassName : NettyByteBufDemo2
 * @date : 2021/12/10 11:19
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NettyByteBufDemo2 {

    public static void main(String[] args) {

        // 创建 byteBuf
        String src = "Hello 尚硅谷!" ;
        ByteBuf buf = Unpooled.copiedBuffer(src, CharsetUtil.UTF_16);

        /**
         * hasArray() : 判断是否有 byte 字节数组
         * array() : 返回buf 中的字节数组
         *
         */
        if (buf.hasArray()) {

            //
            byte[] bytes = buf.array();
            System.out.println(new String(bytes,CharsetUtil.UTF_16));

            System.out.println(buf.readerIndex());
            System.out.println(buf.writerIndex());
            System.out.println(buf.capacity());
            System.out.println(buf.readableBytes());
            System.out.println(buf.readByte());
            System.out.println(buf.readableBytes());
            System.out.println(buf.getByte(2));
            System.out.println(buf.readableBytes());
            System.out.println(buf.getChar(1));
            System.out.println(buf.readCharSequence(2,CharsetUtil.UTF_16));
            System.out.println(buf.getCharSequence(0,2, CharsetUtil.UTF_16));
        }

    }





}
