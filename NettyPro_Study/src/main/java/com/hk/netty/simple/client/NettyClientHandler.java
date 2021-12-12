package com.hk.netty.simple.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;

/**
 * @author : HK意境
 * @ClassName : NettyClientHandler
 * @date : 2021/12/9 19:02
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {



    /**
     * @methodName : 当通道就绪就会出发改方法
     * @author : HK意境
     * @date : 2021/12/9 19:09
     * @description :
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("client: " + ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello , server: （*＾-＾*）",CharsetUtil.UTF_8));
    }


    /**
     * @methodName : 接收消息
     * @author : HK意境
     * @date : 2021/12/9 19:10
     * @description :
     * @Todo : 当通道有读取事件时会触发
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf) msg ;
        System.out.println("服务器回复：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器端地址：" + ctx.channel().remoteAddress());

    }


    // 异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();

    }
}
