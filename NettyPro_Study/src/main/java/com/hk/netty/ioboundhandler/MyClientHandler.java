package com.hk.netty.ioboundhandler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author : HK意境
 * @ClassName : MyClientHandler
 * @date : 2021/12/11 14:56
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {


    }


    // 重写 channelActive 发送数据
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("MyClientHanlder 发送数据");
        ctx.writeAndFlush(132456L);

        /**
         * 分析：
         *      1."abcdabcd...." 是16 个字节
         *      2. 该处理器的前一个 handler 是 MyLongToMessageEncoder
         *      3. MyLongToMessageEncoder 的父类是 MessageToLongEncoder
         *              该类(父类) 有编码数据类型检测，如果不是匹配的类型那么跳过编码，原样发出给下一个 handler
         *
         */
        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd", CharsetUtil.UTF_8));


    }
}
